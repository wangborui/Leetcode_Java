//Source: https://leetcode.com/problems/longest-palindromic-subsequence/description/
//Date  : 09/24/2017
/**
 * *****************************************************************************
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * Input:
 *
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 * Input:
 *
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 ******************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class LongestPalindromeSubseq {

    public int longestPalindromeSubseq1(String s) {
        // Write your code here
        int length = s.length();
        if (length == 0) {
            return 0;
        }
        int[][] dp = new int[length][length];
        for (int i = length - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][length - 1];
    }

    public int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        return helper(s, 0, n - 1, new Integer[n][n]);
    }

    public int helper(String s, int i, int j, Integer[][] f) {
        if (f[i][j] != null) {
            return f[i][j];
        }
        if (i > j) {
            return 0;
        }
        if (i == j) {
            f[i][j] = 1;
            return 1;
        }

        if (s.charAt(i) == s.charAt(j)) {
            f[i][j] = helper(s, i + 1, j - 1, f) + 2;
        } else {
            f[i][j] = Math.max(helper(s, i + 1, j, f), helper(s, i, j - 1, f));
        }

        return f[i][j];
    }

    public int longestPalindromeSubseq3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        //f[i][j] = longest palindrome from s[i] to s[j]
        int[][] f = new int[n][n];
        int max = 1;

        //init
        for (int i = 0; i < n; i++) {
            //s[i] to s[i] has one letter with length 1, is also a palindrome
            f[i][i] = 1;

            //length 2 strings
            if (i + 1 < n) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    f[i][i + 1] = 2;
                } else {
                    f[i][i + 1] = 1;
                }
            }
        }

        for (int len = 2; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                f[i][i + len] = 1;
                if (s.charAt(i) == s.charAt(i + len)) {
                    f[i][i + len] = f[i + 1][i + len - 1] + 2;
                } else {
                    f[i][i + len] = Math.max(f[i + 1][i + len], f[i][i + len - 1]);
                }
                max = Math.max(max, f[i][i + len]);
            }
        }

        return max;
    }
}
