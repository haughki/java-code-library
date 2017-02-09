
package org.haughki.codeLibrary.programmingProblems;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ZigZagConversion2 {
    public static void main(String[] args) {

        final String input = "PAYPALISHIRING";
        System.out.println(convert(input, 3));
    }
    private static String convert(String s, int numRows) {
        if (numRows <= 1) {
            System.out.println(s);
            return s;
        }
        
        // actual Deque is a LinkedList
        List<Deque<Character>> table = new ArrayList<>(numRows);
        
        int stringIndex = 0;
        // need to pre-pop first char
        Deque<Character> firstRow = new LinkedList<>();
        firstRow.offer(s.charAt(stringIndex++));
        table.add(0, firstRow);
        boolean needNewRow = true;  // used to initialize the rows
        // while there are still characters in the string
        while (stringIndex < s.length()) {
            // walk down the column
            for (int row = 1; row < numRows; row++) {
                if (!(stringIndex < s.length()))
                    break;
                if (needNewRow) {
                    Deque<Character> newRow = new LinkedList<>();
                    newRow.offer(s.charAt(stringIndex++));
                    table.add(row, newRow);
                    if (row == numRows -1)
                        needNewRow = false;
                } else {
                    table.get(row).offer(s.charAt(stringIndex++));
                }
            }

            // walk up and over
            for (int row = numRows - 2 ; row > -1; row--) {
                if (!(stringIndex < s.length()))
                    break;
                table.get(row).offer(s.charAt(stringIndex++));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        table.forEach(r -> r.forEach(sb::append));
        return sb.toString();
    }
}
