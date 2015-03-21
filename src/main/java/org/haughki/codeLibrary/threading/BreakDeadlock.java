package org.haughki.codeLibrary.threading;


public class BreakDeadlock {
    static class Friend {
        private final String name;
        private final Object bowLock = new Object();
        private final Object bowBackLock = new Object();

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        // Breaks the deadlock by using separate objects for each lock instead of locking "this"
        public void bow(Friend beingBowedTo) {
            synchronized (bowLock) {
                System.out.format("%s: I am bowing to %s!%n", this.name, beingBowedTo.getName());
                beingBowedTo.bowBack(this);
            }
        }

        public void bowBack(Friend beingBowedBackTo) {
            synchronized (bowBackLock) {
                System.out.format("%s: I am bowing back to %s!%n", this.name, beingBowedBackTo.getName());
            }
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
    }
}