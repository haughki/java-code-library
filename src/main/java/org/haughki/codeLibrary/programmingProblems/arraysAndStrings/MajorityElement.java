package org.haughki.codeLibrary.programmingProblems.arraysAndStrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityElement {
    /*
    Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
    You may assume that the array is non-empty and the majority element always exist in the array.
     */
    // Solution from: https://discuss.leetcode.com/topic/8692/o-n-time-o-1-space-fastest-solution
    // This is known as Boyer-Moore Majority Vote algorithm, http://goo.gl/64Nams.  It works for more than just
    // n/2, and remains O(n) time and O(1) space (see below)
    // - There will always be at least 1 more majority element than any other element
    // - Counting _down_ here feels like the trick
    // - My intuition that there must be a way to do this without any map or array was right (didn't figure it out).
    // - Again, the solution was simple math, just applying it in an insightful way
    // - "what if I just assigned 1 to every value in the list...?"
    // - Trying to manually solve some simple cases might have gotten me there
    public int majorityElement(int[] num) {

        int major=num[0], count = 1;
        for(int i=1; i<num.length;i++){
            if(count==0){
                count++;
                major=num[i];
            }else if(major==num[i]){
                count++;
            }else count--;

        }
        return major;
    }

    // https://leetcode.com/problems/majority-element-ii/
    // Return list of all elements > n/3
    public List<Integer> majorityElementII(int[] nums) {
        if (nums == null || nums.length == 0)
            return new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        int number1 = nums[0], number2 = nums[0], count1 = 0, count2 = 0, len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1)
                count1++;
            else if (nums[i] == number2)
                count2++;
            else if (count1 == 0) {
                number1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                number2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {   // O(2n) is still O(n)
            if (nums[i] == number1)
                count1++;
            else if (nums[i] == number2)
                count2++;
        }
        if (count1 > len / 3)
            result.add(number1);
        if (count2 > len / 3)
            result.add(number2);
        return result;
    }
    
    // My original solution
    public int myMajorityElement(int[] nums) {
        Map<Integer, Integer> rec = new HashMap<>();
        int maj = nums[0];
        for (int i = 0; i < nums.length; i++) {
            Integer count = rec.get(nums[i]);
            if (count == null)
                rec.put(nums[i], 1);
            else {
                if (++count > nums.length / 2)
                    return nums[i];
                rec.put(nums[i], count);
                int majCount = rec.get(maj);

                maj = count > majCount ? nums[i] : maj;
            }
        }
        return maj;
    }
}
