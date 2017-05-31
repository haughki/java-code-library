package org.haughki.codeLibrary.filesAndDirectories;

import org.haughki.codeLibrary.aacommon.Common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncFileIo {

    public static void main(String args[]) throws Exception {
        System.out.println("Main thread is: " + Thread.currentThread().getId());
        ExecutorService pool = new ScheduledThreadPoolExecutor(3);
        try (AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(Common.SOME_TEST_DATA, EnumSet.of(StandardOpenOption.READ), pool)) {

            final int bufferCount = 20;
            final int bufferSize = 8;
            ByteBuffer buffers[] = new ByteBuffer[bufferCount];
            // This looping approach is terrible.  Need a way to keep reading until done.  See RecursiveAsyncFileIo
            for (int i = 0; i < bufferCount; i++) {
                buffers[i] = ByteBuffer.allocate(bufferSize);
                fileChannel.read(buffers[i], i * bufferSize, buffers[i], new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public synchronized void completed(Integer result, ByteBuffer attachment) {
                        try {
                            System.out.println(new String(attachment.array(), "UTF-16"));
                            Thread.sleep(50); // add some arbitrary work to make sure it's really async
                        } catch (InterruptedException | UnsupportedEncodingException e) {
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

            Thread.sleep(5000);
            System.out.println("Shutting down pool...");
            pool.shutdown(); // tries to execute all existing tasks, but takes no more
            pool.awaitTermination(10, TimeUnit.SECONDS);  // blocks until shutdown completes or timeout

            // If we're going to print out the bytes we've read, we first need to reconstruct the entire byte string. This is because
            // we have some non-ASCII, multi-byte characters; if we tried to print them as they are read, we might end up with a
            // "split character" which would decode incorrectly.
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

            for (ByteBuffer byteBuffer : buffers) {
                byteStream.write(byteBuffer.array());
            }

            System.out.print(new String(byteStream.toByteArray(), "UTF-16"));
        }
    }
}

