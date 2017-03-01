package org.haughki.codeLibrary.programmingProblems.crackingCoding;

/*
Crack the Coding Interview, p. 91.
 */
class OneAway {
    private final String s1;
    private final String s2;
    private int numDifferences = 0;
    
    OneAway(String _s1, String _s2) {
        s1 = _s1;
        s2 = _s2;
    }
    
    boolean isOneAway() {
        // compare the two strings in parallel
        // if the chars are equal, continue
        // else, look at the next char in each string
            // if they are equal, increment differences, check if > 1, continue
            // else, look at next char in each string, holding the index of the other
                // if they are equal, increment differences, check if > 1, continue
        
        if (Math.abs(s1.length() - s2.length()) > 1)
            return false;
        
        int j = 0;
        for (int i = 0; i < s1.length(); i++) {
            int s1Curr = s1.codePointAt(i);
            int s2Curr;
            if (j < s2.length())
                s2Curr = s2.codePointAt(j);
            else {
                return stillOneAway();  // s2 is incremented out, s1 is not -- this constitutes a difference.
            }                           // In any case, we're done
            
            if (s1Curr == s2Curr) {
                j++;
                continue;
            }
            if(!stillOneAway())  // if they're not equal, we've found a difference -- maybe we can just be done
                return false;

            
            // first difference, not equal indexed chars, not at end of either string
            {
                int nexti = i + 1;
                int nextj = j + 1;
                if (nexti >= s1.length() && nextj >= s2.length()) // we are at end of both arrays with only one difference (this one)
                    return true;
                else {  // nexti < s1Length && nextj < s2Length
                    if (s1.codePointAt(nexti) == s2.codePointAt(nextj)) {
                        j++;
                    } else if (s1Curr == s2.codePointAt(nextj)) {
                        i--;
                        j++;
                    } else if (!(s2Curr == s1.codePointAt(nexti)))
                        return false;
                }
            }
        }
        
        return numDifferences < 2;
    }

    private boolean stillOneAway() {
        numDifferences++; // s2 isn't as long as s1, there's a diff
        return numDifferences < 2;
    }
}
