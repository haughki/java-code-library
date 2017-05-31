package org.haughki.codeLibrary.programmingProblems.hackerRank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.haughki.codeLibrary.aacommon.SysOutCapture;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
https://www.hackerrank.com/challenges/between-two-sets
 */
public class BetweenTwoSets {
    private SysOutCapture sysOutCapture;

    @Before
    public void setUp() {
        sysOutCapture = new SysOutCapture();
    }

    @After
    public void tearDown() throws Exception {
        sysOutCapture.close();
    }


    @Test
    public void simpleHappyPath() {
        String input = "2 3\n" +
                "2 4\n" +
                "16 32 96";

        between(input);
        Assert.assertEquals("3", sysOutCapture.value());
    }

    @Test
    public void noFactors() {
        String input = "0 3\n" +
                "16 32 96";

        between(input);
        Assert.assertEquals("0", sysOutCapture.value());
    }

    @Test
    public void noFactorables() {
        String input = "2 0\n" +
                "2 4";

        between(input);
        Assert.assertEquals("0", sysOutCapture.value());
    }

    @Test
    public void primeNope() {
        String input = "4 1\n" +
                "1 2 4 6\n" +
                "53";

        between(input);
        Assert.assertEquals("0", sysOutCapture.value());
    }

    @Test
    public void primeYup() {
        String input = "1 1\n" +
                "1\n" +
                "53";

        between(input);
        Assert.assertEquals("2", sysOutCapture.value());
    }

    
    // my best attempt below.  A better solution on HackerRank:
    // https://www.hackerrank.com/challenges/between-two-sets/forum/comments/214974
    // from that post:
    // "O(n log(n)) solution.
    // 1. find the LCM of all the integers of array A.
    // 2. find the GCD of all the integers of array B.
    // 3. Count the number of multiples of LCM that evenly divides the GCD."
    private static void between(String input) {
        Scanner in = new Scanner(input);
        //Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = in.nextInt();
        }

        if (a.length < 1 || b.length < 1) {
            System.out.print("0");
            return;
        }
        
        int a0 = a[0];
        List<Integer> factorable = new ArrayList<>();
        for (int i = 1; i < 101 ; i++) {
            if (i % a0 == 0)
                factorable.add(i);
        }

        for (int i = 1; i < a.length; i++) {
            Iterator<Integer> fiterator = factorable.iterator();
            int fcurr;
            while (fiterator.hasNext()) {
                fcurr = fiterator.next();
                if (fcurr % a[i] != 0)
                    fiterator.remove();
            }
        }

        for (int i = 0; i < b.length; i++) {
            Iterator<Integer> fiterator = factorable.iterator();
            int fcurr;
            while (fiterator.hasNext()) {
                fcurr = fiterator.next();
                if (b[i] % fcurr != 0)
                    fiterator.remove();
            }
        }
        System.out.print(factorable.size());
    }
}