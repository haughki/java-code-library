package org.haughki.codeLibrary.programmingProblems.arraysAndStrings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] source = {3,2,4};
        int target = 6;
        //int[] found = twoSum(source, target);
        int[] found = twoSum(source, target);
        Arrays.stream(found).forEach(System.out::print);
    }

    private static int[] twoSum(int[] source, int target) {
        Map<Integer, Integer> record = new HashMap<>();
        int[] ret = new int[2];
        for (int i = 0; i < source.length; i++) {
            int current = source[i];
            Integer previous = record.get(current);
            if (previous != null) {
                ret[0] = previous;
                ret[1] = i;
                return ret;
            } else
                record.put(target - current,i);
        }
        return ret;
    }
 }
