// Source : https://oj.leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
// Date   : 01/10/2017
/**
 * ********************************************************************************
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions
 * as you like (ie, buy one and sell one share of the stock multiple times). However,
 * you may not engage in multiple transactions at the same time (ie, you must sell the
 * stock before you buy again).
 *
 *********************************************************************************
 */
package Leetcode_Java.arrays_medium;

/**
 *
 * @author Borui Wang
 */
public class MaxProfit2 {
    // find all of ranges: which start a trough with the nearest peak after
    // add their difference together

    static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int trough = prices[0];
        int peak = prices[0];
        //if we do not make any money
        int max = 0;
        int i = 0;
        while (i < prices.length) {
            //find trough
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            //if it does not enter while loop, value of i will never change
            //increment i to avoid infinite loop
            trough = prices[i++];

            //find peak
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            //if it does not enter while loop, value of i will never change
            //increment i to avoid infinite loop, but i could already be out of bound 
            peak = i < prices.length ? prices[i++] : trough;
            max += peak - trough;
        }
        return max;

    }
//    参考：https://leetcode.com/media/original_images/122_maxprofit_2.PNG
//    因为我们能买卖股票无限次，所以使用贪心算法在每次能赚钱的时候买进和买出就好了
//    这种情况就是当前价格大于前一天价格的时候，前一天买进，现在卖出就可以了
    static int maxProfit2(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                max += prices[i] - prices[i - 1];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{2, 5, 2, 3, 9}));
    }
}
