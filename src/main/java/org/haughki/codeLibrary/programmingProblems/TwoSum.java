package org.haughki.codeLibrary.programmingProblems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
