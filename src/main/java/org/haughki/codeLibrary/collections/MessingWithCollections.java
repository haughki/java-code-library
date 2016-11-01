package org.haughki.codeLibrary.collections;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.SimpleTimeZone;
import java.util.TreeMap;

public class MessingWithCollections {
    public static void main(String[] args) {
        //tryPriorityQueue();

        TreeMap<String, String> treeMap = new TreeMap<>();

        HashSet<String> hashSet = new HashSet<>();
        
    }

    private static void tryPriorityQueue() {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.add("carl");
        priorityQueue.add("john");
        priorityQueue.add("anders");
        priorityQueue.add("zed");
        priorityQueue.add("franky");

        priorityQueue.forEach(System.out::println);

        System.out.println();

        while(priorityQueue.peek() != null) {
            System.out.println(priorityQueue.poll());
        }
    }
}
