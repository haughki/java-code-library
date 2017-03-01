package org.haughki.codeLibrary.algorithm;

public class RecursiveTimeComplexity {
    public static void main(String[] args) {
        System.out.println(recursiveFun3(25));
        System.out.println(recursiveFun3(125));
        System.out.println(recursiveFun3(625));
        System.out.println(recursiveFun3(3125));
        System.out.println(recursiveFun3(15625));
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
        {
            System.out.println(String.format("%d, %d\n", m, o));
        }
        else
        {
            recursiveFun4(n-1, m+1, o);
            recursiveFun4(n-1, m, o+1);
        }
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
    
}
