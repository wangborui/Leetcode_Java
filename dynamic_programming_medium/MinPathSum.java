/*
Source: https://leetcode.com/problems/minimum-path-sum/

********************************************************************************
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
********************************************************************************

Solution:
1. Time O(m * n) Space(m * n)
2. Time O(m * n) Space(m)
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class MinPathSum {

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        //sum[i][j] means the min path sum from grid[0][0] to i,j
        int[][] sum = new int[m][n];

        //initializing starting point
        sum[0][0] = grid[0][0];

        //initializing first row
        for (int i = 1; i < n; i++) {
            sum[0][i] = grid[0][i] + sum[0][i - 1];
        }

        //initializing first col
        for (int i = 1; i < m; i++) {
            sum[i][0] = grid[i][0] + sum[i - 1][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                sum[i][j] = Math.min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
            }
        }

        return sum[m - 1][n - 1];
    }

    public int minPathSumSpaceOptimized(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] sum = new int[n];
        sum[0] = grid[0][0];
        
        //initializationn
        for(int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + grid[0][i]; 
        }
        //sum[i][j] means the min path sum from grid[0][0] to i,j
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(j == 0) {
                    sum[j] = sum[j] + grid[i][j];
                } else {
                    sum[j] = Math.min(sum[j], sum[j - 1]) + grid[i][j];
                }
            }
        }
        return sum[n - 1];

    }
}
