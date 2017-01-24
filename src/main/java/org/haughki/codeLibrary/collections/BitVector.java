package org.haughki.codeLibrary.collections;

import java.util.BitSet;

public class BitVector {
    public static void main(String[] args) {
        // use a bit set to track the "on" coordinates of a left-to-right diagonal in a matrix
        // here I just set them all to "on", just to see if it works
        int size = 5;
        int numDiags = (size - 1) * 2 + 1;
        BitSet b = new BitSet(numDiags);  
        int[][] m = new int[5][5];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.println(i + ", " + j);
                int leftRight = i - j;
                System.out.println(leftRight);
                
                if (leftRight < 0) // value might be negative; if so, subtract it from the max
                    b.set(numDiags + leftRight);
                else
                    b.set(leftRight);
            }
        }
        for (int i = 0; i < numDiags; i++) {
            System.out.println(b.get(i));
        }
    }
}
