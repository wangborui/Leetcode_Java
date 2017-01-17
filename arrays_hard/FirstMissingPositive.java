// Source : https://oj.leetcode.com/problems/first-missing-positive/
// Date   : 01/17/2017
/**
 * ********************************************************************************
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
 *********************************************************************************
 */
package Leetcode_Java.arrays_hard;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Borui Wang
 */
public class FirstMissingPositive {

    /*
 *  Idea:
 * 
 *    We can move the num to the place which the index is the num. 
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
/*
 *    The idea is simple: 
 *
 *    1) put all positive number into a set.
 *    2) start iterating nums.length times at 0, check for first missing positive number(the first missing number could be 1)
            a.) for each i, make sure i + 1 exists in the set, if not return i + 1
 *    3) during the iteration process, if some number cannot be found, which means it's missed.
      4) if missing number is not found in iteration, return n + 1
 *
 *    considering a case [-2, -1, 4,5,6], 
 *        [-2, -1] => missed 1
 *        [4,5,6]  => missed 1
 *
 *    NOTE: this solution is not constant space slution!!!!
 *
 */
    public int firstMissingPositiveSet(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        Set<Integer> positives = new HashSet();
        for (int num : nums) {
            if (num > 0) {
                positives.add(num);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (!positives.contains(i + 1)) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{4, 5, 6}));
        System.out.println(firstMissingPositive(new int[]{3, 4, -1, 1,}));
        System.out.println(firstMissingPositive(new int[]{1, 2, 0}));
    }
}
