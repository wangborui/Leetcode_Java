/*
Source : http://www.lintcode.com/en/problem/classical-binary-search/
Date   : 01/17/2017

*********************************************************************************
Find any position of a target number in a sorted array. Return -1 if target does not exist.

Have you met this question in a real interview? Yes
Example
Given [1, 2, 2, 4, 5, 5].

For target = 2, return 1 or 2.

For target = 5, return 4 or 5.

For target = 6, return -1.
*********************************************************************************
 */
package Leetcode_Java.binary_search_easy;

/**
 *
 * @author Borui Wang
 */
public class FindPosition {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return an integer
     */
    public int findPosition(int[] nums, int target) {
        // Write your code here
        if(nums == null || nums.length == 0) {
            return -1;
        }
        //start < target, exception when start >= target
        int start = 0;
        //end >= target, exception when end < target
        int end = nums.length - 1;
        
    
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(nums[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if(nums[start] == target || nums[end] == target) {
            return nums[start] == target? start : end;
        } else {
            return -1;
        }
    }
}
