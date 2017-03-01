package org.haughki.codeLibrary.algorithm;

import org.haughki.codeLibrary.aacommon.Common;

public class MergeSort {

    private int[] array;
    private int[] temp;
    private int length;

    public static void main(String a[]){

        //int[] inputArr = Common.createArray(10);
        int[] inputArr = {61, 95, 32, 4, 21, 73, 31, 6, 27, 36};
        Common.printArray(inputArr);
        System.out.println();
        MergeSort ms = new MergeSort();
        ms.sort(inputArr);
        Common.printArray(inputArr);
    }

    public void sort(int inputArr[]) {
        this.array = inputArr;
        this.length = inputArr.length;
        this.temp = new int[length];
        splitIndices(0, length - 1);
    }

    private void splitIndices(int lowerIndex, int higherIndex) {
        if (lowerIndex < higherIndex) {
            int midIndex = lowerIndex + (higherIndex - lowerIndex) / 2;  // order of operations!
            
            splitIndices(lowerIndex, midIndex);  // the left side of the array
            splitIndices(midIndex + 1, higherIndex);  // the right side of the array
            
            // Now merge both sides
            sortAndMergeHalves(lowerIndex, midIndex, higherIndex);
        }
    }

    // Keep in mind that this "merge" is either a) just sorting two elements (smallest chunk) or b) merging two groups
    // of numbers which have already been sorted.  So, think of it as a "sorted merge" -- this is what makes it possible
    // to do this final sorting step linearly:  we're just walking the two already-sorted sub-chunks and interleaving
    // them into the correct order.
    private void sortAndMergeHalves(int lowIndex, int midIndex, int highIndex) {
        for (int i = lowIndex; i <= highIndex; i++) {
            temp[i] = array[i];
        }
        int tLow = lowIndex;  // t for temp
        int tMid = midIndex + 1;
        int a = lowIndex;  // a for The Array
        // low       mid          high
        // tLow-------->tMid--------->
        while (tLow <= midIndex && tMid <= highIndex) {  // so long as neither tLow nor tMid has incremented out
            if (temp[tLow] <= temp[tMid]) {
                array[a] = temp[tLow];
                tLow++;
            } else {
                array[a] = temp[tMid];
                tMid++;
            }
            a++;
        }
        // if tLow hasn't incremented out, it means that there are still some items on the left which are greater
        // than items which _were_ on the right.  So, just insert them in the ACTUAL array after the alreay-inserted
        // from the right.
        while (tLow <= midIndex) {
            array[a] = temp[tLow];
            a++;
            tLow++;
        }
    }
}