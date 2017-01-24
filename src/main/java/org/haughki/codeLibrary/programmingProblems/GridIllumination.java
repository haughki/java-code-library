package org.haughki.codeLibrary.programmingProblems;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;


/*
Given an NxN grid with an array of lamp coordinates. Each
lamp provides illumination to every square on their x axis, every square on
their y axis, and every square that lies in their diagonal (think of a Queen in
chess). Given an array of query coordinates, determine whether that point is
illuminated or not. The catch is, when checking a query all lamps adjacent to, or
on, that query get turned off. The ranges for the variables/arrays were about:
10^3 < N < 10^9, 10^3 < lamps < 10^9, 10^3 < queries < 10^9.
 */
public class GridIllumination {
    public static void main(String[] args) {

    }
}


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


class Illuminator {

    final HashMap<Integer, Integer> litRows = new HashMap<>();  // coordinate value to number of times that value has been set
    final HashMap<Integer, Integer> litColumns = new HashMap<>();
    final HashMap<Integer, Integer> litLeftRight = new HashMap<>();
    final HashMap<Integer, Integer> litRightLeft = new HashMap<>();
    private final Set<Coordinate> lamps;

    Illuminator(final Set<Coordinate> lamps) {
        // TODO: validation.  E.g., are all lamps and queries within the grid?
        this.lamps = lamps;
        populateLitPoints();
    }
    
    public boolean isLit(Coordinate query) {
        boolean isLit = false;
        if (isAspectLit(query.row, litRows)) isLit = true;
        else if (isAspectLit(query.col, litColumns)) isLit = true;
        else if (isAspectLit(query.leftRight, litLeftRight)) isLit = true;
        else if (isAspectLit(query.rightLeft, litRightLeft)) isLit = true;

        findAndDisableLamps(query);
        return isLit;
    }

    private void findAndDisableLamps(Coordinate query) {
        // walk the point around the query (and the point)
        for (int r = query.row - 1; r < query.row + 2; r++) {
            for (int c = query.col - 1; c < query.col + 2; c++) {
                Coordinate near = new Coordinate(r,c);
                if (lamps.contains(near))
                    turnLampOff(near);                
            }
        }
    }

    private void turnLampOff(final Coordinate lamp) {
        removeLamp(lamp.row, litRows);
        removeLamp(lamp.col, litColumns);
        removeLamp(lamp.leftRight, litLeftRight);
        removeLamp(lamp.rightLeft, litRightLeft);

        lamps.remove(lamp);  // remove the lamp so that the next adjacent query doesn't find it
    }

    private void removeLamp(final int aspect, HashMap<Integer, Integer> map) {
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

    private boolean isAspectLit(final int aspect, HashMap<Integer,Integer> map) {
        return map.get(aspect) != null;
    }

    private void populateLitPoints() {
        for(Coordinate lamp: lamps) {
            addOrUpdateMap(lamp.row, litRows);
            addOrUpdateMap(lamp.col, litColumns);
            addOrUpdateMap(lamp.leftRight, litLeftRight);
            addOrUpdateMap(lamp.rightLeft, litRightLeft);
        }
    }

    private void addOrUpdateMap(int aspect, HashMap<Integer,Integer> map) {
        Integer num = map.get(aspect);
        if (num != null)
            map.put(aspect, ++num);
        else
            map.put(aspect, 1);
    }
}
