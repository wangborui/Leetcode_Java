// Source : https://oj.leetcode.com/problems/decode-ways/
// Date   : 07/04/2017
/**
 * ********************************************************************************
 *
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 *
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 *
 * For example,
 * Given encoded message "12",
 * it could be decoded as "AB" (1 2) or "L" (12).
 *
 * The number of ways decoding "12" is 2.
 *
 *
 *********************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class DecodeWays {
//Count[i] = Count[i-1]              if S[i-1] is a valid char (not '0')
//         = Count[i-1]+ Count[i-2]  if S[i-1] and S[i-2] together is still a valid char (10 to 26).

    static int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return isValid(s.charAt(0)) ? 1 : 0;
        }

        int n = s.length();
        int[] dp = new int[n];
        dp[0] = isValid(s.charAt(0)) ? 1 : 0;
        dp[1] = (isValid(s.charAt(1)) ? dp[0] : 0) + (isValid(s.substring(0, 2)) ? 1 : 0);

        for (int i = 2; i < n; i++) {
            char cur = s.charAt(i);
            if (!Character.isDigit(cur)) {
                return 0;
            }
            if (isValid(cur)) {
                dp[i] = dp[i - 1];
            }
            if (isValid(s.substring(i - 1, i + 1))) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n - 1];

    }

    static boolean isValid(char c) {
        return c != '0';
    }

    static boolean isValid(String s) {
        int val = Integer.parseInt(s);
        return 10 <= val && val <= 26;
    }

    /**
     *
     * @param s
     * @return Integer There must be last number to decode, and this number (1,
     * 2, 3...9) could be A, B, C ...I Note this number cannot be zero There may
     * be last two numbers to decode, and this number(10, 11, 12...26) could be
     * J, K, L ... Z
     *
     * f[i] means the number of ways to decode first i numbers not including the
     * ith number
     *
     * Init: there is one way to decode first 0 number, as an empty one
     *
     * f[0] = 1;
     *
     * sub-optimal function:
     *
     * f[i] = 0 f[i] += f[i - 1] if 's[i - 1]' is in the range [1:9] f[i] += f[i
     * - 2] if 's[i - 2] + s[i - 1]' is in the range [10:26]
     *
     */
    static int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        char[] c = s.toCharArray();
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i] = 0;
            //last digit
            int num = c[i - 1] - '0';

            if (num > 0 && num <= 9) {
                dp[i] += dp[i - 1];
            }

            //length must be greater than 1
            if (i >= 2) {
                //last two digits together is number in range[10:26]
                num = (c[i - 2] - '0') * 10 + (c[i - 1] - '0');
                if (num >= 10 && num <= 26) {
                    dp[i] += dp[i - 2];
                }
            }

        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("100"));
    }
}
