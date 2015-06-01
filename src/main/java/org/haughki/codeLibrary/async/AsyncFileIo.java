package org.haughki.codeLibrary.async;

import org.haughki.codeLibrary.aacommon.Common;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncFileIo {

    public static void main(String args[]) throws Exception {

        ExecutorService pool = new ScheduledThreadPoolExecutor(3);
        try (AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(Common.SOME_TEST_DATA, EnumSet.of(StandardOpenOption.READ), pool)) {

            final int bufferCount = 5;
            final int bufferSize = 8;
            ByteBuffer buffers[] = new ByteBuffer[bufferCount];
            for (int i = 0; i < bufferCount; i++) {
                buffers[i] = ByteBuffer.allocate(bufferSize);
                fileChannel.read(buffers[i], i * bufferSize, buffers[i], new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public synchronized void completed(Integer result, ByteBuffer attachment) {
                        try {
                            Thread.sleep(250); // add some arbitrary work to make sure it's really async
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Completed 1 read on thread: " + Thread.currentThread().getId() + ". Result: " + result);
                    }

                    @Override
                    public void failed(Throwable e, ByteBuffer attachment) {
                        System.out.println("Error: " + e.toString());
                    }
                });
            }

            System.out.println("Shutting down pool...");
            pool.shutdown(); // tries to execute all existing tasks, but takes no more
            pool.awaitTermination(10, TimeUnit.SECONDS);  // blocks until shutdown completes or timeout

            // If we're going to print out the bytes we've read, we first need to reconstruct the entire byte sting. This is because
            // we have some non-ASCII, multi-byte characters; if we tried to print them as they are read, we might end up with a
            // "split character" which would decode incorrectly.
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            for (ByteBuffer byteBuffer : buffers) {
                byteStream.write(byteBuffer.array());
            }

            System.out.print(new String(byteStream.toByteArray()));
/*
            // another way to print with the correct encoding (which is UTF8)
            ByteBuffer buf = ByteBuffer.wrap(byteStream.toByteArray());
            CharBuffer charbuf = Charset.forName("UTF8").decode(buf);
            System.out.print(charbuf);
*/
        }
    }
}

