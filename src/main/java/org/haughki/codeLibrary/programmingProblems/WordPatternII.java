package org.haughki.codeLibrary.programmingProblems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPatternII {
    public static void main(String[] args) {
        WordPatternMatch wpm = new WordPatternMatch();
        System.out.println(wpm.wordPatternMatch("xyxy", "totx"));

    }
}

class WordPatternMatch {
    WordPatternMatch() {}
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        return isMatch(str, 0, pattern, 0, map, set);
    }

    boolean isMatch(String str, int sIndex, String pat, int pIndex, Map<Character, String> map, Set<String> set) {
        if (sIndex == str.length() && pIndex == pat.length()) return true;
        if (sIndex == str.length() || pIndex == pat.length()) return false;

        char pId = pat.charAt(pIndex);  // get current pattern character
        
        if (map.containsKey(pId)) {  // if the pattern character exists
            String pattern = map.get(pId);

            // Is this the pattern again? Try to match the pattern starting at the current sIndex.
            if (!str.startsWith(pattern, sIndex)) 
                return false;

            // if it can match, great: continue to try to match starting after the matched pattern
            return isMatch(str, sIndex + pattern.length(), pat, pIndex + 1, map, set);
        }

        // pattern character does not exist in the map
        for (int k = sIndex; k < str.length(); k++) {
            String possiblePatt = str.substring(sIndex, k + 1);

            if (set.contains(possiblePatt))
                continue;

            map.put(pId, possiblePatt);  // create or update
            set.add(possiblePatt);

            // continue to match the rest
            if (isMatch(str, k + 1, pat, pIndex + 1, map, set))
                return true;

            // backtracking
            map.remove(pId);
            set.remove(possiblePatt);
        }

        // we've tried our best but still no luck
        return false;
    }
}
