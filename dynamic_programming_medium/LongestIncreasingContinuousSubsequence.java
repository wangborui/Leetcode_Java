//Source : http://www.lintcode.com/en/problem/longest-increasing-continuous-subsequence/
//Date   : 02/19/2017

/**
 * ******************************************************************************
 * Give an integer array，find the longest increasing continuous subsequence in this array.
 *
 * An increasing continuous subsequence:
 *
 * Can be from right to left or from left to right.
 * Indices of the integers in the subsequence should be continuous.
 ******************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
public class LongestIncreasingContinuousSubsequence {

    /**
     * @param A an array of Integer
     * @return an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[] dp = new int[n];
        int max = 1;
        Arrays.fill(dp, 1);

        //left to right scan
        for (int i = 1; i < n; i++) {
            if (A[i] < A[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                max = Math.max(max, dp[i]);
            }
        }

        Arrays.fill(dp, 1);
        //right to left scan
        for (int i = n - 2; i >= 0; i--) {
            if (A[i] < A[i + 1]) {
                dp[i] = dp[i + 1] + 1;
                max = Math.max(max, dp[i]);
            }
        }

        return max;
    }
}
