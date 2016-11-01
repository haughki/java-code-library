package org.haughki.codeLibrary.algorithm;

// taken from:  https://examples.javacodegeeks.com/core-java/quicksort-algorithm-in-java-code-example/
public class QuickSort {

    private static int []a;

    // Initialize array
    static {
        int size=10;
        int []array = new int[size];
        int item;
        for(int i=0;i<size;i++){
            item = (int)(Math.random()*100);
            array[i] = item;
        }
        a = array;
        a = new int[]{30, 67, 38, 23, 60, 72, 86, 22, 71, 94};
    }

    public static void main(String[] args) {
        printArray();
        sort();
        System.out.println();
        printArray();
    }

    // This method sorts an array and internally calls quickSort 
    public static void sort(){
        int left = 0;
        int right = a.length-1;
        quickSort(left, right);
    }

    // This method is used to sort the array using quicksort algorithm.
    // It takes the left and the right end of the array as the two cursors.
    private static void quickSort(int left,int right){
        // If both cursor scanned the complete array quicksort exits
        if(left >= right)
            return;

        // For the simplicity, we took the right most item of the array as a pivot 
        int pivot = a[right];
        int partition = partition(left, right, pivot);

        // Recursively, calls the quicksort with the different left and right parameters of the sub-array
        quickSort(0, partition-1);
        quickSort(partition+1, right);
    }

    // This method is used to partition the given array and returns the integer which points to the _sorted_ pivot index
    private static int partition(int left,int right,int pivot){
        int leftCursor = left - 1;
        int rightCursor = right;
        while(leftCursor < rightCursor){
            while(a[++leftCursor] < pivot);
            while(rightCursor > 0 && a[--rightCursor] > pivot);
            if(leftCursor >= rightCursor){
                break;
            }else{
                swap(leftCursor, rightCursor);
            }
        }
        swap(leftCursor, right);  // move the pivot to it's new position
        return leftCursor;
    }

    // Swaps the values between the two given indexes
    private static void swap(int left, int right){
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }

    private static void printArray(){
        for(int i : a){
            System.out.print(i+" ");
        }
    }
}
