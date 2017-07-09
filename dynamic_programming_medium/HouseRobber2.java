//Source : https://leetcode.com/problems/house-robber-ii/#/description
//Date   : 07/08/2017
/**
 * *****************************************************************************
 * After robbing those houses on that street, the thief has found himself
 * a new place for his thievery so that he will not get too much attention.
 * This time, all houses at this place are arranged in a circle. That means the first
 * house is the neighbor of the last one. Meanwhile, the security system for these houses
 * remain the same as for those in the previous street.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 ******************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
public class HouseRobber2 {

    /**
     * @param nums: An array of non-negative integers. return: The maximum
     * amount of money you can rob tonight
     */
    public int houseRobber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        //handle edge case
        if (n == 1) {
            return nums[0];
        }

        //take out first house
        int[] temp = Arrays.copyOfRange(nums, 1, n);
        int max = getMax(temp);

        //take out last house
        temp = Arrays.copyOfRange(nums, 0, n - 1);
        return Math.max(max, getMax(temp));
    }

    public int getMax(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = A[0];

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + A[i - 1]);
        }
        return dp[n];
    }
}
