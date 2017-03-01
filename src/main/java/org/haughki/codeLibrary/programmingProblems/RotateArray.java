package org.haughki.codeLibrary.programmingProblems;

import org.haughki.codeLibrary.bitsAndBytes.BitVector;

import java.util.Arrays;

/*
https://leetcode.com/problems/rotate-array/?tab=Description
Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 */
public class RotateArray {
    public static void main(String[] args) {
        // SEE TESTS
//        Rotate r = new Rotate();
//        int[] a = {1,2,3,4,5,6,7};
//        r.rotate(a, 4);
//        System.out.println();
//        Arrays.stream(a).forEach(System.out::print);
    }
}

class Rotate {

    // Top solution from leetcode:  https://discuss.leetcode.com/topic/14341/easy-to-read-java-solution
    // NOTE:  very similar to ReverseString!  O(n) totally in place.
    public void rotateReverse(int[] nums, int k) {
//    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    
    /*
    Accepted by leetcode.  
    Start at 0 as source, hold target, move source to target.  Keep doing this until done, tracking each moved number
    with a bit vector.  If you hit a number you've already moved, search for a new starting point.  Everything in place, 
    one temp var.  Use bit vector to track when you've moved a number.
     */
    public void rotateOneByOne(int[] nums, int k) {
//    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0)
            return;
        BitVector moved = new BitVector(nums.length);
        int numMoved = 0, i = 0, n = nums[0], t;
        while (numMoved < nums.length) {
            if (!moved.get(i)) {
                int shift = i + k;
                if (shift > nums.length - 1)
                    shift = shift - nums.length;
                t = nums[shift];
                nums[shift] = n;
                moved.set(i, true);
                n = t;
                numMoved++;
                i = shift;
            } else {
                while (moved.get(i))
                    i++;
                n = nums[i];
            }
        }
    }

    /*
    Accepted by leetcode.  O(n).  Not in place.
     */
    public void rotateArrayCopy(int[] nums, int k) {
//    public void rotate(int[] nums, int k) {
        k = k % nums.length;

        int[] copy = Arrays.copyOf(nums, nums.length);

        for (int i = 0; i < nums.length; i++) {
            int shift = i - k;
            if (shift < 0)
                nums[i] = copy[nums.length + shift];
            else
                nums[i] = copy[shift];
        }
    }

    /*
    Too slow for leetcode.  Can't figure out why: seems to run fine in my testing.  Not sure of time complexity here. 
    Second attempt.  Uses temp array to store only k numbers; but, k could equal n - 1. Moves
    "non-k" numbers in place, while storing k.  Then copies k back to nums.
     */
//    public void rotateTempK(int[] nums, int k) {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;

        int[] temp = new int[k];
        int tCount = k - 1, move = nums.length - k, moved = 0;
        int i = nums.length - 1;
        while (i >= nums.length - k) {
            temp[tCount] = nums[i];
            tCount--;
            if (moved < move) {
                nums[i] = nums[i - k];
                moved++;
            }
            i--;
        }
        while (moved < move) {
            nums[i] = nums[i - k];
            i--;
            moved++;
        }

        for (int j = 0; j < k; j++) {
            nums[j] = temp[j];
        }
    }

    // Too slow.  First attempt. O(k * n), sortof O(n^2).  Brute force.
    public void rotateBruteForce(int[] nums, int k) {
//    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int t;
        while (k > 0) {
            t = nums[nums.length - 1];
            for (int i = nums.length - 2; i > -1; i--) {
                nums[i + 1] = nums[i];
            }
            nums[0] = t;
            k--;
        }
    }

}
