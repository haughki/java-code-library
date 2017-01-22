package org.haughki.codeLibrary.programmingProblems;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GolSolutionTest {
    @Test
    public void noRows() throws Exception {
        GolSolution game = new GolSolution();
        int[][] board = new int[0][0];
        game.gameOfLife(board);
        Assert.assertEquals(0, board.length);
    }

    @Test
    public void noCols() throws Exception {
        GolSolution game = new GolSolution();
        int[][] board = new int[2][0];
        game.gameOfLife(board);
        Assert.assertEquals(0, board[0].length);
    }

    @Test
    public void gameOfLifeHappy() throws Exception {
        GolSolution game = new GolSolution();
        int[][] board = new int[5][4];
        board[0][1] = 1;
        board[2][1] = 1;
        board[2][2] = 1;
        board[3][1] = 1;
        printBoard(board);
        System.out.println();
        game.gameOfLife(board);

        printBoard(board);

        System.out.println();
        
        int[][] expected = new int[5][4];
        expected[1][1] = 1;
        expected[1][2] = 1;
        expected[2][1] = 1;
        expected[2][2] = 1;
        expected[3][1] = 1;
        expected[3][2] = 1;

        printBoard(expected);
        validateBoard(expected, board);
        
    }

    private void validateBoard(int[][] expected, int[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
//                System.out.println(r + ":" + c + " -> " + board[r][c]);
//                System.out.println(r + ":" + c + " -> " + expected[r][c]);
                Assert.assertEquals(expected[r][c], board[r][c]);
            }
        }
    }

    private void printBoard(int[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                // System.out.println(r + ":" + c + " -> " + board[r][c]);
                System.out.print(board[r][c] + "  ");
            }
            System.out.println();
        }
    }

    
}