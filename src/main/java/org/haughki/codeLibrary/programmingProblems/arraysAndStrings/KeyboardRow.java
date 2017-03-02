package org.haughki.codeLibrary.programmingProblems.arraysAndStrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/keyboard-row/
Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.

(see link)

Example 1:
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]
Note:
You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.
 */
public class KeyboardRow {
    public static void main(String[] args) {
        String[] a = {"Hello","Alaska","Dad","Peace"};
        Keyboard k = new Keyboard();
        for(String s : k.findWords(a))
            System.out.println(s);
    }
}

class Keyboard {
    final static Map<Character, Integer> charToRow = new HashMap<>();
    public String[] findWords(String[] words) {
        ArrayList<String> result = new ArrayList<>();
        for(String s : words) {
            int prev = -1; int curr = -1; 
            for (int i = 0; i < s.length(); i++) {
                curr = charToRow.get(Character.toLowerCase(s.charAt(i)));
                if (prev != -1) {
                    if (curr != prev) {
                        curr = -1;
                        break;
                    }
                }
                prev = curr;
            }
            if (curr != -1)
                result.add(s);
        }
        return result.toArray(new String[result.size()]);
    }
    
    static {
        charToRow.put('q', 0);
        charToRow.put('w', 0);
        charToRow.put('e', 0);
        charToRow.put('r', 0);
        charToRow.put('t', 0);
        charToRow.put('y', 0);
        charToRow.put('u', 0);
        charToRow.put('i', 0);
        charToRow.put('o', 0);
        charToRow.put('p', 0);

        charToRow.put('a', 1);
        charToRow.put('s', 1);
        charToRow.put('d', 1);
        charToRow.put('f', 1);
        charToRow.put('g', 1);
        charToRow.put('h', 1);
        charToRow.put('j', 1);
        charToRow.put('k', 1);
        charToRow.put('l', 1);

        charToRow.put('z', 2);
        charToRow.put('x', 2);
        charToRow.put('c', 2);
        charToRow.put('v', 2);
        charToRow.put('b', 2);
        charToRow.put('n', 2);
        charToRow.put('m', 2);
    }
}
