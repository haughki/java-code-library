package org.haughki.codeLibrary.programmingProblems.hackerRank;


import org.haughki.codeLibrary.aacommon.SysOutCapture;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

// https://www.hackerrank.com/challenges/picking-numbers
public class PickingNumbers {
    private SysOutCapture sysOutCapture;

    @Before
    public void setUp() {
        sysOutCapture = new SysOutCapture();
    }

    @After
    public void tearDown() throws Exception { sysOutCapture.close(); }


    @Test
    public void basic1() {
        String input = "6\n" +
            "4 6 5 3 3 1";
        helper(input);
        // {4,3,3}
        Assert.assertEquals("3", sysOutCapture.value());
    }

    @Test
    public void basic2() {
        String input = "6\n" +
                "1 2 2 3 1 2";
        helper(input);
        // {1,2,2,1,2}
        Assert.assertEquals("5", sysOutCapture.value());
    }

    private static void helper(String input) {
        Scanner in = new Scanner(input);
        //Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int longest = 0;
        for (int i = 0; i < n; i++) {
            int ival = a[i];
            int countPlus1 = 1;
            int countMinus1 = 1;
            for (int j = i + 1; j < n; j++) {
                int jval = a[j];
                if (ival == jval) {
                    countPlus1++;
                    countMinus1++;
                } else if (jval - ival == 1) {
                    countPlus1++;
                } else if (ival - jval == 1)
                    countMinus1++;
            }
            longest = Math.max(longest, Math.max(countPlus1, countMinus1));
        }

        System.out.print(longest);
        
    }
}

