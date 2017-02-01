// Source : https://oj.leetcode.com/problems/interleaving-string/
// Date   : 01/31/2017

/********************************************************************************** 
* 
* Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
* 
* For example,
* Given:
* s1 = "aabcc",
* s2 = "dbbca",
* 
* When s3 = "aadbbcbcac", return true.
* When s3 = "aadbbbaccc", return false.
* 
*               
**********************************************************************************/
package Leetcode_Java.dynamic_programming_hard;

/**
 *
 * @author Borui Wang
 */


public class InterleavingString {
/**
 * 
 * @param s1
 * @param s2
 * @param s3
 * @return boolean
 * Analysis:
 * 
 * Considering: 
 * 
 *      s1 = a1, a2, ...... a(i - 1), size = i
 *      s2 = b1, b2, ...... b(j - 1), size = j
 *      s3 = c1, c2, ...... c(i + j - 1), size = i + j
 * 
 * Defined 
 * 
 *      dp[i][j] means s1[0..i] and s2[0..j] are interleaved with s3[0...i + j - 1]
 *      s3[0...i + j - 1] is either s1[i] or s2[j]
 * 
 * Initialization
 *      
 *      if s1 is "" then dp[0][j] = true if s2[j] = s3[j] and dp[0][j - 1] = true
 *      if s2 is "" then dp[0][j] = true if s1[j] = s3[j] and dp[0][j - 1] = true
 * 
 * Optimal Function
 * 
 *      starting at i = 1 and j = 1
 *      dp[i][j] = true if s1[i - 1] = s3[i + j - 1] AND dp[i - 1][j] = true Or
 *                         s2[j - 1] = s3[i + j - 1] AND dp[i][j - 1] = true
 */
    static boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }

        int len1 = s1.length();
        int len2 = s2.length();

        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;

        //initialization
        for (int i = 1; i <= len1; i++) {
            if (s1.charAt(i - 1) == s3.charAt(i - 1) && dp[i - 1][0]) {
                dp[i][0] = true;
            }
        }
        for (int i = 1; i <= len2; i++) {
            if (s2.charAt(i - 1) == s3.charAt(i - 1) && dp[0][i - 1]) {
                dp[0][i] = true;
            }
        }
        //optimal function
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                char cur = s3.charAt(i + j - 1);
                if (cur == s1.charAt(i - 1) && dp[i - 1][j]
                        || cur == s2.charAt(j - 1) && dp[i][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[len1][len2];
    }
    static void printArray(boolean [][] a) {
        for (int i = 0; i <  a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        System.out.println(isInterleave("axy","aab","aaxaby"));
        System.out.println(isInterleave("aab","axy","abaaxy"));
        System.out.println(isInterleave("aabcc","dbbca","aadbbcbcac"));
    }
}
