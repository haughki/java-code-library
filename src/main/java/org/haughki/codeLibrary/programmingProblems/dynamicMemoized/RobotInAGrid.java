package org.haughki.codeLibrary.programmingProblems.dynamicMemoized;

import org.haughki.codeLibrary.aacommon.Coordinate;

import java.util.HashSet;
import java.util.Set;

/*
Cracking p. 135
 */
public class RobotInAGrid {

    /*
    Second attempt.  Modifications based on the explanation in CTCI.  First, start from the last square instead of the
    first. Why is it always like this.  I don't think this _really_ does much for the basic algorithm, but it does
    have the advantage of making it easier to build the path (by appending) during the unwinding of the recursion.
    
    The second change is much more important:  the memoization.  As simple as:  if you've visited a coordinate, and
    it's a dead end, add it to a Set<Coordinate>.  Query this each time before you recurse -- if you've been there
    already, don't go there again.  This is a huge time optimization for certain mazes.  Changes the time complexity
    from O(2^r+c) to O(rc)
     */
    public String start(boolean[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1)
            return "";

        Set<Coordinate> tried = new HashSet<>();
        StringBuilder path = move(grid.length - 2, grid[0].length - 1, grid, "d", tried);
        if (path != null)
            return path.toString();
        path = move(grid.length - 1, grid[0].length - 2, grid, "r", tried);
        if (path != null)
            return path.toString();
        return "";
    }

    private StringBuilder move(int r, int c, boolean[][] grid, String direction, Set<Coordinate> tried) {
        if (r < 0 || c < 0)
            return null;
        if (!grid[r][c])
            return null;
        if (r == 0 && c == 0)
            return new StringBuilder(direction);

        Coordinate current = new Coordinate(r, c);
        if (tried.contains(current))
            return null;
        
        StringBuilder path = move(r - 1, c, grid, "d", tried);
        if (path != null)
            return path.append(direction);
        path = move(r, c - 1, grid, "r", tried);
        if (path != null)
            path.append(direction);
        
        tried.add(current);  // memoization
        return path;
    }

    
    // First attempt.  Starting from 0,0
    public String start1(boolean[][] grid) {
        if (grid.length < 1 || grid[0].length < 1)
            return "";
        
        StringBuilder path = move1(0, 1, grid.length, grid[0].length, grid, "r");
        if (path != null)
            return path.toString();
        path = move1(1, 0, grid.length, grid[0].length, grid, "d");
        if (path != null)
            return path.toString();
        return "";
    }
    
    private StringBuilder move1(int r, int c, int nRows, int nCols, boolean[][] grid, String direction) {
        if (r >= nRows || c >= nCols)
            return null;
        if (!grid[r][c])
            return null;
        if (r == nRows - 1 && c == nCols - 1)
            return new StringBuilder(direction);
        
        StringBuilder path = move1(r, c + 1, nRows, nCols, grid, "r");
        if (path != null)
            return path.insert(0, direction);  // TODO: this and the one below are TERRIBLE. Build as we go or figure out a better way.
        path = move1(r + 1, c, nRows, nCols, grid, "d");
        if (path != null)
            path.insert(0, direction);
        return path;
    }
}
