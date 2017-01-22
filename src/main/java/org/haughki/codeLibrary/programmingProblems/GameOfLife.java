package org.haughki.codeLibrary.programmingProblems;

import java.util.Arrays;

public class GameOfLife {
    public static void main(String[] args) {
        GolSolution game = new GolSolution();
        int[][] board = new int[5][4];
        game.gameOfLife(board);
    }
}

class GolSolution {
    private int rows;
    private int cols;

    public void gameOfLife(int[][] board) {
        if (board.length < 1 || board[0].length < 1)
            return;
        
        rows = board.length;
        if (rows > 0)
            cols = board[0].length;

        int[][] newBoard = new int[rows][cols];
        for (int row = 0; row < rows; row++){
            for (int col = 0; col < cols; col++) {
                int adjacent = countLiveAdjacent(row, col, board);
                int newVal = calcNewCellValue(board[row][col], adjacent);
                newBoard[row][col] = newVal;
            }
        }
        System.arraycopy(newBoard, 0, board, 0, board.length);
    }

    private int calcNewCellValue(int live, int adjacent) {
        if (live > 0) {
            return adjacent >= 2 && adjacent <= 3 ? 1 : 0;
        } else
            return adjacent == 3 ? 1 : 0;
    }

    private int countLiveAdjacent(int row, int col, int[][] board) {
        int adjacent = 0;
        for (int r = Math.max(0, row - 1); r < Math.min(rows, row + 2); r++) {
            for (int c = Math.max(0, col - 1); c < Math.min(cols, col + 2); c++)
                adjacent += board[r][c];
        }
        adjacent -= board[row][col];
        return adjacent;
    }
}

