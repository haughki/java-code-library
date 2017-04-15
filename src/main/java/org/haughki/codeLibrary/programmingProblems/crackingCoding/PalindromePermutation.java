package org.haughki.codeLibrary.programmingProblems.crackingCoding;

import java.util.HashSet;
import java.util.Set;

/*
CtCI p. 91
 */
public class PalindromePermutation {
    public static void main(String[] args) {
        PalinPerm p = new PalinPerm();
        String s = "Tact Coa";
        System.out.println(p.isPalindrome(s));
        s = "abccbA";
        System.out.println(p.isPalindrome(s));
        s = "abCdcba";
        System.out.println(p.isPalindrome(s));
        s = "abcbdbcba";
        System.out.println(p.isPalindrome(s));
        s = "A man a plan a canal Panama";
        System.out.println(p.isPalindrome(s));
        
        System.out.println();
        s = "abcdcb";
        System.out.println(p.isPalindrome(s));
        s = "abcdcbb";
        System.out.println(p.isPalindrome(s));
        
    }
}

class PalinPerm {

    /*
    4/10
    From CtCI, p. 198
    The idea here is that any palindrome has the following properties:
        - for any character, that character MUST occur an even number of times, EXCEPT that one character MAY occur an
        odd number of times, e.g., ppdddpp.
    So, use a bit vector to track how many times a given char has occurred.  Each time you see a given char, toggle that
    bit (ascii value) in the vector.  If you end up with 0 or 1 bits set in the vector, it's a palindrome (or a 
    permutation of a palindrome).
     */
    boolean isPalindrome(String s) {
        int bitVector = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = charToBitNum(Character.toLowerCase(s.charAt(i)));
            bitVector = toggle(bitVector, c);
        }
        return bitVector == 0 || oneBitSet(bitVector);
    }

    // If an int only has 1 bit set, subtracting 1 will create an int full of 1's to the right of the original 1.
    // and'ing that number to itself will result in 0.  If not, there's at least one other bit set somewhere...
    private boolean oneBitSet(int bitVector) {
        return (bitVector & (bitVector - 1)) == 0;
    }

    private int toggle(int vector, int index) {
        if (index < 0) return vector;
        int mask = 1 << index;  // shift '1' left by the number of the index
        if ((vector & mask) == 0)  // bit not set
            vector |= mask;  // set it to 1
        else
            vector &= ~mask;  // set it to 0
        return vector;
    }
    
    private int charToBitNum(char convert) {
        int a = (int)'a';
        int z = (int)'z';
        int c = (int)convert;

        if (a <= c && c <= z)
            return c - a; // start at 0 for vector
        else
            return -1;
    }

    // My first attempt -- not bad
    boolean isPalindromeSet(String s) {
        Set<Character> record = new HashSet<>();
        int charCount = 0; // need a separate count because of spaces
        for (int i = 0; i < s.length(); i++) {
            char c = Character.toLowerCase(s.charAt(i));
            if (c == ' ')
                continue;
            charCount++;
            if (record.contains(c))
                record.remove(c);
            else
                record.add(c);
        }
        
        // Note that we don't need all of this.  If size is 0 or 1, it must be a palindrome.
        // If a string of even length has 1 bad char, it must have at least 2.  A string of odd length cannot have
        // 0 bad chars....  So, the correct check is: record.size() == 0 or record.size() == 1.
        if (charCount % 2 == 0) {
            return record.size() == 0;
        } else
            return record.size() == 1;
    }
}