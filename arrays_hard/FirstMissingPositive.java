// Source : https://oj.leetcode.com/problems/first-missing-positive/
// Date   : 01/17/2017

/********************************************************************************** 
* 
* Given an unsorted integer array, find the first missing positive integer.
* 
* For example,
* Given [1,2,0] return 3,
* and [3,4,-1,1] return 2.
* 
* Your algorithm should run in O(n) time and uses constant space.
* 
*               
**********************************************************************************/
package Leetcode_Java.arrays_hard;

/**
 *
 * @author Borui Wang
 */
public class FirstMissingPositive {
/*
 *  Idea:
 * 
 *    We can move the num to the place whcih the index is the num. 
 *   
 *    for example,  (considering the array is zero-based.
 *       1 => A[0], 2 => A[1], 3=>A[2]
 *   
 *    Then, we can go through the array check the i+1 == A[i], if not ,just return i+1;
 *   
 *    This solution comes from Github
 *    https://github.com/HaochenLiu/LeetCode-haoel/blob/master/algorithms/firstMissingPositive/firstMissingPositive.cpp
*/
    static int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            while (val > 0 && val < n && nums[val - 1] != val) {
                swap(nums, i, val - 1);
                val = nums[i];
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{4, 5, 6}));
        System.out.println(firstMissingPositive(new int[]{3, 4, -1, 1,}));
        System.out.println(firstMissingPositive(new int[]{1, 2, 0}));
    }
}
