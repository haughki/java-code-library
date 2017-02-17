package org.haughki.codeLibrary.programmingProblems.dynamicMemoized;

/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */
public class MaximumSubarray {
    public static void main(String[] args) {
        int[] a = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        MaxSub m = new MaxSub();
        //System.out.println(m.maxSubArray(a));
        System.out.println(m.maxSubArray2(a));

    }
}

class MaxSub {
    /*
    O(n) version from leetcode. I think I just needed to "see" this solution.
     - Start a new subarray if the previous sum up to i is <= to i
     - (Said another way) If the sum of the subarray up to this point is <= to the current value at i, start a new subarray
     */
    public static int maxSubArray2(int[] A) {
        int maxSoFar = A[0], maxEndingHere = A[0];
        for (int i=1; i < A.length; ++i){
            maxEndingHere = Math.max(maxEndingHere + A[i], A[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    /*
    My original attempt.  I knew n^2 wouldn't be fast enough, but just couldn't get any further with it.  Tried
    to work through recursive/memoized versions, but wasn't able to get anywhere.
     */
    public int maxSubArray(int[] nums) {
        int greatest = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int total = nums[i];
            if (total > greatest)
                greatest = total;
            for (int j = i + 1; j < nums.length; j++) {
                total += nums[j];
                if (total > greatest)
                    greatest = total;
            }
        }
        return greatest;
    }

}