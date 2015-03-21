package org.haughki.codeLibrary.threading.producerConsumer;

public class Drop {
    private String message; // Message sent from producer to consumer.

    // True if consumer should wait for producer to send message, false if producer should wait for
    // consumer to retrieve message.
    private boolean empty = true;

    // producer puts
    public synchronized void put(String message) {
        // Wait until message has been retrieved.
        while (!empty) {
            try {
                System.out.println("put waiting...");
                wait(); // waits until notified, AND until it can re-gain the sync lock (take() must exit first)
                System.out.println("put FREE!");
            } catch (InterruptedException e) {
                System.out.println("Drop.put Interrupted! " + e.toString());
            }
        }

        this.message = message;
        empty = false;
        System.out.println("put notifying.");
        notifyAll();  // Notify consumer that status has changed.
    }

    // consumer takes
    public synchronized String take() {
        // Wait until message is available.
        while (empty) {
            try {
                System.out.println("take waiting...");
                wait();
            } catch (InterruptedException e) {
                System.out.println("Drop.take Interrupted! " + e.toString());
            }
        }
        // note that the ordering here doesn't matter much: put() can't actually start executing again until take() has
        // returned and released the synch lock on "this".
        empty = true;
        System.out.println("take notifying.");
        notifyAll();  // Notify producer that status has changed.
        return message;
    }
}