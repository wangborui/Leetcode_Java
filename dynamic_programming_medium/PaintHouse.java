/*
Source: https://leetcode.com/problems/paint-house/#/description
Date  : 07/04/2017
********************************************************************************
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color red; 
costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.
********************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class PaintHouse {
/**
 * 
 * @param costs
 * @return minimum cost
 *  Method One:
 *                
 *      the optimal strategy is to have minimum cost to paint those houses
 *      As our last step, we must have painted the last house(N - 1) to be red, blue, or green
 *      But neighboring houses can not be painted the same color:
 *          If our last house(N - 1) is painted red, then house N - 2 must be either blue or green
 *          If our last house(N - 1) is painted blue, then house N - 2 must be either red or green
 *          If our last house(N - 1) is painted green, then house N - 2 must be either red or blue
 *      STOP! This is too complicated to keep track of everything
 * 
 * Method Two:
 *      Keep track of minimum cost to paint each house with respective color
 *      Init:
 *              f[0][0] = f[0][1] = f[0][2] = 0
 *      sub-optimal function:
 *          Min cost to paint the first i houses(not including ith house)
 *          Red:
 *              f[i][0] = min(f[i - 1][1] + cost[i - 1][0], f[i - 1][2] + cost[i - 1][0])
 *          Blue:
 *              f[i][1] = min(f[i - 1][0] + cost[i - 1][1], f[i - 1][2] + cost[i - 1][1])
 *          Green:
 *              f[i][2] = min(f[i - 1][1] + cost[i - 1][2], f[i - 1][0] + cost[i - 1][2])
 */
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) {
            return 0;
        }

        int[][] dp = new int[n + 1][3];
        //init
        dp[0][0] = dp[0][1] = dp[0][2] = 0;

        //house from 1 to n
        for (int i = 1; i <= n; i++) {
            //current house color
            for (int j = 0; j < 3; j++) {

                dp[i][j] = Integer.MAX_VALUE;
                //previous house color
                for (int k = 0; k < 3; k++) {
                    if (j == k) {
                        continue;
                    }
                    // last house     cost of current house
                    dp[i][j] = Math.min(dp[i - 1][k] + costs[i - 1][j], dp[i][j]);
                }
            }
        }
        return Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
    }
}
