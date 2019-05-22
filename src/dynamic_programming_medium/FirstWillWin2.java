//Source : http://www.lintcode.com/en/problem/coins-in-a-line-ii/
//Date   : 02/20/2017
/**
 * ****************************************************************************
 * There are n coins with different value in a line.
 * Two players take turns to take one or two coins from left side until there are no more coins left.
 * The player who take the coins with the most value wins.
 *
 * Could you please decide the first player will win or lose?
 *
 * Have you met this question in a real interview? Yes
 * Example
 * Given values array A = [1,2,2], return true.
 *
 * Given A = [1,2,4], return false.
 ******************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class FirstWillWin2 {

    /**
     * @param values: an array of integers
     * @return: a boolean which equals to true if the first player will win
     */
//idea is:
//
//using dynamic programming assuming dp[i] means if we have i coins left, what is the max value first hand person can get
//        
//Initialization:
//        
//        dp[0] = 0;
//        dp[1] = coin[n - 1];
//        dp[2] = coin[n - 2] + coin[n - 1];
//        dp[3] = coin[n - 3] + coin[n - 2];
//        
//optimal function:
//        chooseOne = min(dp[i - 2], dp[i - 3]) + coin[n - i]
//        chooseTwo = min(dp[i - 3], dp[i - 4]) + coin[n - i] + coin[n - i + 1]
//        dp[i] = max(chooseOne, chooseTwo)
//                             dp[i]                   first  hand
//                            /      \
//           coin[n - i]    /         \     coin[n - i + 1]
//                     dp[i-1]      dp[i-2]            second hand
//                       / \          /  \
//                 (-1) /   \(-2)(-1)/    \(-2)
//                dp[i-2]  dp[i-3]dp[i-3]  dp[i-4]     first  hand
//lastly, determine dp[n] > coins[0 : n](total sum) / 2
    static boolean firstWillWin(int[] values) {
        if (values == null || values.length == 0) {
            return false;
        }

        if (values.length < 3) {
            return true;
        }
        int n = values.length;

        //dp[i] means when there are x coins left to choose, what is the max value first hand player can get
        int[] dp = new int[n + 1];
        int totalVal = 0;

        //initialization
        dp[0] = 0;
        //last coin/right most coin
        dp[1] = values[n - 1];
        //last two coins/right most coin
        dp[2] = values[n - 1] + values[n - 2];
        //last two coins
        dp[3] = values[n - 3] + values[n - 2];
        for (int i = 4; i <= n; i++) {
            int takeOne = Math.min(dp[i - 2], dp[i - 3]) + values[n - i];
            int takeTwo = Math.min(dp[i - 3], dp[i - 4]) + values[n - i] + values[n - i + 1];
            dp[i] = Math.max(takeOne, takeTwo);
        }

        //add all values
        for (int val : values) {
            totalVal += val;
        }

        return dp[n] > (totalVal / 2);
    }
    public static void main(String[] args) {
        int[] input = {5,1,2,10};
        System.out.println(firstWillWin(input));
    }
}
