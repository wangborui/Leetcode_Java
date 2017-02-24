//Source: https://leetcode.com/problems/predict-the-winner/?tab=Solutions
//Date  : 02/24/2017

/**
 * ****************************************************************************
 * Given an array of scores that are non-negative integers. 
 * Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, 
 * that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.
 *
 * Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.
 *
 * Example 1:
 * Input: [1, 5, 2]
 * Output: False
 * Explanation: Initially, player 1 can choose between 1 and 2.
 * If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2).
 * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
 * Hence, player 1 will never be the winner and you need to return False.
 * Example 2:
 * Input: [1, 5, 233, 7]
 * Output: True
 * Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
 * Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 * Note:
 * 1 <= length of the array <= 20.
 * Any scores in the given array are non-negative integers and will not exceed 10,000,000.
 * If the scores of both players are equal, then player 1 is still the winner.
 ******************************************************************************
 

 * @author Borui Wang
 */
package Leetcode_Java.dynamic_programming_medium;
import java.util.Arrays;
public class PredictTheWinner {
//idea is:
//
//using dynamic programming assuming dp[i][j] means from coin i to coin j, what is the max value first hand player can get
//        
//Initialization:
//        
//        dp[i][i] = nums[i]
//        dp[i][j] = 0, if i > j
//        
//optimal function:
//        take_left  = min(dp[i + 2][j], dp[i + 1][j - 1]) + nums[i]
//        take_right = min(dp[i + 1][j - 1], dp[i][j - 2]) + nums[j]
//        dp[i][j] = max(take_left, take_right)
//    
//                             dp[i][j]                   first  hand
//                            /      \
//               nums[i]    /         \     nums[j]
//               dp[i+1][j]      dp[i][j - 1]            second hand
//                       / \          /  \
//                     /    \        /    \
//          dp[i+2]][j]  dp[i+1]dp[i-1]  dp[i][j - 2]     first  hand
//lastly, determine dp[0][n - 1] * 2 >= total sum
    public boolean PredictTheWinner(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int totalVal = 0;
        for (int num : nums) {
            totalVal += num;
        }
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        //pitfall: we cannot do totalVal / 2 >= helper(...)
        //because if totalVal = 5, heper() = 2, it should return false, but we return true due to integer division
        return helper(dp, nums, 0, n - 1) * 2 >= totalVal;
    }

    private int helper(int[][] dp, int[] nums, int start, int end) {
        if (start > end) {
            return 0;
        } else if (start == end) {
            dp[start][end] = nums[start];
            return dp[start][end];
        } else if (dp[start][end] != -1) {
            return dp[start][end];
        } else {
            int takeLeft = Math.min(helper(dp, nums, start + 2, end), helper(dp, nums, start + 1, end - 1)) + nums[start];
            int takeRight = Math.min(helper(dp, nums, start + 1, end - 1), helper(dp, nums, start, end - 2)) + nums[end];
            dp[start][end] = Math.max(takeLeft, takeRight);
            return dp[start][end];
        }
    }

    public static void main(String[] args) {
        //System.out.println(PredictTheWinner(new int[]{1,3,1}));
    }
}
