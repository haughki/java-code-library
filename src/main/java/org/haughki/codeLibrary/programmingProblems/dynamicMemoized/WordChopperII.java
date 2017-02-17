package org.haughki.codeLibrary.programmingProblems.dynamicMemoized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Finds all ordered permutations of a string. E.g., "abc" would give:
abc
ab
ac
a
bc
b
c
 */
public class WordChopperII {
    public static void main(String[] args) {
//        List<String> combos = findCombos("abcdefghijklmnop");
//        combos.forEach(System.out::println);

        findCombos2("abcdefghijklmnop");
//        List<List<String>> combos = 
//        for (List<String> c: combos) {
//            for(String s: c) {
//                System.out.println(s);
//            }
//        }
    }

    private static List<List<String>> findCombos2(String s) {
        char[] c = s.toCharArray();
        List<List<String>> combos = new ArrayList<>();

        findCombos2(0, c, s.length(), combos);
        return combos;
    }

    /*
    Memoized version.
     */
    private static List<String> findCombos2(int i, char[] chars, int permus, List<List<String>> record) {
        
        if(record.size() < i + 1) {  // haven't calc'd the permuations for this char yet
            String currentChar = String.valueOf(chars[i]);
            List<String> currentResults = new ArrayList<>(Arrays.asList(currentChar));
            record.add(currentResults);
            int count = 1;
            while (count < permus && i + count < chars.length) {  // short circuits the base condition
                List<String> found = findCombos2(i + count, chars, permus, record);
                for(String s: found) {
                    currentResults.add(currentChar + s);
                }
                count++;
            }
        }
        
        return record.get(i);
    }


    private static List<String> findCombos(String s) {
        char[] c = s.toCharArray();  // much easier to manage...
        List<String> combos = new ArrayList<>();

        // first, loop through each letter; for each, recurse
        for (int i = 0; i < s.length(); i++) {
            combos.add(findCombos(i, c, s.length(), "", combos));
        }
        return combos;
    }
    
    /*
    Pure recursive version.  First attempt.  Memoized version above is significantly faster.
     */
    private static String findCombos(int i, char[] chars, int permus, String result, List<String> output) {
        result = result + String.valueOf(chars[i]);
        int count = 1;
        // recursively call findCombos in a loop, adding one to the "step" each time.
        while (count < permus && i + count < chars.length) {  // short circuits the base condition so that we don't keep trying past the end of the array.
            output.add(findCombos(i + count, chars, permus, result, output));
            count++;
        }

        return result;
    }


}
