package org.haughki.codeLibrary.programmingProblems;

import java.sql.Time;
import java.util.*;

class NestedCounter {
    class MyTimeSlot {
        public MyTimeSlot(long t) {
            time = t;
            hits = 1;

        }
        private long time;
        private long hits;
    }
    
    private Deque<MyTimeSlot> record = new LinkedList<>();
    private long hits = 0L;
    private int SLOTS = 5;
    public void hit() { // time in seconds
        
        long currentTime = System.currentTimeMillis()/1000;
        if (record.size() > 0) {
            MyTimeSlot first = record.getFirst();
            if (first.time == currentTime)
                first.hits++;
            else 
                record.addFirst(new MyTimeSlot(currentTime));
        } else
            record.addFirst(new MyTimeSlot(currentTime));
        
        cleanUp(currentTime);
        System.out.println("size: " + record.size());
        hits++;
    }

    public Long getHits() {
        cleanUp(System.currentTimeMillis()/1000);
        return hits;
    }

    private void cleanUp(long time) {
        while(record.size() > SLOTS) {
            MyTimeSlot removing = record.removeLast();
            hits -= removing.hits;
        }
    }

    private int calcSlot() {
        return (int)((System.currentTimeMillis()/1000) % SLOTS);
    }
}

public class LongHitCounter {
    public static void main(String[] args) throws InterruptedException {
        NestedCounter c = new NestedCounter();
        long start = System.currentTimeMillis()/1000;
        long current = start;
        while (current - start < 20) {
            c.hit();
            System.out.println(c.getHits());
            Thread.sleep(250);
            current = System.currentTimeMillis()/1000;
        }
    }
}