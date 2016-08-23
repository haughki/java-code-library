package org.haughki.codeLibrary.algorithm;

// taken from:  https://examples.javacodegeeks.com/core-java/quicksort-algorithm-in-java-code-example/
public class Various {

    private static int [] integers;

    // Initialize array
    static {
        int size=20;
        int []array = new int[size];
        int item = 0;
        for(int i=0;i<size;i++){
            array[i] = (int)(Math.random()*100);
        }
        integers = array;
        //integers = new int[]{55, 39, 4, 10, 56, 65, 89, 31, 25, 63, 3, 7, 96, 52, 27, 22, 11, 60, 43, 87};
    }

    public static void main(String[] args) {
        
        System.out.println(findMax());
        System.out.println(findMax2());
        printArray();
    }

    private static int findMax(){
        int maxIndex = 0;
        for (int i = 0; i < integers.length; i++) {
            if (i + 1 < integers.length) {
                if (integers[maxIndex] < integers[i + 1])
                    maxIndex = i + 1;
            }
        }
        return integers[maxIndex];
    }

    private static int findMax2(){
        int max = 0;
        for (int i: integers) {
            if (i > max)
                max = i;
        }
        return max;
    }

    public static void printArray(){
        for(int i : integers){
            System.out.print(i+" ");
        }
    }
}
