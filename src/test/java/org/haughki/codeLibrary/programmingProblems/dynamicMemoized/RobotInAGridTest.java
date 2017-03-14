package org.haughki.codeLibrary.programmingProblems.dynamicMemoized;

import org.haughki.codeLibrary.aacommon.Coordinate;
import org.haughki.codeLibrary.programmingProblems.dynamicMemoized.RobotInAGrid;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RobotInAGridTest {
    @Test
    public void noRows() throws Exception {
        boolean [][] grid = new boolean[0][0];
        
        RobotInAGrid r = new RobotInAGrid();
        assertEquals("", r.start(grid));
    }

    @Test
    public void noCols() throws Exception {
        boolean [][] grid = new boolean[1][0];

        RobotInAGrid r = new RobotInAGrid();
        assertEquals("", r.start(grid));
    }

    @Test
    public void oneRowOneCol() throws Exception {
        boolean [][] grid = new boolean[1][1];
        grid[0][0] = true;

        RobotInAGrid r = new RobotInAGrid();
        assertEquals("", r.start(grid));
    }

    @Test
    public void oneRowTwoCols() throws Exception {
        boolean [][] grid = new boolean[1][2];
        buildGrid(grid, new HashSet<>());
        RobotInAGrid r = new RobotInAGrid();
        assertEquals("r", r.start(grid));
    }

    @Test
    public void oneColTwoRows() throws Exception {
        boolean [][] grid = new boolean[2][1];
        buildGrid(grid, new HashSet<>());
        RobotInAGrid r = new RobotInAGrid();
        assertEquals("d", r.start(grid));
    }

    @Test
    public void threeByFour() throws Exception {
        boolean [][] grid = new boolean[3][4];
        Set<Coordinate> offLimits = new HashSet<>();
        offLimits.add(new Coordinate(0,2));
        offLimits.add(new Coordinate(1,1));
        buildGrid(grid, offLimits);
        RobotInAGrid r = new RobotInAGrid();
        assertEquals("ddrrr", r.start(grid));
    }

    @Test
    public void fiveBySix() throws Exception {
        boolean [][] grid = new boolean[5][6];
        Set<Coordinate> offLimits = new HashSet<>();
        offLimits.add(new Coordinate(1,2));
        offLimits.add(new Coordinate(1,4));
        offLimits.add(new Coordinate(1,5));
        offLimits.add(new Coordinate(2,2));
        offLimits.add(new Coordinate(3,3));
        buildGrid(grid, offLimits);
        RobotInAGrid r = new RobotInAGrid();
        assertEquals("rrrddrrdd", r.start(grid));
    }

    @Test
    public void fiveBySixBlocked() throws Exception {
        boolean [][] grid = new boolean[5][6];
        Set<Coordinate> offLimits = new HashSet<>();
        offLimits.add(new Coordinate(1,2));
        offLimits.add(new Coordinate(1,4));
        offLimits.add(new Coordinate(2,2));
        offLimits.add(new Coordinate(2,5));
        offLimits.add(new Coordinate(3,3));
        offLimits.add(new Coordinate(3,4));
        buildGrid(grid, offLimits);
        RobotInAGrid r = new RobotInAGrid();
        assertEquals("rdddrdrrr", r.start(grid));
    }

    // A maze that makes the robot try the same paths over and over -- solution optimizes for this using
    // memoization.
    @Test 
    public void forceRepetition() throws Exception {
        boolean [][] grid = new boolean[7][6];
        Set<Coordinate> offLimits = new HashSet<>();
        offLimits.add(new Coordinate(0,1));
        offLimits.add(new Coordinate(1,1));
        offLimits.add(new Coordinate(2,1));
        offLimits.add(new Coordinate(3,1));
        offLimits.add(new Coordinate(4,1));
        offLimits.add(new Coordinate(5,1));
        buildGrid(grid, offLimits);
        RobotInAGrid r = new RobotInAGrid();
        assertEquals("ddddddrrrrr", r.start(grid));
    }

    // Need this because boolean[][] inits to false.
    private void buildGrid(boolean[][] grid, Set<Coordinate> offLimits) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!offLimits.contains(new Coordinate(i, j)))
                    grid[i][j] = true;
            }
        }
    }
}