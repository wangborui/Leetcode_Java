// Source : https://oj.leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
// Date   : 01/11/2017

/********************************************************************************** 
* 
* Say you have an array for which the ith element is the price of a given stock on day i.
* 
* Design an algorithm to find the maximum profit. You may complete at most two transactions.
* 
* Note:
* You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
*               
**********************************************************************************/
package Leetcode_Java.arrays_medium;

/**
 *
 * @author Borui Wang
 */
 
//     Dynamic Programming
//    
//         Considering prices[n], and we have a position "i", we could have
//           1) the maxProfit1 for prices[0..i], we sell on ith day and buy on min price from prices[0..i]
//           2) the maxProfit2 for proices[i..n], we buy on ith day and sell on max price from [i..n]
//    
//        So, 
//          for 1) we can go through the prices[n] forwardly.
//              forward[i] = max( forward[i-1], price[i] - lowestPrice[0..i] ) 
//          for 2) we can go through the prices[n] backwoardly.
//              backward[i] = max( backward[i+1], highestPrice[i..n] - price[i]) 
//      For example, if we have the following and go forwardly
//                prices    = [3, 8, 10, 5, 7]
//                min       = [3, 3,  3, 3, 3]
//                forward   = [0, 5,  7, 7, 7]
//      Go backward
//                max       = [10, 10, 10, 7, 7]
//                backward  = [7 , 2, 2, 2, 0]
//                globalMax = [7,  7, 9, 9, 9]
public class MaxProfit3 {
    static int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) {
            return 0;
        }
        
        int n = prices.length;
        //max profit on ith day if we sell on ith day and buy from left to right on the min price day
        int [] left = new int[n];
        //max profit on ith day if we buy on the ith day and sell on the max price from ith to end of the array day
        int [] right = new int[n];
        
        //dp from left to right
        int min = prices[0];
        left[0] = 0;
        for(int i = 1; i < n; i++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }
        
        //dp from right to left
        int max = prices[n - 1];
        right[n - 1] = 0;
        for(int i = n - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }
        //find global max
        int maxProf = 0;
        for(int i = 0; i < n; i++) {
            maxProf = Math.max(left[i] + right[i], maxProf);
        }
        return maxProf;
    }
    public static void main(String[] args) {
        maxProfit(new int[]{3,8,10,5,7});
    }
}
