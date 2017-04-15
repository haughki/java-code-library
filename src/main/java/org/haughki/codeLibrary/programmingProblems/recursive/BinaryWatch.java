package org.haughki.codeLibrary.programmingProblems.recursive;

import java.util.ArrayList;
import java.util.List;

/*
3/29
https://leetcode.com/problems/binary-watch/

see also:  recursive.OrderPermutationWithLimit

A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the 
minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the 
watch could represent.

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
        List<String> valid = bws.readBinaryWatch(4);
        valid.forEach(System.out::println);
    }
}

class HoursMins {
    HoursMins(byte hours, byte mins) {this.hours = hours; this.mins = mins;}
    public byte hours = 0;
    public byte mins = 0;
}

class BinWatchSolution {
    final static private byte[] ON_VALUES = {-8, -4, -2, -1, 32, 16, 8, 4, 2, 1};
    //final static private byte[] ON_VALUES = {-2, -1, 32, 16};
    private List<HoursMins> combos = new ArrayList<>();

    public List<String> readBinaryWatch(int numberOn) {
        recurse((byte)0, (byte)0, 0, numberOn - 1);
        
        List<String> validTimes = new ArrayList<>();
        combos.forEach(c -> {
            if (c.hours < 12 && c.mins < 60) {
                //System.out.print(String.format("%d:%02d", hours, mins));
                validTimes.add(String.format("%d:%02d", c.hours, c.mins));
            }

            //System.out.println();
        });
        return validTimes;
    }

    private void recurse(byte hours, byte mins, int start, int stop) {
        if (stop < 0) {
            combos.add(new HoursMins(hours, mins));
            return;
        }
        for (int i = start; i < ON_VALUES.length - stop; i++) {
            byte val = ON_VALUES[i];
            //System.out.println(val);
            byte newHours = hours, newMins = mins;
            if (val > 0)
                newMins += val;
            else
                newHours += Math.abs(val);

            recurse(newHours, newMins, i + 1, stop - 1);
        }
    }
}


