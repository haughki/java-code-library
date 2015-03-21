package org.haughki.codeLibrary.threading;

public class Deadlock {
    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        // Thread one enters and locks "this" for alphonse.  Thread two enters (at ~ the same time) and does the same
        // for gaston.  Both have now locked "this" for their respective objects.
        public synchronized void bow(Friend beingBowedTo) {  // locks "this" for the length of the method -- doesn't "lock the method"
            System.out.format("%s: I am bowing to %s!%n", this.name, beingBowedTo.getName());
            // Now, each tries to bow back to the other.  So, to enter bowBack() each thread must try to obtain a lock on
            // the other object, which neither can do because the other thread hasn't released the first "this" lock yet.
            beingBowedTo.bowBack(this);
        }

        public synchronized void bowBack(Friend beingBowedBackTo) {
            System.out.format("%s: I am bowing back to %s!%n", this.name, beingBowedBackTo.getName());
        }
    }

    public static void main(String[] args) {
        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");
        new Thread(new Runnable() {
            public void run() {
                alphonse.bow(gaston);
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                gaston.bow(alphonse);
            }
        }).start();

        System.out.println("Main exiting...");
    }
}