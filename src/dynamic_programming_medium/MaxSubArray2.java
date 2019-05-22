/*
Source : http://www.lintcode.com/en/problem/maximum-subarray-ii/
Date   : 01/11/2017
********************************************************************************
Given an array of integers, find two non-overlapping subarrays which have the largest sum.
The number in each subarray should be contiguous.
Return the largest sum.

 Notice

The subarray should contain at least one number

Have you met this question in a real interview? Yes
Example
For given [1, 3, -1, 2, -1, 2], the two subarrays are [1, 3] and [2, -1, 2] or [1, 3, -1, 2] and [2], they both have the largest sum 7.
********************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

import java.util.ArrayList;

/**
 *
 * @author Borui Wang
 */
public class MaxSubArray2 {

//find max subarray values from left to right, forward[i] means the max subarray value at ith index from 0 ... i
//find max subarray values from right to left, backward[i] means the max subarray value at ith index from i ... n
//scan from left to right to find max values between each pair forward[i] and backward[i + 1];
//    先从左往右扫描一次数组，当扫描到第i个数字的时候，计算从[0:i]数组的最大subarray sum是多少，记录到forward数组里面
//    再从右往左扫描一次数组，当扫描到第i个数字的时候，计算从[i:n - 1]数组的最大subarray sum是多少，记录到backward数组里面
//    再遍历一次数组，算出forward[i] + backward[i + 1]的最大值
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        if (nums.isEmpty()) {
            return 0;
        }

        int n = nums.size();
        int[] forward = new int[n];
        int[] backward = new int[n];
        int max = Integer.MIN_VALUE;
        int sum = 0;

        //use greedy algo to calculate forward
        for (int i = 0; i < n; i++) {
            sum += nums.get(i);
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);
            forward[i] = max;
        }

        sum = 0;
        max = Integer.MIN_VALUE;
        //use greedy algo to calculate backward
        for (int i = n - 1; i >= 0; i--) {
            sum += nums.get(i);
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);
            backward[i] = max;
        }

        max = Integer.MIN_VALUE;
        //find both subarrays with max value
        for (int i = 0; i < n - 1; i++) {
            int subarrayOne = forward[i];
            int subarrayTwo = backward[i + 1];
            max = Math.max(max, subarrayOne + subarrayTwo);
        }
        return max;
    }
}
