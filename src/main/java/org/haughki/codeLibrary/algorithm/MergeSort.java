package org.haughki.codeLibrary.algorithm;

public class MergeSort {

    private int[] array;
    private int[] temp;
    private int length;

    public static void main(String a[]){

        int[] inputArr = Common.createArray(10);
        Common.printArray(inputArr);
        System.out.println();
        MergeSort mms = new MergeSort();
        mms.sort(inputArr);
        Common.printArray(inputArr);
    }

    public void sort(int inputArr[]) {
        this.array = inputArr;
        this.length = inputArr.length;
        this.temp = new int[length];
        mergeSort(0, length - 1);
    }

    private void mergeSort(int lowerIndex, int higherIndex) {
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // splits the left side of the array
            mergeSort(lowerIndex, middle);
            // splits the right side of the array
            mergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    private void mergeParts(int lowIndex, int middle, int highIndex) {

        for (int i = lowIndex; i <= highIndex; i++) {
            temp[i] = array[i];
        }
        int tLow = lowIndex;
        int tMid = middle + 1;
        int a = lowIndex;
        while (tLow <= middle && tMid <= highIndex) {
            if (temp[tLow] <= temp[tMid]) {
                array[a] = temp[tLow];
                tLow++;
            } else {
                array[a] = temp[tMid];
                tMid++;
            }
            a++;
        }
        while (tLow <= middle) {
            array[a] = temp[tLow];
            a++;
            tLow++;
        }
    }
}