package org.haughki.codeLibrary.programmingProblems.hackerRank;

import java.util.Scanner;

public class TheHurdleRace {
    public static void main(String[] args) {
        //String input = "5 4 1 6 3 5 2";
        String input = "5 7 2 5 4 5 2";
        
        
        Scanner in = new Scanner(input);

        //Scanner in = new Scanner(System.in);
        int numHurd = in.nextInt();
        int maxJump = in.nextInt();
        int count = 0;
        for (int i = 0; i < numHurd; i++) {
            int height = in.nextInt();
            int diff = height - maxJump;
            if (diff > 0) {
                count += diff;
                maxJump = height;
            }
        }

        System.out.println(count);

    }
}
