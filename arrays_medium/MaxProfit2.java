// Source : https://oj.leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
// Date   : 01/10/2017

/********************************************************************************** 
* 
* Say you have an array for which the ith element is the price of a given stock on day i.
* 
* Design an algorithm to find the maximum profit. You may complete as many transactions 
* as you like (ie, buy one and sell one share of the stock multiple times). However, 
* you may not engage in multiple transactions at the same time (ie, you must sell the 
* stock before you buy again).
*               
**********************************************************************************/

package Leetcode_Java.arrays_medium;

/**
 *
 * @author Borui Wang
 */
public class MaxProfit2 {
    // find all of ranges: which start a trough with the nearest peak after
    // add their difference together
 
    static int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
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
            peak = i < prices.length? prices[i++] : trough;
            max += peak - trough;
        }
        return max;
        
    }
    static int maxProfit2(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }
        
        //if we do not buy any stocks
        int total = 0;
        
        for(int i = 0; i < prices.length - 1; i++) {
            if(prices[i] < prices[i + 1]) {
                total += prices[i + 1] - prices[i];
            }
        }
        
        return total;
    }
    public static void main(String[] args){
         System.out.println(maxProfit(new int[]{2,5,2,3,9}));
    }
}
