package org.haughki.codeLibrary.algorithm;

import org.haughki.codeLibrary.aacommon.Common;

public class BubbleSort {

    public static void main(String[] args) {
        int[] input = Common.createArray(10);
        Common.printArray(input);
        System.out.println();
        bubbleSort(input);
        Common.printArray(input);

    }

    private static void bubbleSort(int array[]) {
        for (int m = array.length; m >= 0; m--) {
            for (int i = 0; i < m - 1; i++) {
                if (array[i] > array[i + 1]) {
                    swapNumbers(i, i + 1, array);
                }
            }
        }
    }

    private static void swapNumbers(int i, int j, int[] array) {
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}