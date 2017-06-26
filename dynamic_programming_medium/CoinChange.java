/*
Source:https://leetcode.com/problems/coin-change/#/description
Date  : 06/26/2017
********************************************************************************
You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin.
********************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
public class CoinChange {
     static int coinChange(int[] coins, int amount) {
        int [] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i<=amount;i++){
            for(int coin:coins){
                if(coin<=i){
                    dp[i] = Math.min(dp[i],dp[i-coin]+1);
                }
            }
        }
        return dp[amount] > amount?-1:dp[amount];
    }
     public static void main(String[] args){
         System.out.println(coinChange(new int[]{2},3));
     }
}
