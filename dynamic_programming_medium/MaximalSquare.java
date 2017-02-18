//Source : https://leetcode.com/problems/maximal-square/?tab=Description
//Date   : 02/17/2017

/*******************************************************************************
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * 
 * For example, given the following matrix:
 * 
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 4.
*******************************************************************************/
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        //init
        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;
        int[][] dp = new int[m][n];

        //init first column
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] - '0';
        }

        //init first row
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j] - '0';
        }

        //dp
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                maxArea = Math.max(dp[i][j] * dp[i][j], maxArea);
            }
        }
        return maxArea;
    }
}
