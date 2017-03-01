package org.haughki.codeLibrary.bitsAndBytes;

import static org.haughki.codeLibrary.bitsAndBytes.Bytes.print32Bits;

/*
A simple bit vector to hold true/false for a-z.
See  \programmingProblems\crackingCoding\PalindromePermutation.java
 */
public class A_ZBitVector {
    public static void main(String[] args) {
        print32Bits(Character.getNumericValue('a'), "10");
        print32Bits(Character.getNumericValue('z'), "35");
        int vector = toggle(0, Character.getNumericValue('a'));
        vector = toggle(vector, Character.getNumericValue('z'));
        print32Bits(vector, "vector");

    }
    private static final int offset = Character.getNumericValue('a');
    private static int toggle(int vector, int index) {
        index = index - offset;  // start the index at 0
        if (index < 0) return vector;
        int mask = 1 << index;  // shift '1' left by the number of the index
        print32Bits(mask, "mask for " + index);
        if ((vector & mask) == 0)  // bit not set
            vector |= mask;  // set it to 1
        else
            vector &= ~mask;  // set it to 0
        return vector;
    }
}
