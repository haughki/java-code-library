package org.haughki.codeLibrary.programmingProblems.hackerRank;

import java.math.BigInteger;
import java.util.Scanner;

//https://www.hackerrank.com/challenges/fibonacci-modified
public class FibonacciModified {
    public static void main(String[] args) {
        String input = "2 2 10";
        Scanner in = new Scanner(input);

        //Scanner in = new Scanner(System.in);
        BigInteger first = BigInteger.valueOf(in.nextLong());
        BigInteger second = BigInteger.valueOf(in.nextLong());
        int n = in.nextInt();
        BigInteger next = BigInteger.ZERO;
        for (int i = 0; i < n - 2; i++) {
            next = first.add(second.pow(2));
            first = second;
            second = next;
        }
        System.out.println(next.toString());
    }
}
