package org.haughki.codeLibrary.algorithm;

// taken from:  https://examples.javacodegeeks.com/core-java/quicksort-algorithm-in-java-code-example/
public class QuickSort {

    private static int []a;



    public static void main(String[] args) {
        a = Common.createArray(10);
        Common.printArray(a);
        sort();
        System.out.println();
        Common.printArray(a);
    }
    
    public static void setA(int[] array){
        a = array;
    }
    
    public static void sort() {
        int left = 0;
        int right = a.length-1;
        quickSort(left, right);
    }

    // This method is used to sort the array using the quicksort algorithm.
    // It takes the left and the right end of the array as the two cursors.
    private static void quickSort(int leftStart, int rightStart){
        // If both cursor scanned the complete array quicksort returns
        if(leftStart >= rightStart)
            return;

        // For the simplicity, we took the right most item of the array as a pivot 
        int pivot = a[rightStart];
        int divideIndex = grossSort(leftStart, rightStart, pivot);

        // Recursively, calls the grossSort with increasingly smaller chunks of the array
        quickSort(0, divideIndex - 1);
        quickSort(divideIndex + 1, rightStart);
    }

    // This method is used to grossSort the given array and returns the integer which points to the _sorted_ pivot index
    private static int grossSort(int leftStart, int rightStart, int pivot){
        int leftIndex = leftStart - 1;
        int rightIndex = rightStart;
        while(true) {
            while(a[++leftIndex] < pivot);  // move the cursor right until you find an element greater than the pivot
            while(rightIndex > 0 && a[--rightIndex] > pivot);  // move the cursor left until you find an element less than the pivot
            if(leftIndex >= rightIndex){  // we've moved through the whole array and grouped everything re the pivot
                break;
            }else{
                swap(leftIndex, rightIndex); // swap the greater than pivot (left) with the less than pivot (right)
            }
        }
        swap(leftIndex, rightStart);  // move the pivot to it's new position
        return leftIndex;
    }

    // Swaps the values between the two given indexes
    private static void swap(int left, int right){
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }
    
}
