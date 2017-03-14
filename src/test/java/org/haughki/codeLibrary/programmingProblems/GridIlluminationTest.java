package org.haughki.codeLibrary.programmingProblems;

import org.haughki.codeLibrary.aacommon.Coordinate;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;


public class GridIlluminationTest {
    @Test
    public void test1() throws Exception {
        Set<Coordinate> lamps = new HashSet<>();
        lamps.add(new Coordinate(1,2));
        Illuminator ill = new Illuminator(lamps);
        /*  1, 1
            2, 1
            -1, 1
            3, 1   */
        ill.litRows.forEach((k, v) -> System.out.println(k + ", " + v));
        ill.litColumns.forEach((k, v) -> System.out.println(k + ", " + v));
        ill.litLeftRight.forEach((k, v) -> System.out.println(k + ", " + v));
        ill.litRightLeft.forEach((k, v) -> System.out.println(k + ", " + v));
    }

    @Test
    public void test2() throws Exception {
        Set<Coordinate> lamps = new HashSet<>();
        lamps.add(new Coordinate(1,2));
        lamps.add(new Coordinate(1,3));
        Illuminator ill = new Illuminator(lamps);
        /*  1, 2
            2, 1   3, 1
            -1, 1   -2, 1
            3, 1   4, 1  */
        ill.litRows.forEach((k, v) -> System.out.print(k + ", " + v + "   "));
        System.out.println();
        ill.litColumns.forEach((k, v) -> System.out.print(k + ", " + v + "   "));
        System.out.println();
        ill.litLeftRight.forEach((k, v) -> System.out.print(k + ", " + v + "   "));
        System.out.println();
        ill.litRightLeft.forEach((k, v) -> System.out.print(k + ", " + v + "   "));
    }

    @Test
    public void queryTurnsOff() throws Exception {
        Set<Coordinate> lamps = new HashSet<>();
        lamps.add(new Coordinate(1,2));

        Illuminator ill = new Illuminator(lamps);
        Assert.assertTrue(ill.isLit(new Coordinate(1,2)));
        assertAllSizeEqual(ill, 0);
        Assert.assertEquals(0, lamps.size());

        lamps.add(new Coordinate(1,2));
        ill = new Illuminator(lamps);
        Assert.assertFalse(ill.isLit(new Coordinate(3,1)));
        assertAllSizeEqual(ill, 1);
        Assert.assertTrue(ill.isLit(new Coordinate(2,3)));
        assertAllSizeEqual(ill, 0);
        Assert.assertEquals(0, lamps.size());

        lamps.add(new Coordinate(1,2));
        lamps.add(new Coordinate(1,3));
        ill = new Illuminator(lamps);
        Assert.assertFalse(ill.isLit(new Coordinate(4,1)));
        Assert.assertEquals(1, ill.litRows.size());
        Assert.assertEquals(2, ill.litColumns.size());
        Assert.assertEquals(2, ill.litLeftRight.size());
        Assert.assertEquals(2, ill.litRightLeft.size());
        Assert.assertTrue(ill.isLit(new Coordinate(2,3)));
        assertAllSizeEqual(ill, 0);
        Assert.assertEquals(0, lamps.size());

        lamps.add(new Coordinate(1,2));
        lamps.add(new Coordinate(1,3));
        ill = new Illuminator(lamps);
        Assert.assertTrue(ill.isLit(new Coordinate(0,1)));
        assertAllSizeEqual(ill, 1);
        Assert.assertFalse(ill.isLit(new Coordinate(2,1)));
        assertAllSizeEqual(ill, 1);
        Assert.assertTrue(ill.isLit(new Coordinate(2,4)));
        assertAllSizeEqual(ill, 0);
    }

    private void assertAllSizeEqual(final Illuminator ill, final int size) {
        Assert.assertEquals(size, ill.litRows.size());
        Assert.assertEquals(size, ill.litColumns.size());
        Assert.assertEquals(size, ill.litLeftRight.size());
        Assert.assertEquals(size, ill.litRightLeft.size());
    }

    @Test
    public void coordinates() throws Exception {
        Assert.assertEquals(new Coordinate(1,2), new Coordinate(1,2));
        Assert.assertNotEquals(new Coordinate(1,3), new Coordinate(1,2));
        Assert.assertNotEquals(new Coordinate(1,3), new Coordinate(3,3));

        Set<Coordinate> coordinates = new HashSet<>();
        coordinates.add(new Coordinate(1,2));
        Assert.assertFalse(coordinates.add(new Coordinate(1,2)));
        Assert.assertEquals(1, coordinates.size());
    }
    
    
    
    

    private Entry<Integer,Integer> getNext(HashMap<Integer, Integer> map) {
        if(map.size() > 0) {
            Iterator<Entry<Integer, Integer>> it = map.entrySet().iterator();
            return it.next();
        }
        return null;
    }

}