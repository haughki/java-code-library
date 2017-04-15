package org.haughki.codeLibrary.algorithm;


import java.util.Arrays;

// From:  http://www.java2novice.com/java-sorting-algorithms/insertion-sort/
public class InsertionSort {

    public static void main(String a[]){
        int[] actual = {10,34,2,56,7,67,88,42};
        doInsertionSort(actual);
        for(int i:actual){
            System.out.print(i);
            System.out.print(" ");
        }

        int[] expected = {2, 7, 10, 34, 42, 56, 67, 88};
        if (!Arrays.equals(expected, actual))
            throw new AssertionError();
    }

    private static void doInsertionSort(int[] input){

        int temp;
        for (int i = 1; i < input.length; i++) {
            for(int j = i; j > 0; j--){
                if(input[j] < input[j-1]){
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                } else
                    break;
            }
        }
    }
}
