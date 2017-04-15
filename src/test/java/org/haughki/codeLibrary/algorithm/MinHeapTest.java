package org.haughki.codeLibrary.algorithm;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinHeapTest {
    @Test
    public void insert() throws Exception {
        MinHeap minHeap = new MinHeap();
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);

        System.out.println(minHeap.toString());
        System.out.println("The Min val is " + minHeap.min());
        
        minHeap.removeMin();
        System.out.println(minHeap.toString());
        System.out.println("The Min val is " + minHeap.min());

    }

}