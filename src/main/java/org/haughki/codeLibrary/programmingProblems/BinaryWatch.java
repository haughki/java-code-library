package org.haughki.codeLibrary.programmingProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/binary-watch/
A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */
public class BinaryWatch {
    public static void main(String[] args) {
        BinWatchSolution bws = new BinWatchSolution();
        List<String> valid = bws.readBinaryWatch(0);
        valid.forEach(System.out::println);
    }
}

class BinWatchSolution {
    final static private int[] ON_VALUES = {-8, -4, -2, -1, 32, 16, 8, 4, 2, 1};
    private List<int[]> combos = new ArrayList<>();
    private int comboIndex = -1;
    private int numberOn;
    public List<String> readBinaryWatch(int numberOn) {
        this.numberOn = numberOn;
        down(new int[this.numberOn], 0, this.numberOn - 1);
        List<String> validTimes = new ArrayList<>();
        combos.forEach(c -> {
            int hours = 0;
            int mins = 0;
            for (int i = 0; i < c.length; i++) {
                int curr = c[i];
                //System.out.print(curr + " ");
                if (curr > 0)
                    mins += curr;
                else
                    hours += Math.abs(curr);
            }
            if (hours < 13 && mins < 60) {
                //System.out.print(String.format("%d:%02d", hours, mins));
                validTimes.add(String.format("%d:%02d", hours, mins));
            }

            //System.out.println();
        });
        return validTimes;
    }


    private void down(int[] combo, int start, int stop) {
        if (stop < 0) {
            combos.add(Arrays.copyOf(combo, this.numberOn));
            return;
        }
        comboIndex++;
        for (int i = start; i < ON_VALUES.length - stop; i++) {
            combo[comboIndex] = ON_VALUES[i];
            down(combo, i + 1, stop - 1);
        }
        comboIndex--;
    }
}


