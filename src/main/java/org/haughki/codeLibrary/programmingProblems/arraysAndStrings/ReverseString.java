package org.haughki.codeLibrary.programmingProblems.arraysAndStrings;

import sun.plugin.dom.exception.InvalidStateException;

/*
Take a string in the form of "who are you sir" and reverse the words so that it reads
"sir you are who".  The first solution is to split and walk the resulting str array backwards.
Then the problem gets harder:  can you do it in place, with at most one "temp" char variable?
The solution is to convert the str to a char[], and then walk the entire string...
 */
public class ReverseString {

    public static void main(String[] args) {
        String s = "hello";
        char[] chars = s.toCharArray();
        int i = 0, j = chars.length - 1;
        char temp;
        while(i < j) {
            temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++; j--;
        }
        System.out.println(String.valueOf(chars));
    }
    
    public String reverseWords(String str) {
        char[] chars = str.toCharArray();
        
        if (chars.length < 1) return "";
        int i = 0, lasti = 0, j = chars.length - 1, lastj = chars.length;
        char temp;
        while (i < j) {
            // swap the entire string, end-to-end
            temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            
            // if you hit a space, reverse the word
            if (chars[i] == ' ') {
                reverse(lasti, i, chars);
                lasti = i + 1;  // one char after the space
            }
            if (chars[j] == ' ') {
                reverse(j + 1, lastj, chars);
                lastj = j;
            }
            i++; j--;
        }
        // we've done the whole string up to the middle.  i is now >= to j.
        if (i == j) {
            if (chars[i] == ' ') {  // reverse left and right
                reverse(lasti, i, chars);
                reverse(j + 1, lastj, chars);
            } else
                reverse(lasti, lastj, chars);  // middle of word:  reverse from last i to last j
        } else { // i and j have passed each other and are off by 1
            if (chars[i] == ' ')
                reverse(lasti, i, chars);  // reverse word left
            else if (chars[j] == ' ')
                reverse(j + 1, lastj, chars);  // reverse word right
            else {
                reverse(lasti, lastj, chars);  // middle of word:  reverse from last i to last j
            }
        }
        return String.valueOf(chars);
    }


    void reverse(int start, int end, char[] chars) {
        char temp;
        while (start < end){
            temp = chars[start];
            chars[start++] = chars[--end];
            chars[end] = temp;
        }
    }


    // first attempt -- see commented out methods below
    //        reverse(0, chars.length, chars);
    //        recursiveReverse(0, chars.length, chars);
    private void recursiveReverse(int start, int end, char[] chars) {
        int spaceIndex = findSpace(start, end, chars);
        if (spaceIndex == -1) {
            reverse(start, end, chars);
            return;
        }

        reverse(start, spaceIndex, chars);
        recursiveReverse(spaceIndex + 1, end, chars);
    }

    private int findSpace(int start, int end, char[] chars) {
        char curr;
        for (int i = start; i < end; i++) {
            curr = chars[i];
            if (curr == ' ')
                return i;
        }

        return -1;
    }
}
