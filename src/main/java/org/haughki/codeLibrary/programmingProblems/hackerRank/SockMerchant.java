package org.haughki.codeLibrary.programmingProblems.hackerRank;


// https://www.hackerrank.com/challenges/sock-merchant

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SockMerchant {
    public static void main(String[] args) {
        String input = "9 10 20 20 10 10 30 50 10 20";
        Scanner in = new Scanner(input);

        //Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        Map<Integer, Integer> socks = new HashMap<>(size);
        socks.put(in.nextInt(), 1);
        int pairs = 0;
        for (int i = 0; i < size - 1; i++) {
            Integer curr = in.nextInt();
            Integer count = socks.get(curr);
            if (count == null)
                socks.put(curr, 1);
            else {
                count++;
                if (! ((count & 1) == 1))
                    pairs++;
                socks.put(curr, count);
            }
        }

        System.out.println(pairs);

    }
}
