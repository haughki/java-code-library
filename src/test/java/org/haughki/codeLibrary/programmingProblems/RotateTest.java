package org.haughki.codeLibrary.programmingProblems;

import org.haughki.codeLibrary.aacommon.Common;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RotateTest {
    @Test
    public void rotate0() throws Exception {
        Rotate r = new Rotate();
        int[] a = {1,2,3,4,5,6,7};
        r.rotate(a, 0);
        int[] expected = {1,2,3,4,5,6,7};
        Assert.assertArrayEquals(expected, a);
    }

    @Test
    public void rotate7() throws Exception {
        Rotate r = new Rotate();
        int[] a = {1,2,3,4,5,6,7};
        r.rotate(a, 7);
        int[] expected = {1,2,3,4,5,6,7};
        Assert.assertArrayEquals(expected, a);
    }

    @Test
    public void rotate1() throws Exception {
        Rotate r = new Rotate();
        int[] a = {1,2,3,4,5,6,7};
        r.rotate(a, 1);
        int[] expected = {7,1,2,3,4,5,6};
        Assert.assertArrayEquals(expected, a);
    }

    @Test
    public void rotate3() throws Exception {
        Rotate r = new Rotate();
        int[] a = {1,2,3,4,5,6,7};
        r.rotate(a, 3);
        int[] expected = {5,6,7,1,2,3,4};
        Assert.assertArrayEquals(expected, a);
    }

    @Test
    public void rotate4() throws Exception {
        Rotate r = new Rotate();
        int[] a = {1,2,3,4,5,6,7};
        r.rotate(a, 4);
        int[] expected = {4,5,6,7,1,2,3};
        Assert.assertArrayEquals(expected, a);
    }

    @Test
    public void rotate9() throws Exception {
        Rotate r = new Rotate();
        int[] a = {1,2,3,4,5,6,7};
        r.rotate(a, 9);
        int[] expected = {6,7,1,2,3,4,5};
        Assert.assertArrayEquals(expected, a);
    }

    @Test
    public void rotateEven2() throws Exception {
        Rotate r = new Rotate();
        int[] a = {1,2,3,4,5,6};
        r.rotate(a, 2);
        int[] expected = {5,6,1,2,3,4};
        Assert.assertArrayEquals(expected, a);
    }

    @Test
    public void rotateBig() throws Exception {
        Rotate r = new Rotate();
        int[] a = Common.createArray(10000000);
        r.rotate(a,1);
        r.rotate(a, 9000000);
    }

// Can't replicate the Leetcode results of this test -- doesn't work the same way for me.. :(    
//    @Test
//    public void rotateLeetCodeBigTest() throws Exception {
//        Rotate r = new Rotate();
//        int[] a = Common.createArray(40000, 10);
//        r.rotate(a, 11939);
//    }
}