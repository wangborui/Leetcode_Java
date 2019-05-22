/*
Source: https://leetcode.com/problems/jump-game-ii/
********************************************************************************
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

Note:
You can assume that you can always reach the last index.
********************************************************************************

Solutions: 
1. Greedy algo
2. Dynamic algo
 */
package Leetcode_Java.dynamic_programming_hard;

/**
 *
 * @author Borui Wang
 */
public class JumpGame2 {

    //Dynamic programming time O(n^2) exceeds limit
    public int jumpDP(int[] nums) {
        int n = nums.length;
        //minStep[i] is the minimum number of jumps to reach i from origin
        int[] minJump = new int[n];
        for (int i = 0; i < n; i++) {
            minJump[i] = Integer.MAX_VALUE;
        }
        minJump[0] = 0;

        for (int i = 1; i < n; i++) {
            //check position 0 <= j < i to find reachable positions
            for (int j = 0; j < i; j++) {
                //position j is reachable from origin
                if (minJump[j] != Integer.MAX_VALUE && nums[j] + j >= i) {
                    minJump[i] = Math.min(minJump[i], minJump[j] + 1);
                }
            }
        }

        return minJump[n - 1];
    }
    /**
     * 
     * @param nums
     * @return Integer
     * define variables 
     * farthest = 0
     * jump = 0
     * last = 0
     *Index           0  1  2  3  4   
     *                i 
     * Given array   [2, 3, 1, 1, 4]
     *                f
     *                j
     *                l
     */
    public int jumpGreedy(int[] nums) {
        //farthest distance current range can cover
        int farthest = 0;
        //min jumps to reach end
        int jump = 0;
        //last step before jumping one more step
        int last = 0;
        int n = nums.length;
        //i traverses num array
        for (int i = 0; i < n; i++) {
            if (i > last) {
                jump++;
                last = farthest;
            }
            farthest = Math.max(farthest, nums[i] + i);
        }
        return jump;
    }
}
