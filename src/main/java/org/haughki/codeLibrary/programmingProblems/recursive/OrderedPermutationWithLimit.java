package org.haughki.codeLibrary.programmingProblems.recursive;

/*
3/29
Problem I made up.  Similar to dynamicMemoized.WordChopperII.  Also related to recursive.BinaryWatch.

Find the order permutations of a string, where each permutation must be of a certain length.

E.g., the two character long ordered permutations of ABCD are:
AB
AC
AD
BC
BD
CD


Solution/Insights - For whatever reason, the trickiest part of this for me is figuring out the "stop" value; i.e.,
where to end the while loop for each recursion.  If you draw out the recursion tree, you can see that the loop ends
+1 greater for each recursion.  So, this seems like a "add one at recursion time" sorta thing.  But, what ends up being
"simplest" is to have the length of the loop be relative to the length of chars; if we do this, the the first loop
is bounded chars.length - (limit - 1). And, if we do *this*, to increase the loop by one for each recurse, we actually
_subtract_ from the number we're taking away from chars.length (i.e., "stop"): we're subtracting one less from the loop
each time, which makes the loop one greater each time.  The weird side-effect of this is that we can use also use stop 
as the base case, since it will always be -1 when we want to print and return.
 */
public class OrderedPermutationWithLimit {
    public static void main(String[] args) {
        OrderedLimit o = new OrderedLimit();
        String chars = "ABCDE";
        int limit = 3;
        o.printPermutations(chars.toCharArray(), "", 0, limit - 1);
    }
}

class OrderedLimit {

    void printPermutations(char[] chars, String s, int i, int stop) {
        if (stop < 0) {
            System.out.println(s);
            return;
        }

        while (i < chars.length - stop) {
            printPermutations(chars, s + chars[i], i + 1, stop - 1);
            i++;
        }
    }
}
