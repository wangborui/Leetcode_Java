/*
Source:https://leetcode.com/problems/unique-dp/
********************************************************************************
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique dp are there?
 *    start                                                  
 *    +---------+----+----+----+----+----+                   
 *    |----|    |    |    |    |    |    |                   
 *    |----|    |    |    |    |    |    |                   
 *    +----------------------------------+                   
 *    |    |    |    |    |    |    |    |                   
 *    |    |    |    |    |    |    |    |                   
 *    +----------------------------------+                   
 *    |    |    |    |    |    |    |----|                   
 *    |    |    |    |    |    |    |----|                   
 *    +----+----+----+----+----+---------+                   
 *                                   finish 

Above is a 3 x 7 grid. How many possible unique dp are there?

Note: m and n will be at most 100.
********************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class UniquePaths {
 /*
 * Dynamic Programming
 *
 * We have a dp[i][j] represents  how many dp from [0][0] to here. So, we have the following DP formula:
 *
 *    dp[i][j] =  1  if i==0 || j==0        //the first row/column only have 1 uniqe path.
 *             =  dp[i-1][j] + dp[i][j-1]   //the path can be from my top cell and left cell.
 */
    public int uniquePaths(int m, int n) {
        int [][] dp = new int[m][n];
        
        //set first row to 1
        for(int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        
        //set first col to 1
        for(int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        // dp[i][j] = dp[i-1][j] + dp[i][j-1]
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        
        return dp[m - 1][n - 1];
    }
}
