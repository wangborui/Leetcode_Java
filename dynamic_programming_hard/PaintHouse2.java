/*
Source: https://leetcode.com/problems/paint-house-ii/#/description
Date  : 07/08/2017
********************************************************************************
There are a row of n houses, each house can be painted with one of the k colors. 
The cost of painting each house with a certain color is different.
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. 
For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Follow up:
Could you solve it in O(nk) runtime?
********************************************************************************
 */
package Leetcode_Java.dynamic_programming_hard;

/**
 *
 * @author Borui Wang
 */
public class PaintHouse2 {

    static int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        int n = costs.length;
        int colors = costs[0].length;
        int min1, min2;
        int idx1, idx2;
        idx1 = idx2 = 0;
        int[][] dp = new int[n + 1][colors];

        //init 
        for (int i = 0; i < colors; i++) {
            dp[0][i] = 0;
        }
        //if there is only one house
        if(n == 1) {
            int min = Integer.MAX_VALUE;
            for(int cost : costs[0]) {
                min = Math.min(min, cost);
            }
            return min;
        }
        for (int i = 1; i <= n; i++) {
            //find min1 and min2 costs
            min1 = Integer.MAX_VALUE;
            min2 = Integer.MAX_VALUE;
            for (int k = 0; k < colors; k++) {
                if (dp[i - 1][k] < min1) {
                    min2 = min1;
                    idx2 = idx1;
                    min1 = dp[i - 1][k];
                    idx1 = k;
                } else if (dp[i - 1][k] < min2) {
                    min2 = dp[i - 1][k];
                    idx2 = k;
                }
            }

            //find min cost for ith house
            for (int j = 0; j < colors; j++) {
                if (idx1 == j) {
                    dp[i][j] = min2 + costs[i - 1][j];
                } else {
                    dp[i][j] = min1 + costs[i - 1][j];
                }
            }
        }

        int minCost = Integer.MAX_VALUE;
        //calculate overall min cost
        for (int i = 0; i < colors; i++) {
            minCost = Math.min(minCost, dp[n][i]);
        }
        return minCost;
    }
    public static void main(String[] args) {
        int[][] c = {{14},{11},{14}};
        System.out.println(minCostII(c));
    }
}
