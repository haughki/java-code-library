package org.haughki.codeLibrary.programmingProblems;

import java.util.Arrays;

/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */
public class MoveZeros {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        MoveZeros mz = new MoveZeros();
        mz.moveZeroes(nums);
        Arrays.stream(nums).forEach(i -> System.out.print(i + ", "));
    }
    
    // Each time you find a 0, add 1 to the number of places you need to bump every following non-zero number down.
    public void moveZeroes(int[] nums) {
        int moveSteps = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                moveSteps++;
                continue;
            } else if (moveSteps > 0) {
                nums[i - moveSteps] = nums[i];
                nums[i] = 0;
            }
        }
    }
}
