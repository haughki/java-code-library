package org.haughki.codeLibrary.programmingProblems;

/*
3/24 - got without looking
https://leetcode.com/problems/number-of-1-bits/#/description

Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).

For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
 */
public class NumberOf1Bits {
    public static void main(String[] args) {
        HammingWeight h = new HammingWeight();
        System.out.println(h.hammingWeight(11));
        System.out.println(h.hammingWeight(-1));
    }
}

class HammingWeight {
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1)
                count++;
            n = n >>> 1;  // use logical right-shift to avoid the two's complement 1's
        }
        return count;
    }
}