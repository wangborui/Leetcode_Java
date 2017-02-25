// Source : https://oj.leetcode.com/problems/decode-ways/
// Date   : 02/25/2017

/********************************************************************************** 
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
**********************************************************************************/
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class DecodeWays {
//Count[i] = Count[i-1]              if S[i-1] is a valid char (not '0')
//         = Count[i-1]+ Count[i-2]  if S[i-1] and S[i-2] together is still a valid char (10 to 26).

    static int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        if(s.length() == 1) {
            return isValid(s.charAt(0)) ? 1 : 0;
        }
        
        int n = s.length();
        int[] dp = new int[n];
        dp[0] = isValid(s.charAt(0)) ? 1 : 0;
        dp[1] = (isValid(s.charAt(1))? dp[0] : 0) + (isValid(s.substring(0, 2)) ? 1 : 0);
        
        for(int i = 2; i < n; i++) {
            char cur = s.charAt(i);
            if(!Character.isDigit(cur)) {
                return 0;
            }
            if(isValid(cur)) {
                dp[i] = dp[i - 1];
            }
            if(isValid(s.substring(i - 1, i + 1))) {
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

    public static void main(String[] args) {
        System.out.println(numDecodings("100"));
    }
}
