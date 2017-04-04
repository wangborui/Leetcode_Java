// Source : https://oj.leetcode.com/problems/best-time-to-buy-and-sell-stock/
// Date   : 01/10/2017

/********************************************************************************** 
* 
* Say you have an array for which the ith element is the price of a given stock on day i.
* 
* If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
* design an algorithm to find the maximum profit.
*               
*********************************************************************************
Dynamic Programming Time O(n) Space O(1)
*/
package Leetcode_Java.arrays_medium;

import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Borui Wang
 */
public class MaxProfit {
//find the largest price difference between peak price and trough price, make sure trough happens before peak price
//traverse all price points, for each price i, assume we sell at price i, and buy at min price from prices[0:i - 1]
//update global profit if i - min(prices[0 : i - 1])
//    建立两个变量股票最低交易额和最大利润额
//    遍历整个价格数组，如果当前价格小于最低交易额，就把当前价格设置成最低交易额
//    用当前价格减去最低交易额，更新最大利润额
    public int maxProfit(int[] prices) {
        //if we do not buy at all we get 0 dollars as profit
        int max = 0;
        int localMin = Integer.MAX_VALUE;
        
        for(int price : prices) {
            localMin = Math.min(price, localMin);
            max = Math.max(price - localMin, max);
        }
        
        return max;
    }
    public static void main(String[] args){
        int [] test = {7,6};
        StdOut.println(new MaxProfit().maxProfit(test));
    }
}
