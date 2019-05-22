//Source : http://www.lintcode.com/en/problem/k-sum/
//Date   : 02/03/2017

/**
 *******************************************************************************
 * Given n distinct positive integers, integer k (k <= n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?

Have you met this question in a real interview? Yes
Example
Given [1,2,3,4], k = 2, target = 5.

There are 2 solutions: [1,4] and [2,3].

Return 2.
*******************************************************************************
 */
package Leetcode_Java.dynamic_programming_hard;

/**
 *
 * @author Borui Wang
 */
public class KSum {
//Time O(n * k * t) Space O(n * k * t)
//Analysis:
//
//dp[i][j][t] is the number of ways for the first i numbers, not including ith number, select exactly j number to sum to exactly t
//
//Initialization: 
//
//        dp[x][0][0] = 1 if we select exactly 0 numbers to have their sum to be 0, we have one way, that is we dont choose at all
//        dp[x][0][y] = 0, because if we choose 0 number, they cant sum to anything, 
//        dp[x][y][0] = 0, when y != 0, because we cant have positive numbers sum up to 0
//        
//Optimal function:
//
//        dp[i][j][t] = dp[i - 1][j][t];
//        if (t - A[i - 1] >= 0) {
//            dp[i][j][t] += dp[i - 1][j - 1][t - A[i - 1]];
//        }
//        1.) We do not take the A[i - 1]th value, dp[i - 1][j][t], we just choose j numbers from the first i - 1 th number, not including A[i - 1], to sum to target t
//        2.) We take the A[i - 1]th value, dp[i - 1][j - 1][t - A[i - 1]]:
//                first make sure t - A[i - 1] >= 0, target is greater than the number we are taking, or we cant take this number
//                if the number A[i - 1] is smaller than target, we find the ways to get to this target t - A[i - 1], from the previous i - 1 numbers choosing exactly j - 1 numbers
//    动态规划定义dp[i][j][t] 的意思是从前i个数字中选出j个数字他们的和是t，可以选出多少种组合达到这个标准
//    初始化
//            dp[x][0][0] = 1 如果我们选择0个数字使得他们的和为0，我们可以有多少种组合达到这个标准？1种，那就是我们什么都不取
//            dp[x][0][y] = 0 如果我们选出0个数字使得他们的和为y，我们可以有多少种组合达到这个标准？0种，如果我们一个数字都没有，可能使得其和为y
                    
    public int kSum(int A[], int k, int target) {
        if (k > A.length || target <= 0 || A == null || A.length == 0) {
            return 0;
        }

        //dp[i][j][t] is the number of ways for the first i numbers, not including ith number, select exactly j number to sum to exactly t
        int n = A.length;
        int[][][] dp = new int[n + 1][k + 1][target + 1];

        //initialization : dp[x][0][0] = 1 if we select exactly 0 numbers to have their sum to be 0, we have one way, that is we dont choose at all
        //dp[x][0][y] = 0, because if we choose 0 number, they cant sum to anything, 
        //dp[x][y][0] = 0, when y != 0, because we cant have positive numbers sum up to 0
        for (int i = 0; i <= n; i++) {
            dp[i][0][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                for (int t = 1; t <= target; t++) {
                    //how many way to find k sum without current element
                    int notTake = dp[i - 1][j][t];
                    //how many ways to find k sum with current element
                    int take = 0;
                    if (t >= A[i - 1]) {
                        take = dp[i - 1][j - 1][t - A[i - 1]];
                    }
                    dp[i][j][t] = take + notTake;
                }
            }
        }
        return dp[n][k][target];
    }
}
