package org.haughki.codeLibrary.threading.producerConsumer;


/**
 * Encapsulates a message to be "sent" from one class to another, where each class is on a different thread.
 * Access to public class methods is synchronized to ensure thread-safe access to the shared .message.  There
 * is no message queue, so a message sender may not send a new message until THE message receiver has "read"
 * a message; similarly, a receiver should not try to read a message until there is one to read.  To accomplish
 * this, sender/receiver access to the message is coordinated via calls to .wait -- waiting pauses the current
 * thread and releases the sync lock (on this) until a separate thread calls notify.  See inline comments.
 */
public class Drop {
    private String message; // Message sent from producer to consumer.

    // False if consumer should wait for producer to send message, true if producer should wait for
    // consumer to retrieve message.
    private boolean messageIsAvailable = false;

    // producer puts
    public synchronized void put(String message) {
        // Wait until message has been retrieved.
        while (messageIsAvailable) {
            try {
                System.out.println("put waiting...");
                wait(); // sleeps thread, releases sync lock. Waits until notified AND until it can re-gain the sync lock (take() must exit first)
                System.out.println("put FREE!");
            } catch (InterruptedException e) {
                System.out.println("Drop.put Interrupted! " + e.toString());
            }
        }

        this.message = message;
        messageIsAvailable = true;
        System.out.println("put notifying.");
        notifyAll();  // Notify consumer that status has changed.
    }

    // consumer takes
    public synchronized String take() {
        // Wait until message is available.
        while (!messageIsAvailable) {
            try {
                System.out.println("take waiting...");
                wait();
            } catch (InterruptedException e) {
                System.out.println("Drop.take Interrupted! " + e.toString());
            }
        }
        // note that the ordering here doesn't matter much: put() can't actually start executing again until take() has
        // returned and released the synch lock on "this".
        messageIsAvailable = false;
        System.out.println("take notifying.");
        notifyAll();  // Notify producer that status has changed.
        return message;
    }
}