package org.haughki.codeLibrary.programmingProblems.hackerRank;

import java.util.Scanner;

//4/6
//https://www.hackerrank.com/challenges/funny-string
public class FunnyString {
    public static void main(String[] args) {
        String input = "2 acxz bcxz";
        Scanner in = new Scanner(input);

        //Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        for (int i = 0; i < size; i++) {
            String curr = in.next();
            boolean funny = true;
            int k = curr.length() - 1;
            for (int j = 0; j < curr.length(); j++) {
                if (j > 0) {
                    int forward = curr.charAt(j);
                    int forPrev = curr.charAt(j - 1);
                    int reverse = curr.charAt(k);
                    int revPrev = curr.charAt(k + 1);
                    
                    if (Math.abs(forward - forPrev) != Math.abs(reverse - revPrev)) {
                        System.out.println("Not Funny");
                        funny = false;
                        break;
                    }
                }
                k--;
            }
            if (funny)
                System.out.println("Funny");
        }
    }
    
}
