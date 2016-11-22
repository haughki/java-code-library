package org.haughki.codeLibrary.algorithm;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSortTest {
    @Test
    public void sort() throws Exception {
        int numRuns = 20;
        for (int j = 0; j < numRuns; j++) {
            int size = 210;
            int[] array = new int[size];
            int item;
            for(int i=0; i<size; i++){
                item = (int)(Math.random()*100);
                array[i] = item;
            }

            QuickSort.setA(array);
//        QuickSort.printArray();
//        System.out.println();
            QuickSort.sort();
//        QuickSort.printArray();
//        System.out.println();

            for (int i = 0; i < array.length; i++) {
                if ((i + 1) < array.length) {
                    //System.out.println(String.valueOf(array[i]) + ":" + String.valueOf(array[i + 1]));
                    Assert.assertTrue(array[i] <= (array[i + 1]));
                } else
                    break;
            }
        }
    }

}