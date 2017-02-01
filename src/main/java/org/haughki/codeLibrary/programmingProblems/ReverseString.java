package org.haughki.codeLibrary.programmingProblems;

/*
Take a string in the form of "who are you sir" and reverse the words so that it reads
"sir you are who".  The first solution is to split and walk the resulting str array backwards.
Then the problem gets harder:  can you do it in place, with at most one "temp" char variable?
The solution is to convert the str to a char[], and then walk the entire string...
 */
public class ReverseString {
    
    public String reverseWords(String str) {
        char[] chars = str.toCharArray();
        
        reverse(0, chars.length, chars);
        recursiveReverse(0, chars.length, chars);
        
        return String.valueOf(chars);
    }

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

    void reverse(int start, int end, char[] str) {
        int j = end - 1;
        int loopHalf = end - ((end - start) / 2);
        char hold;
        for (int i = start; i < loopHalf; i++) {
            hold = str[i];
            str[i] = str[j];
            str[j] = hold;
            j--;
        }
    }
}
