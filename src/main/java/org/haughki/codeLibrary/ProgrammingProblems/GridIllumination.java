package org.haughki.codeLibrary.ProgrammingProblems;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;


class Coordinate {
    final int row;
    final int col;
    final int leftRight;
    final int rightLeft;

    Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
        this.leftRight = row - col;
        this.rightLeft = row + col;
    }
    
    public Coordinate[] getAdjacent() {
        Coordinate[] adjacent = new Coordinate[9];
        adjacent[0] = new Coordinate(row - 1, col - 1);
        adjacent[1] = new Coordinate(row - 1, col);
        adjacent[2] = new Coordinate(row - 1, col + 1);
        
        adjacent[3] = new Coordinate(row, col - 1);
        adjacent[4] = this;
        adjacent[5] = new Coordinate(row, col + 1);

        adjacent[6] = new Coordinate(row + 1, col - 1);
        adjacent[7] = new Coordinate(row + 1, col);
        adjacent[8] = new Coordinate(row + 1, col + 1);
        
        return adjacent;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.row, this.col);
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate))
            return false;
        Coordinate test = (Coordinate)o;
        if (this.row != test.row)
            return false;
        if (this.col != test.col)
            return false;
        return true;
    }
}


/*
Given an NxN grid with an array of lamp coordinates. Each
lamp provides illumination to every square on their x axis, every square on
their y axis, and every square that lies in their diagonal (think of a Queen in
chess). Given an array of query coordinates, determine whether that point is
illuminated or not. The catch is when checking a query all lamps adjacent to, or
on, that query get turned off. The ranges for the variables/arrays were about:
10^3 < N < 10^9, 10^3 < lamps < 10^9, 10^3 < queries < 10^9.
 */
public class GridIllumination {

    final static HashMap<Integer, Integer> litRows = new HashMap<>();
    final static HashMap<Integer, Integer> litColumns = new HashMap<>();
    final static HashMap<Integer, Integer> litLeftRight = new HashMap<>();
    final static HashMap<Integer, Integer> litRightLeft = new HashMap<>();
    static Set<Coordinate> lamps;
    
    public static void main(String[] args) {
        
    }

    public static void driver(final Set<Coordinate> _lamps) {
        // TODO: validation.  E.g., are all lamps and queries within the grid?
        lamps = _lamps;
        populateLitPoints();
    }

    public static boolean areCoordinatesLit(Coordinate query) {
        boolean isLit = false;
        if (isAspectLit(query.row, litRows)) isLit = true;
        else if (isAspectLit(query.col, litColumns)) isLit = true;
        else if (isAspectLit(query.leftRight, litLeftRight)) isLit = true;
        else if (isAspectLit(query.rightLeft, litRightLeft)) isLit = true;
        
        findAndDisableLamps(query);
        return isLit;
    }

    private static void findAndDisableLamps(Coordinate query) {
        Coordinate[] adjacent = query.getAdjacent();
        for (Coordinate near : adjacent) {
            if (lamps.contains(near))
                turnLampOff(near);
        }
    }

    private static void turnLampOff(final Coordinate lamp) {
        removeLamp(lamp.row, litRows);
        removeLamp(lamp.col, litColumns);
        removeLamp(lamp.leftRight, litLeftRight);
        removeLamp(lamp.rightLeft, litRightLeft);
        
        lamps.remove(lamp);
    }

    private static void removeLamp(final int aspect, HashMap<Integer, Integer> map) {
        Integer aspectAmount = map.get(aspect);
        if(aspectAmount == null)
            throw new IllegalStateException("Lamp was turned on, should be able to turn it off.  There should be a value for this aspect!");
        else {
            if(--aspectAmount == 0)
                map.remove(aspect);
            else
                map.put(aspect, aspectAmount);
        }
    }

    private static boolean isAspectLit(final int aspect, HashMap<Integer,Integer> map) {
        return map.get(aspect) != null;
    }
    
    private static void populateLitPoints() {
        for(Coordinate lamp: lamps) {
            addOrUpdateMap(lamp.row, litRows);
            addOrUpdateMap(lamp.col, litColumns);
            addOrUpdateMap(lamp.leftRight, litLeftRight);
            addOrUpdateMap(lamp.rightLeft, litRightLeft);
        }
    }

    private static void addOrUpdateMap(int aspect, HashMap<Integer,Integer> map) {
        Integer num = map.get(aspect);
        if (num != null)
            map.put(aspect, ++num);
        else
            map.put(aspect, 1);
    }
}

