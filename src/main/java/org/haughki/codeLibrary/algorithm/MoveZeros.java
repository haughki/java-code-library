package org.haughki.codeLibrary.algorithm;

import java.util.Arrays;

public class MoveZeros {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        MoveZeros mz = new MoveZeros();
        mz.moveZeroes(nums);
        Arrays.stream(nums).forEach(i -> System.out.print(i + ", "));
    }
    
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
