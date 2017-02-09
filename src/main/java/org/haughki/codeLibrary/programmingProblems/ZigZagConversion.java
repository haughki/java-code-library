
package org.haughki.codeLibrary.programmingProblems;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class ZigZagConversion {
    public static void main(String[] args) {

        final String input = "PAYPALISHIRING";
        System.out.println(convert(input, 2));
    }
    private static String convert(String s, int numRows) {
        if (numRows <= 1) {
            System.out.println(s);
            return s;
        }
        
        // holds the char to be printed as well as the number of spaces that need to be printed before the char
        class SpacesAndChar {
            private SpacesAndChar(final char _c, final int _numSpaces) {
                c = _c;
                numSpaces = _numSpaces;
            }
            private final char c;
            private final int numSpaces;
        }
        
        // actual Deque is a LinkedList
        List<Deque<SpacesAndChar>> table = new ArrayList<>(numRows);
        
        int stringIndex = 0;
        // need to pre-pop first char
        SpacesAndChar firstChar = new SpacesAndChar(s.charAt(stringIndex++), 0);
        Deque<SpacesAndChar> firstRow = new LinkedList<>();
        firstRow.offer(firstChar);
        table.add(0, firstRow);
        boolean needNewRow = true;  // used to initialize the rows
        // while there are still characters in the string
        while (stringIndex < s.length()) {
            // walk down the column
            // for each row, starting with the second row
            for (int row = 1; row < numRows; row++) {
                // add the next char from string into the current row
                // add a space before each char
                if (!(stringIndex < s.length()))
                    break;
                if (needNewRow) {
                    SpacesAndChar current = new SpacesAndChar(s.charAt(stringIndex++), 0);  // first column is not indented
                    Deque<SpacesAndChar> newRow = new LinkedList<>();
                    newRow.offer(current);
                    table.add(row, newRow);
                    if (row == numRows -1)
                        needNewRow = false;
                } else {
                    SpacesAndChar current = new SpacesAndChar(s.charAt(stringIndex++), row - 1);
                    table.get(row).offer(current);
                }
            }

            // walk up and over
            // for each row starting at the bottom row
            int numSpaces = 0;
            for (int row = numRows - 2 ; row > -1; row--) {
                if (!(stringIndex < s.length()))
                    break;
                // move over one column (this is just the next item in the deque)
                // move up one row
                // add the next char from string into the current row
                
                SpacesAndChar current = new SpacesAndChar(s.charAt(stringIndex++), numSpaces++);
                table.get(row).offer(current);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        table.forEach(r -> {
            r.forEach(c -> {
                Stream.generate(() -> " ").limit(c.numSpaces).forEach(System.out::print);
                sb.append(c.c);
                System.out.print(c.c);
            });
            System.out.println();
        });
        System.out.println();
        return sb.toString();
    }
}
