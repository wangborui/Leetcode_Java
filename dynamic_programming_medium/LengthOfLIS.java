/*
Source: https://leetcode.com/problems/longest-increasing-subsequence/
Date: 12/29/2016

********************************************************************************
Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n2) complexity.

Follow up: Could you improve it to O(n log n) time complexity?
********************************************************************************

Solution:
*1. Dynamic Programming 
Time O(n^2): double for loop to iterate nums array 
Space O(n): assistant dp with size equal to nums array
dp[i] means the length of longeset increasing subsequence from index 0 to i
--------------------------------------------------------------------------------
For example, given nums = [4,10,5,6]
index 0 1 2 3
      | | | |
dp = [1,1,1,1]
dp[0] = 1, from 0 to 0 there is nums[0], 4 is the LIS of itself
dp[1] = 1, if nums[0] > nums[1]                                                 index 0 1 2 3
      = max(dp[1], dp[0] + 1) = 2                                                     | | | |
      = 2, because nums[0] < num[1]                                             dp = [1,2,1,1]
dp[2] = 1, if nums[0] > nums[2] && nums[1] > nums[2]                            index 0 1 2 3
      = max(dp[2], dp[0] + 1) = 2                                                     | | | |
      2, because nums[0] < nums[2]                                              dp = [1,2,2,1]
                   .
                   .
                   .
--------------------------------------------------------------------------------
Thus, the optimal function is the following
*dp[i] = 1                     :if 0 <= j < i, all nums[j] > nums[i]
*        max(dp[j] + 1, dp[i]) :if 0 <= j < i, nums[j] <= nums[i]
     
         
 */
package Leetcode_Java.dynamic_programming_medium;

import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
public class LengthOfLIS {
    
     //time O(n^2): double for loop Space O(n): assistant dp array
    static int lengthOfLISDP(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        //dp[i] means the length of longeset increasing subsequence from index 0 to i
        int [] dp = new int[nums.length];
        int maxLength = Integer.MIN_VALUE;
        
        
        /*
        the optimal function is the following
        dp[i] = 1                     :if 0 <= j < i, all nums[j] > nums[i]
                max(dp[j] + 1, dp[i]) :if 0 <= j < i, nums[j] <= nums[i]
        */
        //double for loop time O(n^2)
        for(int i = 0; i < nums.length; i++) {
            //initialization: each number is a increasing subsequence of itself
            dp[i] = 1;
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }
    
     static int lengthOfLISBinarySearchWithFunction(int[] nums) {            
        int[] dp = new int[nums.length];
        int len = 0;

        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if(i < 0) i = -(i + 1);
            dp[i] = x;
            if(i == len) len++;
        }

        return len;
    }
     static int lengthOfLISBinarySearch(int[] nums) {
        int [] LIS = new int[nums.length];
        int length = 0; 
        for(int i = 0; i < nums.length; i++) {
            LIS[i] = Integer.MAX_VALUE;
        }
        
        for(int n : nums) {
            //find index of first number larger than or equal to target n
            int index = binarySearch(length, LIS, n);
            if(index == length) {
                LIS[length++] = n;
            } else {
                LIS[index] = n;
            }
        }
        return length;
    }
    
    static int binarySearch(int last, int [] LIS, int target) {
        //assume LIS[start] < target
        int start = 0;
        //assume LIS[end] >= target
        int end = last;
        
        if(LIS[start] >= target) {
            return start;
        }
        
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(LIS[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return end;
    }
     public static void main(String[] args) {
         System.out.println(lengthOfLISBinarySearch(new int[]{10, 9, 2, 5, 5, 3, 7, 101, 18,1}));
      }
    
}
