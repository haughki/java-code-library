package org.haughki.codeLibrary.programmingProblems.arraysAndStrings;

/*
http://stackoverflow.com/questions/35470605/permutation-of-string-using-backtracking-algorithm

Given a string, print all permutations of a given string.

The permutations of string ABC.
ABC ACB BAC BCA CBA CAB
 */
public class Permutations {

    public static void main(String[] args) {
        String s = "abc";
        permute(s.toCharArray(), 0);
        //generate(s.length(), s.toCharArray());
    }
    static void swap(char chars[], int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    static void permute(char chars[], int i) {
        if (i >= chars.length)
            System.out.println(String.valueOf(chars));
        else {
            for (int j = i; j < chars.length; j++) {
                swap(chars, i, j);
                permute(chars, i + 1);
                swap(chars, i, j); // backtrack
            }
        }
    }

    /*
    Heap's algorithm -- arrg.
     */
    static void generate(int n, char[] chars) {
        if (n == 1)
            System.out.println(String.valueOf(chars));
        else {
            for (int i = 0; i < n - 1; i++) {
                generate(n - 1, chars);
                if (n % 2 == 0)
                    swap(chars, i, n - 1);
                else
                    swap(chars, 0, n - 1);
            }
            generate(n - 1, chars);
        }
    }
}