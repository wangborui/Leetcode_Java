//Source : https://leetcode.com/problems/house-robber/
//Date   : 02/17/2017

/*******************************************************************************
 You are a professional robber planning to rob houses along a street. 
 * Each house has a certain amount of money stashed, the only constraint stopping 
 * you from robbing each of them is that adjacent houses have security system connected 
 * and it will automatically contact the police if two adjacent houses were broken into 
 * on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of each house, 
* determine the maximum amount of money you can rob tonight without alerting the police.
*******************************************************************************/
package Leetcode_Java.dynamic_programming_easy;

/**
 *
 * @author Borui Wang
 */
public class Rob {
    /*
 * Dynamic Programming
 * dp[n] means max values we can get from first i houses, not including the ith house
 * We can easy find the recurive fomular:
 *
 *     dp[n] = max( 
 *                    dp[n-1],   // the previous house has been robbed. 
 *                    dp[n-2] + money[n]  // the previous house has NOT been robbed.
 *                )
 *                  
 * The initalization is obvious:
 *     dp[0] = 0
 *     dp[1] = money[0]
 *
 */
    //Time O(n), space O(n)
    static int rob(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        //dp[i] means max values we can get from first i houses, not including the ith house
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        
        //dp[i] = max(dp[i - 1], dp[i - 2] + nums[i - 1])
        for(int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        
        return dp[n];
    }
    //Space Optimized to O(1)
    static int robOptimized(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        //f[i] means max values we can get from first i houses, not including the ith house
        int n = nums.length;
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = nums[0];
        
        //f[i] = max(f[i - 1], f[i - 2] + nums[i - 1])
        for(int i = 2; i <= n; i++) {
            dp[i%2] = Math.max(dp[(i - 1)%2], dp[(i - 2)%2] + nums[i - 1]);
        }
        
        return dp[n%2];
    }
    public static void main(String[] args){
        System.out.println(rob(new int[]{3,1,5,8,0,4,12}));
    }
}
