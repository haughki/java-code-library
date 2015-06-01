package org.haughki.codeLibrary.async;

import org.haughki.codeLibrary.aacommon.Common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;

public class AsyncFileIo_test {

    // Manages communication between spawned, async file read thread
    // and the main thread, which needs to wait for the read thread to finish.
    // This is just one way to communicate.  I tried sharing a sync object
    // as a capture into completed(), but runtime didn't like that...
    final class Manager {
        public synchronized void completed() {
            notifyAll();
        }

        public synchronized void waiting() throws InterruptedException {
            wait();
        }
    }

    public static void main(String[] args) throws Exception {
        new AsyncFileIo_test().readFile();
    }

    private void readFile() throws IOException, InterruptedException {

        System.out.println(Thread.currentThread().getId());
        printFileContents(Common.SOME_TEST_DATA.toString());


        final Manager manager = new Manager();

        try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(Common.SOME_TEST_DATA)) {
            ByteBuffer buffer = ByteBuffer.allocate(8);
            readFile(0, manager, channel, buffer);


            System.out.println("Waiting for completion...");
            manager.waiting();
            System.out.println();
            System.out.println("Finished.");
        }
    }

    private void readFile(final int readStartPosition, final Manager manager, AsynchronousFileChannel channel, final ByteBuffer buffer) {
        channel.read(buffer, readStartPosition, "File async read with buffer",
                new CompletionHandler<Integer, Object>() {
                    @Override
                    public void completed(Integer result, Object attachment) {
                        //System.out.println(attachment + " completed and " + result + " bytes were read:");

                        if (result != -1) {
                            buffer.flip();
                            System.out.println(Thread.currentThread().getId());
                            //System.out.print(new String(buffer.array()));
                            buffer.clear();
                            readFile(readStartPosition + result, manager, channel, buffer);
                        } else {
                            manager.completed();  // notify waiting main thread?
                        }
                    }

                    @Override
                    public void failed(Throwable e, Object attachment) {
                        System.out.println(attachment + " failed with exception:");
                        e.printStackTrace();
                        manager.completed();  // notify waiting main thread
                    }
                });
    }

    private void printFileContents(String path) throws IOException {
        try (FileReader fr = new FileReader(path)) {
            try (BufferedReader br = new BufferedReader(fr)) {

                String textRead = br.readLine();
                System.out.println("File Read with stream I/O: ");

                while (textRead != null) {
                    System.out.println("     " + textRead);
                    textRead = br.readLine();
                }
            }
        }
    }
}
