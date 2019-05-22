//Source : https://leetcode.com/problems/continuous-subarray-sum/?tab=Description
//Date   : 03/04/2017

/**
 * ******************************************************************************
 * Given an integer array, find a continuous subarray where the sum of numbers is the biggest.
 * Your code should return the index of the first number and the index of the last number.
 * (If their are duplicate answer, return anyone)
 *
 * Have you met this question in a real interview? Yes
 * Example
 * Give [-3, 1, 3, -3, 4], return [1,4].
 *******************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class CheckSubarraySum {
//    idea is:
//    we find prefix sum of the entire array
//    
//    add 0 as the first prefix sum with index -1
//    
//    then find the prefix sum % k remainder at each index, and store in hash map
//            
//    if this remainder has been seen before, we then have found a subarray whose sum is n*k

    static boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int prefixSum = 0;
        Map<Integer, Integer> map = new HashMap();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (k != 0) {
                prefixSum %= k;
            }
            Integer prev = map.get(prefixSum);
            if (prev == null) {
                map.put(prefixSum, i);
            } else if (i - prev > 1) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        checkSubarraySum(new int[]{0,0},0);
    }
}
