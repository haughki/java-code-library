package org.haughki.codeLibrary.programmingProblems.arraysAndStrings;


// https://leetcode.com/problems/longest-substring-without-repeating-characters/#/description
/* Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/
import java.util.HashMap;

public class LongestSubstringWithoutRepeating {
    public static void main(String[] args) {
        LongestSub l = new LongestSub();
        System.out.println(l.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(l.lengthOfLongestSubstring("bbbbbb"));
        System.out.println(l.lengthOfLongestSubstring("pwwkew"));
    }
}

class LongestSub{

    // top leetcode submission.  O(n) using hashmap
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int max=0;
        for (int i=0, j=0; i < s.length(); ++i){
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }
    
    
    // first try.  accepted, but 0(n^2) because for the nested search.
    public int lengthOfLongestSubstring_0(String s) {
        int back = 0;
        int currentLength = 0;
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            int firsti = indexInRegion(curr, back, i - 1, s);
            if (firsti > -1) {
                back = firsti + 1;
                currentLength = i - firsti;
            } else {
                currentLength++;
                max = Math.max(max, currentLength);
            }
        }
        return max;
    }

    private int indexInRegion(char find, int start, int end, String s) {
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) == find)
                return i;
        }
        return -1;
    }
} 