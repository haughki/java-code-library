package org.haughki.codeLibrary.programmingProblems.hitCounter;

import java.sql.Time;
import java.util.*;



interface TimeSource {
    long secs();
}

class TheSource implements TimeSource {
    @Override
    public long secs() {
        return System.currentTimeMillis()/1000;
    }
}

class NestedCounter {
    private Deque<TimeSlot> record = new LinkedList<>();
    private long hits = 0L;
    private final int window;
    private final TimeSource timeSource;
    private class TimeSlot {
        TimeSlot(long t) {
            time = t;
            hits = 1;
        }
        private long time;
        private long hits;
    }
    
    NestedCounter(final int window, TimeSource timeSource) {
        this.window = window;
        this.timeSource = timeSource;
    }
    
    public void hit() {
        long currentSecs = timeSource.secs();
        if (record.size() > 0) {
            TimeSlot first = record.getFirst();
            if (first.time == currentSecs)
                first.hits++;
            else 
                record.addFirst(new TimeSlot(currentSecs));
        } else
            record.addFirst(new TimeSlot(currentSecs));
        
        cleanUp(currentSecs);
        hits++;
    }

    public Long getHits() {
        cleanUp(timeSource.secs());
        return hits;
    }

    private void cleanUp(long time) {
        long edge = time - window;
        if (record.size() > 0) {
            TimeSlot removed;
            while (record.peekLast().time < edge) {
                removed = record.removeLast();
                hits -= removed.hits;
            }
        }

        System.out.println("size: " + record.size());
    }
}

public class SingleThreadedCounter {
    public static void main(String[] args) throws InterruptedException {
        NestedCounter c = new NestedCounter(5, new TheSource());
        long start = currentSecs();
        long current = start;
        while (current - start < 10) {
            c.hit();
            System.out.println(c.getHits());
            Thread.sleep(250);
            current = currentSecs();
        }
    }
    
    public static long currentSecs() {return System.currentTimeMillis()/1000;}
}