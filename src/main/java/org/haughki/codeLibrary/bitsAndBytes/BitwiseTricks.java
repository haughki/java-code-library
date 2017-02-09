package org.haughki.codeLibrary.bitsAndBytes;

public class BitwiseTricks {
    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            System.out.println(multiplyByTwo(4, i));
        }
    }
    
    public static int multiplyByTwo(int input, int raise) {
        return input << raise;
    }

    
    // finds the unique number in an array of duplicates with only one number unique.  XOR'ing duplicates, 
    // in any order, results in 0:  1 ^ 1 = 0, 0 ^ 0 = 0. 
    public static int xorNumbers(int[] nums) {
        int result = 0;  // this init is important:  0 ^ n = n.
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }
}
