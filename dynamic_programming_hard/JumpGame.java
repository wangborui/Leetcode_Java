/*
Source : https://oj.leetcode.com/problems/jump-game/
Date   : 12/27/2016

/*******************************************************************************
* 
* Given an array of non-negative integers, you are initially positioned at the first index of the array.
* 
* Each element in the array represents your maximum jump length at that position. 
* 
* Determine if you are able to reach the last index.
* 
* For example:
* A = [2,3,1,1,4], return true.
* 
* A = [3,2,1,0,4], return false.
* 
*               
********************************************************************************
Solutions:
1. Greedy algorithms Time O(n) space O(1)
2. Dynamic programming Time O(n^2) space O(n)
*/

package Leetcode_Java.dynamic_programming_hard;

/**
 *
 * @author Borui Wang
 */
public class JumpGame {
    //greedy algorithms: traverse array and maintain farthest we can go
    public boolean canJumpGreedy(int[] nums) {
         if(nums == null) {
             return false;
         }
         int n = nums.length;
         int farthest = nums[0];
         for(int i = 1; i <= farthest && i < n; i++) {
             //update farthest when i position is reachable from origin
             if(farthest < nums[i] + i) {
                 farthest = nums[i] + i;
             }
         }
         return farthest >= n - 1;
    }
    //O(n^2) time complexity dynamic programming, exceeds time limit
    //f[i] means can we jump from 0 to ith position, we must check if we can
    //reach any position from 0 to i - 1 and at that position we can jump to greater distance than i
    //f[..........?............]
    //  0     i-1 i           n 
    public boolean canJumpDP(int[] nums) {
        int n = nums.length;
        boolean [] reachable = new boolean[n];
        //Initialization
        reachable[0] = true;
        
        //f[i] = true: if f[0 : i - 1] is true and nums[j] + j >= i
        //       false: otherwize
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(reachable[j] && nums[j] + j >= i) {
                    reachable[i] = true;
                    break;
                }
            }   
        }
        
        return reachable[n - 1];
    }
}
