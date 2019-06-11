/*
Source : http://www.lintcode.com/en/problem/subarray-sum/
Date   : 01/12/2017
********************************************************************************
Given an integer array, find a subarray where the sum of numbers is zero. 
Your code should return the index of the first number and the index of the last number.
********************************************************************************

 */
package Leetcode_Java.dynamic_programming_medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class SubarraySum {
    /**
     * https://leetcode.com/problems/subarray-sum-equals-k/
     * June 10, 2019
     *
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     * Find the prefix sum of an array
     *        if prefix sum[i] == sum[j] for i < j, 
     *      we know that sum[i + 1...j] must be 0
     *      therefore, the start index of array is i + 1, and end index j
     */
    // 首先计算整个数组的prefix sum， 并且用哈希表储存每个元素prefix sum的下标
    // 注意在访问前先在哈希表中插入（0，1）意思是prefix sum为0的元素出现了一次
    //  在遍历数组，如果找到了哈希表中已经有的数字，那么我们就找到了subarray sum 为k的一段数字
    public int subarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        //<sum, times>
        Map<Integer, Integer> map = new HashMap();
        int sum = 0;
        map.put(0, 1);
        int count = 0;

        for(int i = 0; i < n; i++){
            sum += nums[i];
            if(map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

//    首先计算整个数组的prefix sum， 并且用哈希表储存每个元素prefix sum的下标
//    注意在访问前先在哈希表中插入（0，-1）意思是prefix sum为0的元素在-1下标出现了
//    在遍历数组，如果找到了哈希表中已经有的数字，那么我们就找到了subarray sum 为0的一段数字

    public ArrayList<Integer> subarraySum(int[] nums) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap();
        ArrayList<Integer> res = new ArrayList();
        //assume prefix sum start with value 0 at index -1
        //this setting helps addresses for edge test case [1,-1] or [0], where prefix sum starts at 0
        map.put(0, -1);
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(map.containsKey(sum)) {
               res.add(map.get(sum) + 1);
               res.add(i);
               return res;
            } else {
               map.put(sum, i); 
            }
        }
        return res;
    }
}
