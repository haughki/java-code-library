package org.haughki.codeLibrary.algorithm;

import java.util.ArrayList;

public class GoDeeper {

    private static int[] VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    private static ArrayList<String> add(int digit, String sign, ArrayList<String> branches) {
        for (int i = 0; i < branches.size(); i++) {
            branches.set(i, digit + sign + branches.get(i));
        }

        return branches;
    }

    private static ArrayList<String> f(int sum, int number, int index) {
        int digit = Math.abs(number % 10);
        if (index >= VALUES.length) {
            if (sum == number) {
                ArrayList<String> result = new ArrayList<>();
                result.add(Integer.toString(digit));
                return result;
            } else {
                return new ArrayList<>();
            }
        }

        ArrayList branch1 = f(sum - number, VALUES[index], index + 1);
        ArrayList branch2 = f(sum - number, -VALUES[index], index + 1);

        int concatenatedNumber = number >= 0
                ? 10 * number + VALUES[index]
                : 10 * number - VALUES[index];
        ArrayList branch3 = f(sum, concatenatedNumber, index + 1);

        ArrayList<String> results = new ArrayList<>();

        results.addAll(add(digit, "+", branch1));
        results.addAll(add(digit, "-", branch2));
        results.addAll(add(digit, "", branch3));

        return results;
    }

    public static void main(String[] args) {
        int TARGET_SUM = 100;
        f(TARGET_SUM, VALUES[0], 1).forEach(System.out::println);
    }
}

