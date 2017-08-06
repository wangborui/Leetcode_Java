// Source : https://leetcode.com/problems/maximum-average-subarray-i/description/
// Date   : 08/05/2017
/**
 * ********************************************************************************
 *
 * Given an array consisting of n integers, find the contiguous subarray of
 * given length k that has the maximum average value. And you need to output
 * the maximum average value.
 *
 * Example 1:
 * Input: [1,12,-5,-6,50,3], k = 4
 * Output: 12.75
 * Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
 * Note:
 * 1 <= k <= n <= 30,000.
 * Elements of the given array will be in the range [-10,000, 10,000].
 *********************************************************************************
 */
package Leetcode_Java.arrays_easy;

/**
 *
 * @author Borui Wang
 */
public class FindMaxAverage {

    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        double sum = 0;

        //total of first k numbers
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        double max = sum;

        //find max sum/average
        for (int i = k; i < n; i++) {
            sum += nums[i] - nums[i - k];
            max = Math.max(max, sum);
        }

        return max / k;
    }
}
