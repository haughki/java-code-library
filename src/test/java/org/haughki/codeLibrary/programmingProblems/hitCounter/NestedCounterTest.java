package org.haughki.codeLibrary.programmingProblems.hitCounter;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class NestedCounterTest {
    @Test
    public void getHits5ms() throws Exception {
        NestedCounter c = new NestedCounter(4, new TheSource());
        long start = SingleThreadedCounter.currentSecs();
        long current = start;
        while (current - start < 2) {
            c.hit();
            //System.out.println(c.getHits());
            Thread.sleep(5);
            current = SingleThreadedCounter.currentSecs();
        }
        System.out.println("hits: " + c.getHits());
        Assert.assertTrue(100 < c.getHits());
    }

    @Test
    public void getHits250ms() throws Exception {
        NestedCounter c = new NestedCounter(4, new TheSource());
        long start = SingleThreadedCounter.currentSecs();
        long current = start;
        while (current - start < 5) {
            c.hit();
            Thread.sleep(250);
            current = SingleThreadedCounter.currentSecs();
            System.out.println("hits @ time: " + (current - start) + " - " + c.getHits());
        }
        System.out.println("hits: " + c.getHits());
//        Assert.assertTrue(17 > c.getHits());
//        Assert.assertTrue(13 < c.getHits());
    }

    @Test
    public void getHits2s() throws Exception {
        NestedCounter c = new NestedCounter(4, new TheSource());
        long start = SingleThreadedCounter.currentSecs();
        long current = start;
        int count = 0;
        while (current - start < 8) {
            c.hit();
            count++;
            System.out.println(c.getHits());
            Thread.sleep(2000);
            current = SingleThreadedCounter.currentSecs();
        }
        System.out.println("count: " + count);
        System.out.println("hits: " + c.getHits());
//        Assert.assertTrue(17 > c.getHits());
//        Assert.assertTrue(13 < c.getHits());
    }
}