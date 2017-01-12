/*
Source: https://leetcode.com/problems/two-sum/
Date  : 01/12/2017
********************************************************************************
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
********************************************************************************
 */
package Leetcode_Java.arrays_easy;

import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class TwoSum {
    //use hashmap, time O(n), space O(n)
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        int [] res = new int[2];
        for(int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            //see if diff already exists in hashmap
            if(map.containsKey(diff)) {
                res[0] = map.get(diff);
                res[1] = i;
                return res;
            } else {
                //stores number into hashmap
                map.put(nums[i], i);
            }
        }
        
        return res;
    }
    public static void main(String []args){
        //{-1,-2,-3,-4,-5};-8
        //{3,2,4}; 6
        //{0,4,3,0}; 0
        int[]a = {3,2,4};  
        int target = 6;
        TwoSum b =  new TwoSum();
        for(int temp: b.twoSum(a,target)){
            StdOut.println(temp);
        }
    }
}
