package org.haughki.codeLibrary.algorithm;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearch {
    @Test
    public void testEvenNumberOfElements() {
        int[] a = {2,6,7,13,22,28,37,52};

        Assert.assertEquals(7, findIndex(52, a, 0, a.length - 1));
        Assert.assertEquals(0, findIndex(2, a, 0, a.length - 1));
        Assert.assertEquals(3, findIndex(13, a, 0, a.length - 1));
        Assert.assertEquals(4, findIndex(22, a, 0, a.length - 1));
        Assert.assertEquals(-1, findIndex(1, a, 0, a.length - 1));
        Assert.assertEquals(-1, findIndex(3, a, 0, a.length - 1));
        Assert.assertEquals(-1, findIndex(9, a, 0, a.length - 1));
        Assert.assertEquals(-1, findIndex(15, a, 0, a.length - 1));
        Assert.assertEquals(-1, findIndex(25, a, 0, a.length - 1));
        Assert.assertEquals(-1, findIndex(53, a, 0, a.length - 1));
    }

    @Test
    public void testOddNumberOfElements() {
        int[] a = {2,6,7,13,22,28,37,52,67};

        Assert.assertEquals(8, findIndex(67, a, 0, a.length - 1));
        Assert.assertEquals(0, findIndex(2, a, 0, a.length - 1));
        Assert.assertEquals(3, findIndex(13, a, 0, a.length - 1));
        Assert.assertEquals(4, findIndex(22, a, 0, a.length - 1));
        Assert.assertEquals(5, findIndex(28, a, 0, a.length - 1));
        Assert.assertEquals(-1, findIndex(1, a, 0, a.length - 1));
        Assert.assertEquals(-1, findIndex(3, a, 0, a.length - 1));
        Assert.assertEquals(-1, findIndex(9, a, 0, a.length - 1));
        Assert.assertEquals(-1, findIndex(15, a, 0, a.length - 1));
        Assert.assertEquals(-1, findIndex(25, a, 0, a.length - 1));
        Assert.assertEquals(-1, findIndex(30, a, 0, a.length - 1));
        Assert.assertEquals(-1, findIndex(68, a, 0, a.length - 1));
    }

    @Test
    public void testZeroLengthArray() {
        int[] a = {};

        Assert.assertEquals(-1, binarySearch(3, a));
    }

    @Test
    public void testArrayLengthOne() {
        int[] a = {1};

        Assert.assertEquals(0, binarySearch(1, a));
        Assert.assertEquals(-1, binarySearch(3, a));
    }

    @Test
    public void testArrayLengthTwo() {
        int[] a = {1,5};

        Assert.assertEquals(0, binarySearch(1, a));
        Assert.assertEquals(1, binarySearch(5, a));
        Assert.assertEquals(-2, binarySearch(3, a));
        Assert.assertEquals(-1, binarySearch(-1, a));
    }

    public static int binarySearch(int target, int[] sorted) {
        if (sorted.length < 1)
            return -1;
        
        return findIndex(target, sorted, 0, sorted.length - 1);
    }
    
    // assumes a.length > 0
    private static int findIndex(int target, int[] a, int start, int end) {
        // difference here will always be one
        if (start > end)
            return -(start + 1);  // the index where it would have been, plus one, as a negative number 
        
        int mid = start + ((end - start) / 2);

        if (a[mid] == target) {
            return mid;
        } else if (target < a[mid]) {
            return findIndex(target, a, start, mid - 1);
        } else {
            return findIndex(target, a, mid + 1, end);
        }
    }
}
