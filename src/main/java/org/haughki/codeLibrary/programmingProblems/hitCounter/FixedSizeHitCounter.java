package org.haughki.codeLibrary.programmingProblems.hitCounter;

import java.util.Timer;
import java.util.TimerTask;

/*
This idea hasn't quite panned out.  The idea is to have a "circular" array of the number of seconds you are looking at
(e.g., last five minutes == 300 seconds).  Each time a hit comes in, record it in the total, and add it to the correct
slot. Each time you move to a new slot, remove all of the items there (and that number from the total hits) and start 
again.  Problem is, what if you get no hits for 10 mins?  You need a separate thread/event timer to check each second
and remove old hits.  If you do that, then you need to coordinate the timing of that (maybe have an extra, dummy slot
so that your "remove thread" is always one ahead?)
 */
class InternalCounter {

    class CounterManager extends TimerTask {
        private final InternalCounter counter;
        CounterManager(InternalCounter counter) {
            this.counter = counter;
        }
        
        @Override
        public void run() {
            //counter.setSlotAndTidy(this.scheduledExecutionTime()/1000); // when run was called, in secs since epoch
            counter.setSlotAndTidy(System.currentTimeMillis()/1000); // when run was called, in secs since epoch
        }
    }

    private synchronized void setSlotAndTidy(long timerSecs) {
        currentSlot = calcSlot(timerSecs);
        int nextSlot = currentSlot + 1 >= WINDOW ? 0 : currentSlot + 1;
        System.out.println("set slot: " + currentSlot + " -> " + nextSlot);
        int nextHits = record[nextSlot];
        record[nextSlot] = 0;
        hits -= nextHits;
    }

    private int WINDOW = 5;  // in seconds
    private long hits = 0;
    private int[] record = new int[WINDOW];
    private int currentSlot;
    InternalCounter() {
        currentSlot = calcSlot(System.currentTimeMillis()/1000);
        System.out.println("init slot: " + currentSlot);
    }

    synchronized void hit() {
        hits++;
        record[currentSlot]++;
    }

    private int calcSlot(long secsSinceEpoch) {
        return (int)(secsSinceEpoch % WINDOW);
    }

    long getHits() {
        return hits;
    }
}



public class FixedSizeHitCounter {
    
    public static void main(String[] args) throws InterruptedException {
        InternalCounter c = new InternalCounter();
        Timer t = new Timer(true);
        t.schedule(c.new CounterManager(c), 0, 1000);

        long start = SingleThreadedCounter.currentSecs();
        long current = start;
        while (current - start < 10) {
            c.hit();
            Thread.sleep(250);
            current = SingleThreadedCounter.currentSecs();
            System.out.println("hits @ time: " + (current - start) + " - " + c.getHits());
        }
        System.out.println("hits: " + c.getHits());
    }
}
