package org.haughki.codeLibrary.programmingProblems.dynamicMemoized;

import java.util.Arrays;

/*
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and 
it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of 
money you can rob tonight without alerting the police.
 */
public class HouseRobber {
    public static void main(String[] args) {
        int[] nums = {2, 1, 0, 4, 6, 5, 1, 2, 3};
        //int[] nums = {2, 1, 0, 4, 6};
        Robber r = new Robber();
        System.out.println(r.recursiveRob(nums));
    }
}

class Robber {

    /*
    This is the editorial solution from leetcode.  My brain hurts.  Stunning.
     */
    public int rob(int[] num) {
        int prevMax = 0;
        int currMax = 0;
        for (int x : num) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;
    }
    
    /*
    This drives my attempts.  With my approach, you need to take the greatest from two passes, the first starting at i=0,
    the second starting at i=1.
     */
    public int recursiveRob(int[] nums) {
//        int greatest = recurse(nums, 0, 0, 0);
//        int greatestII = recurse(nums, 1, 0, 0);
//        return greatest > greatestII ? greatest : greatestII;

        int[] record = new int[nums.length];
        Arrays.fill(record, -1);
        int greatest = recurse2(nums, 0, record);
        int greatestII = recurse2(nums, 1, record);
        return Math.max(greatest, greatestII);
    }
    
    /*
    First attempt; driving code commented out above.  Submitted on leetcode, but the runtime was too long and it 
    didn't pass.  I think the algorithm is correct, only that the runtime (2^n?) is horrible.
     */
    private int recurse(int[] nums, int i, int total, int greatest) {
        if (i >= nums.length)
            return total > greatest ? total : greatest;
        total += nums[i];
        greatest = recurse(nums, i + 2, total, greatest);
        return recurse(nums, i + 3, total, greatest);
    }

    /*
    After much study, managed to get here.  Proud of this!  The trick is to calculate the max for a certain index and
    cache the result so you don't have to calc it again. Memoization!  It's no where near as elegant as leetcode's
    editorial solution, but it passes all the tests :)  Runtime is closer to n, but I do multiple passes, so it's at
    least 2n. (That's still n!).  The trick for me here was just clearly understanding how I could cache the last
    result at i = n - 1.  So, starting from the deepest recursion.
     */
    private int recurse2(int[] nums, int i, int[] record) {
        if (i >= nums.length)
            return 0;
        if (record[i] == -1) {
            int curr = nums[i];
            record[i] = Math.max(curr + recurse2(nums, i + 2, record), curr + recurse2(nums, i + 3, record));
        }
        return record[i];
    }

}