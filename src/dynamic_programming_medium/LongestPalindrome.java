// Source : https://oj.leetcode.com/problems/longest-palindromic-substring/
// Date   : 04/04/2017
/**
 * ********************************************************************************
 *
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000,
 * and there exists one unique longest palindromic substring.
 *
 *********************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class LongestPalindrome {

    static String longestPalindrome(String s) {
        //Construct a matrix, and consdier matrix[j][i] as s[i] -> s[j] is Palindrome or not.
        //                                 ------^^^^^^
        //                                 NOTE: it's [j][i] not [i][j]

        //Using vector  could cause the `Time Limit Error`
        //So, use the native array
        String longestPal = "";
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        // Dynamic Programming
        //   1) if i == j, then matrix[i][j] = true;
        //   2) if i != j, then matrix[i][j] = (s[i]==s[j] && matrix[i-1][j+1])
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                char first = s.charAt(i);
                char sec = s.charAt(j);
                // The following if statement can be broken to
                //   1) j==i, matrix[i][j] = true
                //   2) the length from j to i is 2 or 3, then, check s[i] == s[j]
                //   3) the length from j to i > 3, then, check s[i]==s[j] && matrix[i-1][j+1]
                if (first == sec && (j - i <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (j - i + 1 > longestPal.length()) {
                        longestPal = s.substring(i, j + 1);
                    }
                }
            }
        }
        print(dp);
        return longestPal;
    }

    static void print(boolean[][] dp) {
        for (boolean[] b : dp) {
            for (boolean boo : b) {
                System.out.print((boo ? "T" : "F") + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        System.out.println(longestPalindrome("abcda"));
    }
}
