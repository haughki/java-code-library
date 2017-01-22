package org.haughki.codeLibrary.programmingProblems;


/*
This idea hasn't quite panned out.  The idea is to have a "circular" array of the number of seconds you are looking at
(e.g., last five minutes == 300 seconds).  Each time a hit comes in, record it in the total, and add it to the correct
slot. Each time you move to a new slot, remove all of the items there (and that number from the total hits) and start 
again.  Problem is, what if you get no hits for 10 mins?  You need a separate thread/event timer to check each second
and remove old hits.  If you do that, then you need to coordinate the timing of that (maybe have an extra, dummy slot
so that your "remove thread" is always one ahead?)
 */
class InternalCounter {

    int WINDOW = 300;
    long hits;
    int[] record = new int[WINDOW];
    int currentSlot;
    InternalCounter() {
        hits = 0;
        currentSlot = calcSlot(); 
    }
    void hit() {
        hits++;
        int slot = calcSlot();
        record[slot]++;
    }

    private int calcSlot() {
        return (int)((System.currentTimeMillis()/1000) % WINDOW);
    }

    long getHits() {
        return hits;
    }
}

public class HitCounter2 {
    
    public static void main(String[] args) throws InterruptedException {
        InternalCounter c = new InternalCounter();
        while (true) {
            c.hit();
            Thread.currentThread().sleep(250);
        }
    }
}
