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
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     * Find the prefix sum of an array
     *        if prefix sum[i] == sum[j] for i < j, 
     *      we know that sum[i + 1...j] must be 0
     *      therefore, the start index of array is i + 1, and end index j
     */
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
