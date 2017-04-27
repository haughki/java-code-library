package org.haughki.codeLibrary.programmingProblems.arraysAndStrings;


/*
https://leetcode.com/problems/reverse-words-in-a-string/#/description

Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".
 */
public class ReverseStringII {
    public static void main (String[]args){
        Rever r = new Rever();
        System.out.println(r.reverseWords("the sky is blue"));

        if (!r.reverseWords("     ").equals(""))
            throw new IllegalStateException();

        String ab = r.reverseWords("   a   b ");
        System.out.println(ab);
        if (!ab.equals("b a"))
            throw new IllegalStateException();

        String space = r.reverseWords("   one   two   three  ");
        System.out.println(space);
        if (!space.equals("three two one"))
            throw new IllegalStateException();

    }
}

class Rever {
    
    public String reverseWords(String s) {
        String trimmed = s.trim();
        StringBuilder sb = new StringBuilder(trimmed);
        swap(sb, 0, sb.length() - 1);

        int back = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ' ') {
                swap(sb, back, i - 1);
                back = i + 1;
                int j = i + 1;
                char next = sb.charAt(j);
                while(next == ' ') {
                    sb.deleteCharAt(j);
                    next = sb.charAt(j);
                }
            }
            else if (i == sb.length() - 1) {
                swap(sb, back, i);
            }
        }

        return sb.toString();
    }

    private String swap(StringBuilder sb, int start, int end) {
        int i = start;
        int j = end;

        while (i < j ) {
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
            i++;
            j--;
        }
        return sb.toString();
    }
}
