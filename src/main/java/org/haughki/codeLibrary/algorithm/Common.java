package org.haughki.codeLibrary.algorithm;

import java.util.Arrays;

public class Common {
    public static int[] createArray(int size) {
        int[] array = new int[size];
        int item;
        for(int i=0;i<size;i++){
            item = (int)(Math.random()*100);
            array[i] = item;
        }
        return array;
    }
    public static void printArray(int[] array) {
        Arrays.stream(array).forEach(value -> System.out.print(value + " "));
    }
}
