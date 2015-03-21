package org.haughki.codeLibrary.threading.producerConsumer;

import java.util.Random;

public class Producer implements Runnable {
    private final Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };
        Random random = new Random();

        for (int i = 0; i < importantInfo.length; i++) {
            drop.put(importantInfo[i]);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Producer Interrupted! " + e.toString());
            }
        }
        drop.put("DONE");
    }
}
