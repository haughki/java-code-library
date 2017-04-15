package org.haughki.codeLibrary.algorithm;

public class RecursiveTimeComplexity {
    public static void main(String[] args) {
        System.out.println(recurseTwice(3));
    }
    
    /*
    O(n)
    Recurses until n == 0.  Returns 1.  All following returns (n) add 1, so result is n + 1.
     */
    private static int recursiveFun1(int n)
    {
        if (n <= 0)
            return 1;
        else
            return 1 + recursiveFun1(n-1);
    }

    /*
    O(n).  Time complexity still an arithmetic function of n.
     */
    private static int recursiveFun2(int n)
    {
        if (n <= 0)
            return 1;
        else
            return 1 + recursiveFun2(n-5);
    }

    // O(log n)
    // That this is logarithmic is obvious when you draw the tree:  each recursion takes a huge bite out of the
    // remaining work to be done.  It's actually log5(n), because we are dividing n by 5 each time.
    private static int recursiveFun3(int n)
    {
        if (n <= 0)
            return 1;
        else
            return 1 + recursiveFun3(n/5);
    }

    // O(2^n)
    // Each recursion recurses 2 times.  This results in a binary tree of recursive calls. Each step in the recursion
    // reduces n by 1, so we do a total of n recursions, each twice.  If there were 3 calls, it would be O(3^n).
    private static void recursiveFun4(int n, int m, int o)
    {
        if (n <= 0)
            System.out.println(String.format("%d, %d\n", m, o));
        else {
            recursiveFun4(n-1, m+1, o);
            recursiveFun4(n-1, m, o+1);
        }
    }

    // O(2^n)
    // Same as above, but a little clearer.  This counts the time complexity, so that you can "see" the 2^n.  I'm giving
    // +1 to the base case -- in reality there might be much less work going on there, but it's still _something_, and
    // should amount to the same time complexity in either case (but giving the base case +1 make it easier to see
    // the 2^n).
    private static int recurseTwice(int n)
    {
        if (n < 1)
            return 1;
        // this:
//        int count = recurseTwice(n-1);
//        count += recurseTwice(n-1);
//        return ++count;
        // is the same as this:
        return 1 + recurseTwice(n - 1) + recurseTwice(n - 1);
    }

    // O(n^2)
    // For each recursion (O(n)), we loop through n (O(n)).  Just like a nested for loop.
    private static int recursiveFun5(int n)
    {
        for (int i = 0; i < n; i += 2) {
            // do something
        }

        if (n <= 0)
            return 1;
        else
            return 1 + recursiveFun5(n-5);
    }

    // O(n!)?  O(n^4)?  Something terrible.  This is essentially the algorithm for calculating all of the permutations
    // of a string of len m.
    private static int recursiveFun6(int n, int m)
    {
        int count = 0;
        if (n >= m)
            return 0;
        for (int i = n; i < m; i++) {
            count += 1 + recursiveFun6(n + 1, m);
        }
        return count;
    }

}
