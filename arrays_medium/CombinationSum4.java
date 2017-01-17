/*
Source : https://leetcode.com/problems/combination-sum-iv/
Date   : 01/17/2017
********************************************************************************
Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?
********************************************************************************
 */
package Leetcode_Java.arrays_medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class CombinationSum4 {

    /**
     *
     * @param nums
     * @param target
     * @return number of ways to reach target This solution exceeds time limit,
     * because its counting all ways to get to target and not doing memoization
     * Time O(2^n) Space O(n)
     */
    static int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap();
        dfsHelper(nums, map, target, 0);
        return map.get(target);
    }

    static void dfsHelper(int[] nums, Map<Integer, Integer> map, int target, int sum) {
        if (sum >= target) {
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(sum + nums[i])) {
                map.put(sum + nums[i], map.get(sum + nums[i]) + 1);
            } else {
                map.put(sum + nums[i], 1);
            }
            dfsHelper(nums, map, target, sum + nums[i]);
        }

    }
    /**
     * 
     * @param nums
     * @param target
     * @return number of ways to get to target
     * This approach uses top down dp approach, which uses memoization to remember calculated results
     * optimal function is the following:
     *  Initialization: f[0] = 1, there is only one way to get to value 0, by using 0      
     *          f[i] = f[i] + f[i - nums[0...n]] if i - nums[0...n] >= 0
     *          f[i - nums[0...n]] means all possible ways before i, f[0:i]       
     */
    static int combinationSum4DPTopDown(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //shows number of ways to get to target from 0...target
        int[] sums = new int[target + 1];
        //fills up sums array with -1 to show it hasnt been calculated yet
        Arrays.fill(sums, -1);
        sums[0] = 1;
        return dfsHelperTopDown(nums, sums, target);
    }

    static int dfsHelperTopDown(int[] nums, int[] sums, int target) {
        if (sums[target] != -1) {
            return sums[target];
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target - nums[i] >= 0) {
                count += dfsHelperTopDown(nums, sums, target - nums[i]);
            }
        }

        return count;
    }
      /**
     * 
     * @param nums
     * @param target
     * @return number of ways to get to target
     * This approach uses top down dp approach, which uses memoization to remember calculated results
     * optimal function is the following:
     *  Initialization: f[0] = 1, there is only one way to get to value 0, by using 0      
     *          f[i] = f[i] + f[i - nums[0...n]] if i - nums[0...n] >= 0
     *          f[i - nums[0...n]] means all possible ways before i, f[0:i]       
     */
    static int combinationSum4BottomUp(int[] nums, int target) {
         if(nums == null || nums.length == 0) {
             return 0;
         }
         //how many times sums 0, 1 ... target appears
         int [] sums = new int[target + 1];
         //only one way to get sum 0, which is 0
         sums[0] = 1;
         
         for(int i = 1; i < sums.length; i++) {
             for(int j = 0; j < nums.length; j++) {
                 if(i - nums[j] >= 0) {
                     sums[i] += sums[i - nums[j]];
                 }
             }
         }
         return sums[target];
    }
    public static void main(String[] args) {
        System.out.println(combinationSum4(new int[]{1, 2, 3}, 32));
    }
}
