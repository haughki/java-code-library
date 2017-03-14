package org.haughki.codeLibrary.programmingProblems.recursive;

import java.util.ArrayList;
import java.util.List;


/*
2017.03.13
Old problem.  Combine the numbers 1-9, in order, so that between each number is either a +, -, or nothing.  Which
combination add up to 100?  Print those combinations.

E.g.,
12+3+4+5-6-7+89
123-45-67+89

Solution: the solution I have below is probably not optimal.  Not sure where I got this problem.  Not sure of the best
solution.

This is a complicated little algorithm.  First, note that there are 3 recursions: one for plus, minus, and concat. You
can model this as a tertiary tree.  This creates the permutations:  current number plus, minus, or concated with the
next number.

Plus and minus aren't that tricky:  for each recursion, you track the target value _minus_ the current value (which 
could be pos or neg) -- this gives the number you need to 'hit' on the final base case recursion.  This is the first
trick:  you're not actually trying to do the math "forward" (1 + 2 + 3...) but sortof backwards, always maintaining
a new target to hit with an eventual, final base-case number.

The concatenate case is a little more complicated -- you really just need to look at the code.  In words:  take the
current value times 10 and add or subtract the upcoming value AND don't modify the target.

The weird part is the post-order code which builds up the strings to print.  In this case, REALLY just read the code --
it works by passing the built string back up as the recursion unwinds, and adding the correct new bit of string at
each step; it makes sense, but it's kinda hard to believe it all actually works :)
 */
public class PlusMinusNothing {

    //private static int[] VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static int[] VALUES = {1, 2, 3, 4};
    
    public static void main(String[] args) {
//        int TARGET_SUM = 100;
        int TARGET_SUM = 10;
        validPermus(TARGET_SUM, VALUES[0], 1).forEach(System.out::println);
    }

    private static List<String> validPermus(int sum, int number, int nextIndex) {
        int digit = Math.abs(number % 10);
        if (nextIndex >= VALUES.length) {
            if (sum == number) {
                List<String> result = new ArrayList<>();
                result.add(Integer.toString(digit));
                return result;
            } else {
                return new ArrayList<>();
            }
        }

        List<String> branch1 = validPermus(sum - number, VALUES[nextIndex], nextIndex + 1); // plus
        List<String> branch2 = validPermus(sum - number, -VALUES[nextIndex], nextIndex + 1); // minus

        int concatenatedNumber = number >= 0 ? 10 * number + VALUES[nextIndex] : 10 * number - VALUES[nextIndex];
        List<String> branch3 = validPermus(sum, concatenatedNumber, nextIndex + 1);  // concat

        
        List<String> results = new ArrayList<>();
        results.addAll(add(digit, "+", branch1));
        results.addAll(add(digit, "-", branch2));
        results.addAll(add(digit, "", branch3));

        return results;
    }

    private static List<String> add(int digit, String sign, List<String> branch) {
        for (int i = 0; i < branch.size(); i++) {
            branch.set(i, digit + sign + branch.get(i));
        }

        return branch;
    }
}

