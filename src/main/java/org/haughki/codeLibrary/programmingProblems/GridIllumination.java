package org.haughki.codeLibrary.programmingProblems;

import org.haughki.codeLibrary.aacommon.Coordinate;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;


/*
Given an NxN grid with an array of lamp coordinates. Each lamp provides illumination to every square on its x axis, 
every square on its y axis, and every square that lies on its diagonal (think of a Queen in chess). Given an array of 
query coordinates, determine whether or not the query point is illuminated. Moreover, whenever you execute a query, all 
lamps adjacent to or on that query point are permanently un-illuminated. The ranges for the variables/arrays is:
10^3 < N < 10^9, 10^3 < lamps < 10^9, 10^3 < queries < 10^9.

Solution: brute force would try to create the N x N grid (2d array) and set some "illuminated" value at each illuminated
point and then query the grid.  While a 2d array is a fine model for the problem, it quickly becomes clear that this
approach won't work at all for the value ranges involved, and would be complicated to boot.  

The insight is that any illuminated point also illuminates the entire column, row, and each diagonal for that point.  
So, any query that "lands on" any of those values is also lit. The row and column are obvious; the diagonals need 
to be calculated (see code), but still only require one value.  By storing each of the
four values for every lamp in separate sets, it becomes simple to query against those sets.  E.g., if a lamp illuminates
column 4, store 4 in the "columns" set; then, query that set with the column of the query -- if it hits, the query is
lit.  Then, the added problem of "de-lumintating" just requires a little addition of using hashtables instead of sets
to hold a count for each illuminated point; that way, if you disable a lamp during a query, you just need to decrement
the count for that row/column etc.
 */
public class GridIllumination {
    public static void main(String[] args) {

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
                if (lamps.contains(near)) // requires the equals/hashcode override
                    turnLampOff(near);                
            }
        }
    }

    private void turnLampOff(final Coordinate lamp) {
        turnLampOff(lamp.row, litRows);
        turnLampOff(lamp.col, litColumns);
        turnLampOff(lamp.leftRight, litLeftRight);
        turnLampOff(lamp.rightLeft, litRightLeft);

        lamps.remove(lamp);  // remove the lamp so that the next adjacent query doesn't find it
    }

    private void turnLampOff(final int aspect, HashMap<Integer, Integer> map) {
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
