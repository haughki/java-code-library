package org.haughki.codeLibrary.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.TooManyListenersException;

import static org.junit.Assert.*;

public class TwoSumTest {
    @Test
    public void sumLists() throws Exception {
        List<Integer> l1 = new LinkedList<>();
        l1.add(7);
        l1.add(1);
        l1.add(6);
        List<Integer> l2 = new LinkedList<>();
        l2.add(5);
        l2.add(9);
        l2.add(2);

        TwoSum ts = new TwoSum(l1, l2);
        Assert.assertArrayEquals(new Integer[]{2, 1, 9}, ts.sumLists().toArray());

        l1 = new LinkedList<>();
        l1.add(7);
        l1.add(6);
        l2 = new LinkedList<>();
        l2.add(5);
        l2.add(9);
        l2.add(2);

        ts = new TwoSum(l1, l2);
        Assert.assertArrayEquals(new Integer[]{2, 6, 3}, ts.sumLists().toArray());

        l1 = new LinkedList<>();
        l1.add(9);
        l1.add(9);
        l2 = new LinkedList<>();
        l2.add(9);
        l2.add(9);

        ts = new TwoSum(l1, l2);
        Assert.assertArrayEquals(new Integer[]{8, 9, 1}, ts.sumLists().toArray());
    }

    @Test
    public void reverseSum() throws Exception {
        List<Integer> l1 = new LinkedList<>();
//        l1.add(7);
//        l1.add(1);
//        l1.add(6);
        List<Integer> l2 = new LinkedList<>();
//        l2.add(5);
//        l2.add(9);
//        l2.add(2);
//
        TwoSum ts = new TwoSum(l1, l2);
//        //ts.reverseSum().forEach(System.out::print);
//        Assert.assertArrayEquals(new Integer[]{1, 3, 0, 8}, ts.reverseSum().toArray());

        l1 = new LinkedList<>();
        l1.add(7);
        l1.add(6);
        l2 = new LinkedList<>();
        l2.add(5);
        l2.add(9);
        l2.add(2);

        ts = new TwoSum(l1, l2);
        ts.reverseSum().forEach(System.out::print);
        //Assert.assertArrayEquals(new Integer[]{3, 5, 8}, ts.reverseSum().toArray());

//        l1 = new LinkedList<>();
//        l1.add(9);
//        l1.add(9);
//        l2 = new LinkedList<>();
//        l2.add(9);
//        l2.add(9);
//
//        ts = new TwoSum(l1, l2);
//        Assert.assertArrayEquals(new Integer[]{1, 9, 8}, ts.reverseSum().toArray());
    }

}