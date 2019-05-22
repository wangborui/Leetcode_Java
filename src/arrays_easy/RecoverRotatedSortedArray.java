/*
Source : http://www.lintcode.com/en/problem/recover-rotated-sorted-array/
Date   : 01/18/2017

*********************************************************************************
Given a rotated sorted array, recover it to sorted array in-place.

Have you met this question in a real interview? Yes
Clarification
What is rotated array?

For example, the orginal array is [1,2,3,4], The rotated array of it can be [1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]
Example
[4, 5, 1, 2, 3] -> [1, 2, 3, 4, 5]
*********************************************************************************
 */
package Leetcode_Java.arrays_easy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
public class RecoverRotatedSortedArray {
     /**
     * @param nums: The rotated sorted array
     * @return: void
     *  use 3 step reverse:
        array [4,5,1,2,3],
        reverse [4,5]      = [5,4,1,2,3]
        reverse [1,2,3]   =[5,4,3,2,1]
        reverse array     = [1,2,3,4,5]"

     */
    public void recoverRotatedSortedArray(ArrayList<Integer> nums) {
        if(nums == null || nums.size() < 2) {
            return;
        }

        for(int i = 0; i < nums.size() - 1; i++) {
            int cur = nums.get(i);
            if(cur > nums.get(i + 1)) {
                reverse(nums, 0, i);
                reverse(nums, i + 1, nums.size() - 1);
                reverse(nums, 0, nums.size() - 1);
                return;
            }
        }
      
    }
    private void reverse(ArrayList<Integer> nums, int start, int end) {
        int temp = 0;
        while(start < end) {
            temp = nums.get(start);
            nums.set(start, nums.get(end));
            nums.set(end, temp);
            start ++;
            end--;
        }
    }
    public static void main(String [] args) {
        String s = "aa   ";
        String [] a = s.split(" ");
        System.out.println(a.length);
    }
}
