package org.haughki.codeLibrary.programmingProblems;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;

import static org.haughki.codeLibrary.programmingProblems.GridIllumination.*;

public class GridIlluminationTest {
    @Test
    public void test1() throws Exception {
        Set<Coordinate> lamps = new HashSet<>();
        lamps.add(new Coordinate(1,2));
        driver(lamps);
        /*  1, 1
            2, 1
            -1, 1
            3, 1   */
        litRows.forEach((k, v) -> System.out.println(k + ", " + v));
        litColumns.forEach((k, v) -> System.out.println(k + ", " + v));
        litLeftRight.forEach((k, v) -> System.out.println(k + ", " + v));
        litRightLeft.forEach((k, v) -> System.out.println(k + ", " + v));
    }

    @Test
    public void test2() throws Exception {
        Set<Coordinate> lamps = new HashSet<>();
        lamps.add(new Coordinate(1,2));
        lamps.add(new Coordinate(1,3));
        driver(lamps);
        /*  1, 2
            2, 1   3, 1
            -1, 1   -2, 1
            3, 1   4, 1  */
        litRows.forEach((k, v) -> System.out.print(k + ", " + v + "   "));
        System.out.println();
        litColumns.forEach((k, v) -> System.out.print(k + ", " + v + "   "));
        System.out.println();
        litLeftRight.forEach((k, v) -> System.out.print(k + ", " + v + "   "));
        System.out.println();
        litRightLeft.forEach((k, v) -> System.out.print(k + ", " + v + "   "));
    }

    @Test
    public void queryTurnsOff() throws Exception {
        Set<Coordinate> lamps = new HashSet<>();
        lamps.add(new Coordinate(1,2));
        
        driver(lamps);
        Assert.assertTrue(areCoordinatesLit(new Coordinate(1,2)));
        assertAllSizeEqual(0);
        Assert.assertEquals(0, lamps.size());

        lamps.add(new Coordinate(1,2));
        driver(lamps);
        Assert.assertFalse(areCoordinatesLit(new Coordinate(3,1)));
        assertAllSizeEqual(1);
        Assert.assertTrue(areCoordinatesLit(new Coordinate(2,3)));
        assertAllSizeEqual(0);
        Assert.assertEquals(0, lamps.size());

        lamps.add(new Coordinate(1,2));
        lamps.add(new Coordinate(1,3));
        driver(lamps);
        Assert.assertFalse(areCoordinatesLit(new Coordinate(4,1)));
        Assert.assertEquals(1, litRows.size());
        Assert.assertEquals(2, litColumns.size());
        Assert.assertEquals(2, litLeftRight.size());
        Assert.assertEquals(2, litRightLeft.size());
        Assert.assertTrue(areCoordinatesLit(new Coordinate(2,3)));
        assertAllSizeEqual(0);
        Assert.assertEquals(0, lamps.size());

        lamps.add(new Coordinate(1,2));
        lamps.add(new Coordinate(1,3));
        driver(lamps);
        Assert.assertTrue(areCoordinatesLit(new Coordinate(0,1)));
        assertAllSizeEqual(1);
        Assert.assertFalse(areCoordinatesLit(new Coordinate(2,1)));
        assertAllSizeEqual(1);
        Assert.assertTrue(areCoordinatesLit(new Coordinate(2,4)));
        assertAllSizeEqual(0);
    }

    private void assertAllSizeEqual(final int size) {
        Assert.assertEquals(size, litRows.size());
        Assert.assertEquals(size, litColumns.size());
        Assert.assertEquals(size, litLeftRight.size());
        Assert.assertEquals(size, litRightLeft.size());
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