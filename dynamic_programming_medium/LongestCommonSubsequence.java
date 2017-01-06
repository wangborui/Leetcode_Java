/*
Source: http://www.lintcode.com/en/problem/longest-common-subsequence/ 
Date  : 01/05/2017
********************************************************************************
What's the definition of Longest Common Subsequence?

https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
http://baike.baidu.com/view/2020307.htm
Example
For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.

For "ABCD" and "EACB", the LCS is "AC", return 2.
********************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class LongestCommonSubsequence {
     /**
     * @param A, B: Two strings.
     * @return: The length of longest common subsequence of A and B.
     * use double for loop to iterate A and B
     * 
     * f[0][j] = 0 means choose a = "" to match any b.substring(0, j), which is always 0 LCS
     * f[i][0] = 0 means choose b = "" to match any a.substring(0, i), which is always 0 LCS
     * f[i][j] means the longest common subsequence between previous i from string a[0...i - 1] and previous j from string b[0...j - 1]
     * 
     * So, the optimal function is the following
     *      f[i][j] = f[i - 1][j - 1] + 1           : a.charAt(i - 1) == b.charAt(j - 1)
     *              = max(f[i - 1][j], f[i][j - 1]) : a.charAt(i - 1) != b.charAt(j - 1)
     *                                                this chooses max LCS from not choosing a[i - 1] or b[j - 1]
     */
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        int n = A.length();
        int m = B.length();

        //initialization f[i][0] = 0, and f[0][j] = 0;
        int [][] longest = new int[n + 1][m + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    longest[i][j] = longest[i - 1][j - 1] + 1;
                } else {
                    longest[i][j] = Math.max(longest[i - 1][j], longest[i][j - 1]);
                }
            }
        }
        return longest[n][m];
    }
}
