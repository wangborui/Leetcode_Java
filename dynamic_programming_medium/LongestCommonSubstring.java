/*
Source: http://www.lintcode.com/en/problem/longest-common-substring/
Date  : 01/06/2017

********************************************************************************
Given two strings, find the longest common substring.

Return the length of it.
********************************************************************************
for example a = "abcd" b="bceh"
   |"" | a | b | c | d
"" | 0 | 0 | 0 | 0 | 0 |
b  | 0 | 0 | 1 | 0 | 0 |
c  | 0 | 0 | 0 | 2 | 0 |
e  | 0 | 0 | 0 | 0 | 0 |
h  | 0 | 0 | 0 | 0 | 0 |

optimal function
f[i][j] = f[i - 1][j - 1] + 1  if a[i - 1] == b[i - 1]
        = 0                    if a[i - 1] != b[i - 1]
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class LongestCommonSubstring {

    public int longestCommonSubstring(String A, String B) {
        // write your code here
        if (A.length() == 0 || B.length() == 0) {
            return 0;
        }
        int[][] f = new int[A.length() + 1][B.length() + 1];
        int maxLength = Integer.MIN_VALUE;

        for (int i = 1; i <= A.length(); i++) {
            for (int j = 1; j <= B.length(); j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;

                } else {
                    f[i][j] = 0;
                }
                maxLength = Math.max(maxLength, f[i][j]);
            }
        }

        return maxLength;
    }
}
