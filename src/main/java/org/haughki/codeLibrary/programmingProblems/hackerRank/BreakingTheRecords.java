package org.haughki.codeLibrary.programmingProblems.hackerRank;

import java.util.Scanner;

//https://www.hackerrank.com/challenges/breaking-best-and-worst-records
public class BreakingTheRecords {
    public static void main(String[] args) {
        String input = "9 10 5 20 20 4 5 2 25 1";
        Scanner in = new Scanner(input);

        //Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int first = in.nextInt();
        
        int countLowest = 0;
        int countHighest = 0;
        int lowest = first;
        int highest = first;

        for (int i = 0; i < size - 1; i++) {
            int curr = in.nextInt();
            if (curr > highest) {
                highest = curr;
                countHighest++;
            } else if (curr < lowest) {
                lowest = curr;
                countLowest++;
            }
        }

        System.out.println(countHighest + " " + countLowest);
    }
}
