package org.haughki.codeLibrary.programmingProblems.arraysAndStrings;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.
Show Company Tags
Show Tags
Show Similar Problems

 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        // 30
        //int[] p = {4, 10, 22, 11, 7, 2, 8, 15, 4, 32};
        //5
        //int[] p = {7, 1, 5, 3, 6, 4};
        //0
        //int[] p = {7, 6, 4, 3, 1};
        //10
        //int[] p = {10, 5, 8, 12, 9, 7, 15, 3, 8};
        //int[] p = {};
        //int[] p = {4};
        int[] p = {4, 7};
        
        BuyLow b = new BuyLow();
        System.out.println(b.maxProfit(p));
    }
}

class BuyLow {
    public int maxProfit(int[] prices) {
        if (prices.length < 1)
            return 0;
        int greatest = 0, max = prices[0];
        int min = max, newMin = max;
        int i = 1;
        while(i < prices.length) {
            while (prices[i] <= newMin) {
                newMin = prices[i];
                if (++i >= prices.length) 
                    return greatest;
            }
            
            if (newMin < min) {
                min = newMin;
                max = min;
            }
            
            while (prices[i] >= max) {
                max = prices[i];
                if (++i >= prices.length)
                    return Math.max(max - min, greatest);
            }
            
            greatest = Math.max(max - min, greatest);
            newMin = max;
        }
        
        return greatest;
    }
}