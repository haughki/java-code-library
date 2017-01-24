package org.haughki.codeLibrary.programmingProblems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters.
 */

public class WordPatternII {
    public static void main(String[] args) {
        WordPatternMatch wpm = new WordPatternMatch();
        System.out.println(wpm.wordPatternMatch("xyxy", "teoteo"));

    }
}

class WordPatternMatch {
    WordPatternMatch() {}
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return isMatch(str, 0, pattern, 0, map, set);
    }

    boolean isMatch2(String str, int sIndex, String pat, int pIndex, Map<Character, String> map) {
        if (sIndex == str.length() && pIndex == pat.length()) return true;
        if (sIndex == str.length() || pIndex == pat.length()) return false;

        char pattKey = pat.charAt(pIndex);  // get current pattern character

        String prevMatch = map.get(pattKey);
        if (prevMatch != null) {  // if the pattern character exists

            // Is this the match again? Try to match it starting at the current sIndex.
            int j = 0;
            for (int i = sIndex; i < sIndex + prevMatch.length(); i++) {
                if (prevMatch.charAt(j) != str.charAt(i))
                    return false;
            }
//            if (!str.startsWith(prevMatch, sIndex))
//                return false;

            return isMatch2(str, sIndex + prevMatch.length(), pat, pIndex + 1, map);
        }

        // pattern character does not exist in the map
        String possMatch = String.valueOf(str.charAt(sIndex));

        map.put(pattKey, possMatch);

        if (isMatch2(str, sIndex + 1, pat, pIndex + 1, map))
            return true;

        //map.remove(pId);

        return false;
    }

    boolean isMatch(String str, int sIndex, String pat, int pIndex, Map<Character, String> map, Set<String> set) {
        if (sIndex == str.length() && pIndex == pat.length()) return true;
        if (sIndex == str.length() || pIndex == pat.length()) return false;

        char pattKey = pat.charAt(pIndex);  // get current pattern character
        
        if (map.containsKey(pattKey)) {
            String prevMatch = map.get(pattKey);

            // Is this the pattern again? Try to match the pattern starting at the current sIndex.
            if (!str.startsWith(prevMatch, sIndex)) 
                return false;

            return isMatch(str, sIndex + prevMatch.length(), pat, pIndex + 1, map, set);
        }

        for (int k = sIndex; k < str.length(); k++) {  // pattern character does not exist in the map
            String possMatch = str.substring(sIndex, k + 1);

            if (set.contains(possMatch))
                continue;

            map.put(pattKey, possMatch);  // create or update
            set.add(possMatch);

            if (isMatch(str, k + 1, pat, pIndex + 1, map, set))
                return true;

            map.remove(pattKey);
            set.remove(possMatch);
        }

        return false;
    }
}
