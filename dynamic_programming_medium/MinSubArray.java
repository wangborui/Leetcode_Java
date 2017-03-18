/*
Source: http://www.lintcode.com/en/problem/minimum-subarray/
Date  : 01/11/2017
/*******************************************************************************
Given an array of integers, find the subarray with smallest sum.

Return the sum of the subarray.

 Notice

The subarray should contain one integer at least.

Have you met this question in a real interview? Yes
Example
For [1, -1, -2, 1], return -3.
/*******************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

import java.util.ArrayList;

/**
 *
 * @author Borui Wang
 */
public class MinSubArray {
     /**
     * @param nums: a list of integers
     * @return: A integer indicate the sum of minimum subarray
     * use greedy algo for maximum subarray and invert all values in nums array
     */
//    先把整个数组乘以-1，再到数组里面用maxsubarray的方法找到最大subarray sum
//    然后把找到的值再乘以-1，就算出了我们原来数组中最小subarray sum值
    public int minSubArray(ArrayList<Integer> nums) {
        // write your code
        if(nums == null || nums.size() == 0) {
            return 0;
        }
        
        //use find maximum subarray algo
        int max = Integer.MIN_VALUE;
        int sum = 0;
        
        for(int num : nums) {
            //invert number 
            num = -num;
            sum += num;
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);
        }
        
        //invert max back to be minimum subarray
        return -max;
    }
}
