package org.haughki.codeLibrary.threading.producerConsumer;

import java.util.Random;

public class Consumer implements Runnable {
    private final Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        for (String message = drop.take(); !message.equals("DONE"); message = drop.take()) {
            System.out.format("MESSAGE RECEIVED: %s%n", message);
            try {
                Thread.sleep(random.nextInt(4000));
            } catch (InterruptedException e) {
                System.out.println("Consumer Interrupted! " + e.toString());
            }
        }
        System.out.println("Done.");
    }
}