// Source : https://leetcode.com/problems/shortest-unsorted-continuous-subarray/#/description
// Date   : 05/21/2017
/**
 * ********************************************************************************
 * Given an integer array, you need to find one continuous subarray that if
 * you only sort this subarray in ascending order, then the whole array will be
 * sorted in ascending order, too.
 *
 * You need to find the shortest such subarray and output its length.
 *
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * Note:
 * Then length of the input array is in range [1, 10,000].
 * The input array may contain duplicates, so ascending order here means <=.
 *********************************************************************************
 */
package Leetcode_Java.arrays_easy;

/**
 *
 * @author Borui Wang
 */
public class FindUnsortedSubarray {
    //scan from left to right and update maximum value as we scan, finding the index of the last number needed to be sorted 
    //scan from right to left and update minimum value as we scan, finding the index of the first number needed to be sorted
    //return end(index) - start(index) + 1 as the total numbers in the continuous unsorted subarray
    //if the entire array is sorted, then end(-2) - start(-1) + 1 = 0
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int start = -1;
        int end = -2;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        //find end in nums[start:end] range
        for (int i = 0; i < n; i++) {
            max = Math.max(nums[i], max);
            if (nums[i] < max) {
                end = i;
            }
        }

        //find start in nums[start:end] range
        for (int i = n - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            if (nums[i] > min) {
                start = i;
            }
        }

        return end - start + 1;
    }
}
