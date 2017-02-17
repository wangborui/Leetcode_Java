//Source: https://leetcode.com/problems/climbing-stairs/
//Date  : 02/17/2017

/*
********************************************************************************
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
********************************************************************************
 */
package Leetcode_Java.dynamic_programming_easy;

/**
 *
 * @author Borui Wang
 */
public class ClimbingStairs {
    public int climbStairs(int n) {
        if(n < 2) {
            return 1;
        } 
        int[] ways = new int[n + 1];
        ways[0] = 1;
        ways[1] = 1;
        
        for(int i = 2; i <= n; i++) {
            ways[i] = ways[i - 1] + ways[i - 2];
        }
        return ways[n];
    }
    //exceeds time limit
    public int climbStairs2(int n) {
        if(n < 2) {
            return 1;
        }
        return climbStairs2(n - 1) + climbStairs2(n - 2);
    }
    //Time O(n) Space O(1)
    public int climbStairsOptimizedSpace(int n) {
        int[] dp =  new int[2];
        dp[0] = 1;
        dp[1] = 1;
        
        for(int i = 2; i <= n; i++) {
            dp[i % 2] = dp[(i - 1) % 2] + dp[(i - 2) % 2];
        }
        return dp[n % 2];
    }
}
