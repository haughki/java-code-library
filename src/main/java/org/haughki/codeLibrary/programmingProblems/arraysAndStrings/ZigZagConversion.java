
package org.haughki.codeLibrary.programmingProblems.arraysAndStrings;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;


/*
https://leetcode.com/problems/zigzag-conversion/?tab=Description
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display 
this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR"
 */
public class ZigZagConversion {
    public static void main(String[] args) {

        final String input = "ilovemydoggy";
        System.out.println(convert(input, 2));
    }

    /*
    Top voted solution in leetcode:  https://discuss.leetcode.com/topic/3162/easy-to-understand-java-solution
     */
    public static String convert(String s, int nRows) {
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[nRows];
        for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();

        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < nRows && i < len; idx++) // vertically down
                sb[idx].append(c[i++]);
            for (int idx = nRows-2; idx >= 1 && i < len; idx--) // obliquely up
                sb[idx].append(c[i++]);
        }
        for (int idx = 1; idx < sb.length; idx++)
            sb[0].append(sb[idx]);
        return sb[0].toString();
    }
    
    /*
    Calculates the next letter and appends it to the string. Does this by maintaining "rLeft" which tells "how many rows
    are left" for each step/move, according to a pattern.  E.g., for a 4 row zig-zag:
    4     4
    3   2 3   2
    2 3   2 3
    4     4
    Using this, you can calc the next index of the next letter in the new string.  Then you have to calc rLeft again,
    which is a little wonky.  Keep calc'ing the next letter and rLeft as long as i is within the string.  Do this for
    each row.
     */
    private static String convertCalc(String s, int numRows) {
        if (numRows < 2)
            return s;
        
        StringBuilder sb = new StringBuilder(s.length());
        
        int i = 0, currRow = 0, rLeft = numRows, move;
        while (currRow < numRows) {
            while (i < s.length()) {
                sb.append(s.charAt(i));
                move = rLeft * 2 - 2;
                i += move;
                move -= rLeft - 1;
                rLeft = numRows - move;
                if (rLeft == 1)
                    rLeft = numRows;
            }
            i = ++currRow;
            rLeft = numRows - i;
            if (rLeft == 1)
                rLeft = numRows;
        }
        
        return sb.toString();
    }
    
    
    /*
    OLD ATTEMPTS BELOW
     */
    private static String convert1(String s, int numRows) {
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

    // Second attempt
    private static String convert2(String s, int numRows) {
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
