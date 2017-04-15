package org.haughki.codeLibrary.programmingProblems.hackerRank;

import java.util.Scanner;

//https://www.hackerrank.com/challenges/find-digits
public class FindDigits {
    public static void main(String[] args) {
        String input = "2 12 1012";
        Scanner in = new Scanner(input);

        //Scanner in = new Scanner(System.in);
        int size = in.nextInt();

        for (int i = 0; i < size; i++) {
            int curr = in.nextInt();
            int count = 0;
            int num = curr;
            while (num > 0) {
                int digit = num % 10;
                if (digit != 0 && curr % digit == 0)
                    count++;
                num = num / 10;
            }
            System.out.println(count);
        }


    }
}
