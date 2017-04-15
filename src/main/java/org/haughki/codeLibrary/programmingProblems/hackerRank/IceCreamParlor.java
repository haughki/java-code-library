package org.haughki.codeLibrary.programmingProblems.hackerRank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//https://www.hackerrank.com/challenges/icecream-parlor
public class IceCreamParlor {
    public static void main(String[] args) {
        String input = "2\n" +
                "4\n" +
                "5\n" +
                "1 4 5 3 2\n" +
                "4\n" +
                "4\n" +
                "2 2 4 3";
        Scanner in = new Scanner(input);

        //Scanner in = new Scanner(System.in);
        int trips = in.nextInt();
        for (int i = 0; i < trips; i++) {
            float money = in.nextFloat();    
            int flavors = in.nextInt();
            Map<Float, Integer> record = new HashMap<>(flavors);
            for (int j = 0; j < flavors; j++) {
                float cost = in.nextFloat();
                Integer foundId = record.get(cost);
                if (foundId == null)
                    record.put(money - cost, j + 1);
                else {
                    System.out.println(foundId + " " + (j + 1));
                }
            }
        }
    }
}
