package org.haughki.codeLibrary.programmingProblems.arraysAndStrings;

import org.haughki.codeLibrary.programmingProblems.arraysAndStrings.MoveZeros;
import org.junit.Assert;
import org.junit.Test;

public class MoveZerosTest {
    @Test
    public void moveZeroes() throws Exception {
        int[] nums = {0, 1, 0, 3, 12};
        MoveZeros mz = new MoveZeros();
        mz.moveZeroes(nums);
        //Arrays.stream(nums).forEach(i -> System.out.print(i + ", "));
        Assert.assertArrayEquals(new int[]{1,3,12,0,0}, nums);
    }

    @Test
    public void zeroes() throws Exception {
        int[] nums = {0, 0, 0, 0};
        MoveZeros mz = new MoveZeros();
        mz.moveZeroes(nums);
        //Arrays.stream(nums).forEach(i -> System.out.print(i + ", "));
        Assert.assertArrayEquals(new int[]{0, 0, 0, 0}, nums);
    }

    @Test
    public void zeroesInRow() throws Exception {
        int[] nums = {2, 0, 0, 0, 12};
        MoveZeros mz = new MoveZeros();
        mz.moveZeroes(nums);
        //Arrays.stream(nums).forEach(i -> System.out.print(i + ", "));
        Assert.assertArrayEquals(new int[]{2,12,0,0,0}, nums);
    }

    @Test
    public void zeroesAtEnd() throws Exception {
        int[] nums = {4, 1, 3, 0, 0};
        MoveZeros mz = new MoveZeros();
        mz.moveZeroes(nums);
        //Arrays.stream(nums).forEach(i -> System.out.print(i + ", "));
        Assert.assertArrayEquals(new int[]{4,1,3,0,0}, nums);
    }
}