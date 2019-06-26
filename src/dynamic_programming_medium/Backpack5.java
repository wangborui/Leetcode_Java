package dynamic_programming_medium;

/**
 * https://www.lintcode.com/problem/backpack-v/
 * 06/25/2019
 *
 * *****************************************************************************
 * Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
 *
 *
 * You can not divide any item into small pieces.
 *
 * Have you met this question in a real interview? Yes
 * Example
 * If we have 4 items with size [2, 3, 5, 7], the backpack size is 11,
 * we can select [2, 3, 5], so that the max size we can fill this backpack is 10.
 * If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.
 *
 * You function should return the number of ways we can fill in the given backpack.
 * ways: 2
 * [2, 5]
 * [7]
 *
 * Challenge
 * O(n x m) time and O(m) memory.
 *
 * O(n x m) memory is also acceptable if you do not know how to optimize memory.
 ******************************************************************************
 */
public class Backpack5 {
    static int backPack(int m, int[] A) {
        if (m == 0 || A.length == 0 || A == null) {
            return 0;
        }

        int n = A.length;
        //dp[i][j] means for the first i items, not including the ith item, can we take out a few to sum to max weight of j
        int[][] dp = new int[n + 1][m + 1];
        //initialization: for the first 0 items, can we take out a few to sum to total weight of 0? yes, we dont take any
        dp[0][0] = 1;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                //if we take current item, can we sum up to total of j?
                //meaning we we take first i - 1 items, to sum to to max weight of i - A[j - 1]
                int take = 0;
                if(j - A[i - 1] >= 0) {
                    take = dp[i - 1][j - A[i - 1]];
                }
                //if we do not take current item, can we sum up to total of j?
                int notTake = dp[i - 1][j];
                dp[i][j] = take + notTake;
            }
        }

        return dp[n][m];
    }
}
