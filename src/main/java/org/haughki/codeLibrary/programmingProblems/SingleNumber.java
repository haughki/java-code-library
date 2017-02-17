package org.haughki.codeLibrary.programmingProblems;

import java.util.HashSet;
import java.util.Set;

/*
Find the unique number in the list (array) of numbers.
 */
public class SingleNumber {
    public static void main(String[] args) {
        int[] a = {5, 8, -1, 8, 4, -1, 5};
        System.out.println(singleNumber2(a));
    }
    private static int singleNumber(int[] nums) {
        Set<Integer> previous = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (previous.contains(nums[i]))
                previous.remove(nums[i]);
            else
                previous.add(nums[i]);    
        }
        
        return previous.iterator().next();
    }

    // finds the unique number in an array of duplicates with only one number unique.  XOR'ing duplicates, 
    // in any order, results in 0:  1 ^ 1 = 0, 0 ^ 0 = 0. 
    private static int singleNumber2(int[] nums) {
        int result = 0;  // this init is important:  0 ^ n = n.
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        
        return result;
    }
}
