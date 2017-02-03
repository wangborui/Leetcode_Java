//Source : http://www.lintcode.com/en/problem/backpack-ii/
//Date   : 02/02/2017
/**
 * *****************************************************************************
 * Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?
 *
 * Notice
 *
 * You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.
 *
 * Have you met this question in a real interview? Yes
 * Example
 * Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.
 *
 * Challenge
 * O(n x m) memory is acceptable, can you do it in O(m) memory?
 ******************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class Backpack2 {
    /**
     * Same idea as back pack 1
     * @param m
     * @param A
     * @param V
     * @return int
     * Time O(n * m) n is the number of items, m is total weight 
     * Space (n * m)
     */
    public int backPackII(int m, int[] A, int V[]) {
        // write your code here
        if (m == 0 || A == null || V == null || A.length == 0 || V.length == 0) {
            return 0;
        }

        int n = A.length;
        int maxVal = 0;
        //dp[i][j] means take a few or all of first j items with a total size of exactly i that maximizes overall value
        int[][] dp = new int[n + 1][m + 1];

        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int notTake = dp[i - 1][j];
                int take = (j >= A[i - 1]) ? dp[i - 1][j - A[i - 1]] + V[i - 1] : notTake;
                dp[i][j] = Math.max(take, notTake);
                maxVal = Math.max(maxVal, dp[i][j]);
            }
        }

        return maxVal;
    }
    /**
     *
     * @param m
     * @param A
     * @param V
     * @return int
     * Time O(n * m) Space (m) n is the number of items, m is total weight 
     */
    public int backPackIIOptimizedSpace(int m, int[] A, int V[]) {
        // write your code here
        if (m == 0 || A == null || V == null || A.length == 0 || V.length == 0) {
            return 0;
        }

        int n = A.length;
        int maxVal = 0;
        //dp[i][j] means take a few or all of first j items with a total size of exactly i that maximizes overall value
        int[][] dp = new int[2][m + 1];

        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int notTake = dp[(i - 1) % 2][j];
                int take = (j >= A[i - 1]) ? dp[(i - 1) % 2][j - A[i - 1]] + V[i - 1] : notTake;
                dp[i % 2][j] = Math.max(take, notTake);
                maxVal = Math.max(maxVal, dp[i % 2][j]);
            }
        }

        return maxVal;
    }
}
