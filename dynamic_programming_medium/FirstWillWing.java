//Source : http://www.lintcode.com/en/problem/coins-in-a-line/
//Date   : 02/20/2017
/**
 * ****************************************************************************
 * There are n coins in a line. 
 * Two players take turns to take one or two coins from right side until there are no more coins left. 
 * The player who take the last coin wins.
 *
 * Could you please decide the first play will win or lose?
 *
 * Have you met this question in a real interview? Yes
 * Example
 * n = 1, return true.
 *
 * n = 2, return true.
 *
 * n = 3, return false.
 *
 * n = 4, return true.
 *
 * n = 5, return true.
 ******************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class FirstWillWing {

    /**
     * @param n: an integer
     * @return: a boolean which equals to true if the first player will win
     */
//idea is:
//
//using dynamic programming assuming f[i] means if we have i coins, will first hand win
//        
//Initialization:
//        
//        f[0] = false;
//        f[1] = true;
//        f[2] = true;
//        f[3] = false;
//        f[4] = true;
//        
//optimal function:
//
//        f[i] = (f[i - 2] && f[i - 3]) || (f[i - 3] && f[i - 4])
//                                n                   first 
//                            /      \
//                      (-1)/         \(-2)
//                        n-1         n-2             second
//                       / \          /  \
//                 (-1) /   \(-2)(-1)/    \(-2)
//                 n-2       n-3   n-3     n-4         first
    private static final int WIN = 2;
    private static final int LOSE = 1;

    static boolean firstWillWin(int n) {
        int[] dp = new int[n + 1];
        return helper(n, dp);
    }

    static boolean helper(int n, int[] dp) {
        if (dp[n] != 0) {
            if (dp[n] == WIN) {
                return true;
            } else {
                return false;
            }
        }
        if (n <= 0) {
            dp[n] = LOSE;
            return false;
        } else if (n == 1) {
            dp[n] = WIN;
            return true;
        } else if (n == 2) {
            dp[n] = WIN;
            return true;
        } else if (n == 3) {
            dp[n] = LOSE;
            return false;
        } else if (n == 4) {
            dp[n] = WIN;
            return true;
        } else if ((helper(n - 2, dp) && helper(n - 3, dp)) || (helper(n - 3, dp) && helper(n - 4, dp))) {
            dp[n] = WIN;
        } else {
            dp[n] = LOSE;
        }
        return dp[n] == WIN;
    }
    public static void main(String[] args) {
        for(int i = 1; i <= 100; i++) {
            if(!firstWillWin(i)) {
                System.out.print(i + " ");
            }
        }
         
    }
}
