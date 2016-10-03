package org.haughki.codeLibrary.async;

import org.haughki.codeLibrary.aacommon.Common;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Path;

public class RecursiveAsyncFileIo {

    // Manages communication between spawned, async file read threads and the main thread, which needs to wait for 
    // the read threads to finish.  This is just one way to communicate.  I tried sharing a sync object
    // as a capture into completed(), but runtime didn't like that...
    private final class Coordinator {
        synchronized void completed() {
            notifyAll();
        }

        synchronized void waiting() throws InterruptedException {
            wait(10000);
        }
    }

    public static void main(String[] args) throws Exception {
        synchronousPrintFile(Common.SOME_TEST_DATA);  // print out as a baseline, to make sure the async is right (test)
        
        new RecursiveAsyncFileIo().readFile(Common.SOME_TEST_DATA);
    }

    private void readFile(final Path fileToRead) throws IOException, InterruptedException {

        System.out.println("Main thread id: " + Thread.currentThread().getId());

        final Coordinator coordinator = new Coordinator();

        try (final AsynchronousFileChannel channel = AsynchronousFileChannel.open(fileToRead)) {
            final ByteBuffer buffer = ByteBuffer.allocate(8);  // even numbered byte buffer works nicely with UTF-16 -- see comments below
            readFile(0, coordinator, channel, buffer);
        }

        System.out.println("Waiting for completion...");
        coordinator.waiting();
        System.out.println();
        System.out.println("Finished.");
    }

    private void readFile(final int readStartPosition, final Coordinator coordinator, final AsynchronousFileChannel channel, final ByteBuffer buffer) {
        channel.read(buffer, readStartPosition, "File async read with buffer",
                new CompletionHandler<Integer, Object>() {
                    @Override
                    public void completed(Integer result, Object attachment) {
                        //System.out.println(attachment + " completed and " + result + " bytes were read.");

                        if (result != -1) {
                            buffer.flip();  // flip just means: set the limit of the buffer to current position and
                                            // then set the current position to 0 so that we can get things from the buffer
                            //System.out.println(Thread.currentThread().getId());
                            try {
                                // As long as the file we're reading is UTF-16 and our byte buffer holds an even number
                                // of bytes, we can immediately print the bytes. We know that UTF-16 uses 2 bytes/char,
                                // so an even numbered buffer will always have "complete" characters.  UTF-8 won't work
                                // without some tricky coding.  Note that this is only necessary if we want to _print_ or
                                // otherwise display the bytes.  If we're just streaming, it's not an issue.
                                System.out.print(new String(buffer.array(), "UTF-16"));
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            buffer.clear();
                            // Note that even though we're reusing/"sharing" the read/write buffer, the overall operation
                            // is "thread-safe" because the reads are "in order". Even though multiple different
                            // threads may be doing the reading, they'll never be doing it at the same time because we don't kick
                            // the next recursive read until the last one has completed.  Async doesn't mean "concurrent multi-threaded."
                            readFile(readStartPosition + result, coordinator, channel, buffer);
                        } else {
                            coordinator.completed();  // notify waiting main thread
                        }
                    }

                    @Override
                    public void failed(Throwable e, Object attachment) {
                        System.out.println(attachment + " failed with exception:");
                        e.printStackTrace();
                        coordinator.completed();  // notify waiting main thread
                    }
                });
    }

    // Just used to create a baseline for testing against the async reader
    private static void synchronousPrintFile(final Path fileToRead) throws IOException {
        try (InputStream stream = new FileInputStream(fileToRead.toString())) {
            try (Reader reader = new InputStreamReader(stream, Charset.forName("UTF-16"))) {
                try (BufferedReader br = new BufferedReader(reader)) {
                    String textRead = br.readLine();
                    System.out.println("Baseline: synchronous file read with stream I/O: ");

                    while (textRead != null) {
                        System.out.println("     " + textRead);
                        textRead = br.readLine();
                    }
                }
            }
        }
    }
}
