package org.haughki.codeLibrary.programmingProblems.hackerRank;

import java.util.Scanner;

// 4/6
// https://www.hackerrank.com/challenges/cavity-map
public class CavityMap {
    public static void main(String[] args) {
        String input = "4 1112 1912 1892 1234";
//        String input = "5 12579 68745 26974 83153 57246";

//        String input = "3 143 698 926";
//        String input = "3 143 688 926";
//        String input = "2 29 83";
//        String input = "1 9";
        Scanner in = new Scanner(input);
        
        //Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        String[] grid = new String[size];
        if (size < 3) {
            for(int row = 0; row < size; row++)
                System.out.println(in.next());
            return;
        }
        int[][] mirror = new int[size][size];
        for(int row = 0; row < size; row++){
            grid[row] = in.next();
            for (int col = 0; col < size; col++) {
                char c = grid[row].charAt(col);
                int num = Character.getNumericValue(c);
                if (row == 0) {
                    mirror[row + 1][col] = num;
                    System.out.print(c);
                } else if (row == size - 1) {
                    handleTrueUp(col, size, mirror[row - 1][col], num);
                } else if (row == 1) {
                    handleCurrentRow(mirror[row], grid[row], col, num, size);
                    mirror[row + 1][col] = num;
                } else if (row == size - 2) {
                    handleCurrentRow(mirror[row], grid[row], col, num, size);
                    handleTrueUp(col, size, mirror[row - 1][col], num);
                } else {
                    handleCurrentRow(mirror[row], grid[row], col, num, size);
                    handleTrueUp(col, size, mirror[row - 1][col], num);

                    mirror[row + 1][col] = num;
                }
            }
            if (row != 1)
                System.out.print("\n");
            if (row == size - 1)
                System.out.print(grid[row]);
        }
    }

    private static void handleTrueUp(int col, int size, int trueUp, int num) {
        if (col == 0 || col == size - 1)
            System.out.print(String.valueOf(trueUp));
        else {
            if (trueUp < 0)
                System.out.print(String.valueOf(-trueUp));
            else if (trueUp <= num)
                System.out.print(String.valueOf(trueUp));
            else
                System.out.print("X");
        }
    }

    private static void handleCurrentRow(int[] mirrorRow, String s, int col, int num, int size) {
        if (col == 0 || col == size - 1)
            mirrorRow[col] = num;
        else {
            int up = mirrorRow[col];
            int next = Character.getNumericValue(s.charAt(col + 1));
            int prev = Character.getNumericValue(s.charAt(col - 1));
            if (num <= up || num <= next || num <= prev)
                mirrorRow[col] = -num;
            else
                mirrorRow[col] = num;
        }
    }
}
