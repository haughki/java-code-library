package org.haughki.codeLibrary.programmingProblems.hackerRank;

import java.util.Scanner;
import org.haughki.codeLibrary.aacommon.SysOutCapture;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Kangaroo {
    private SysOutCapture sysOutCapture;
    
    @Before
    public void setUp() {
        sysOutCapture = new SysOutCapture();
    }

    @After
    public void tearDown() throws Exception { sysOutCapture.close(); }


    @Test
    public void simpleHappyPath() {
        // meets at 12
        String input = "0 3 4 2";
        Scanner in = new Scanner(input);
        int x1 = in.nextInt();
        int v1 = in.nextInt();
        int x2 = in.nextInt();
        int v2 = in.nextInt();

        kangarooHelper(x1, v1, x2, v2);
        
        Assert.assertEquals("YES\r\n", sysOutCapture.value());
    }

    @Test
    public void sameVdifferentX() {
        String input = "43 2 70 2";
        Scanner in = new Scanner(input);
        int x1 = in.nextInt();
        int v1 = in.nextInt();
        int x2 = in.nextInt();
        int v2 = in.nextInt();

        kangarooHelper(x1, v1, x2, v2);

        Assert.assertEquals("NO\r\n", sysOutCapture.value());
    }

    public static void main(String[] args) {
//        String input = "43 2 70 2";  // NO
        String input = "0 3 4 2";
        Scanner in = new Scanner(input);

        //Scanner in = new Scanner(System.in);
        int x1 = in.nextInt();
        int v1 = in.nextInt();
        int x2 = in.nextInt();
        int v2 = in.nextInt();

        kangarooHelper(x1, v1, x2, v2);
    }

    // https://www.hackerrank.com/challenges/kangaroo/forum/comments/292850
    // Just grabbed this from the HackerRank comments.  There are many like it.
    // https://www.hackerrank.com/challenges/kangaroo/forum/comments/151726
    // "We just need solve equation : x1 + y * v1 = x2 + y * v2 where "y" is number of jumps... 
    // so if (x1 - x2) % (v2 - v1) == 0 then our kangaroos will meet each other : )
    private static void kangarooHelper(int x1, int v1, int x2, int v2) {
        if((x1 <= x2 && v1 <= v2) || (x1 >= x2 && v1 >= v2)){ 
            System.out.println("NO"); 
        } else { 
            if ((x2 - x1) % (v1 - v2) == 0)
                System.out.println("YES"); 
            else 
                System.out.println("NO"); }
    }
    
    // my best attempt.  I knew there must be a mathmatecal solution, but didn't get it *sniff*
    private static void kangarooHelperBEST(int x1, int v1, int x2, int v2) {
        if (v1 == v2) {
            if (x1 != x2) {
                System.out.println("NO");
                return;
            }
        }

        while (x1 != x2) {
            if (x1 >= x2) {
                if (v1 > v2) {
                    System.out.println("NO");
                    return;
                }
            } else if (v2 > v1) {
                System.out.println("NO");
                return;
            }
            x1 += v1;
            x2 += v2;
        }

        System.out.println("YES");
    }
}
