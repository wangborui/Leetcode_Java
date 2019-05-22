/*
Source : https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
Date   : 01/17/2017

*********************************************************************************
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.end., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
*********************************************************************************

These are arrays without duplicate element, this can be done with binary search
O(log n)

Input: 1,2,3,4,5
No rotation   : 1,2,3,4,5
Rotate one    : 5,1,2,3,4
Normal Rotate : 3,4,5,1,2

Follow up:
Find minimum number in the rotated integer array that may contain duplicates.
O(n)
Input:        1,1,1,1,0,1,1,1
              [3, 3, 3, 3, 3]
              [3, 3, 1, 3, 3]
by getting the value mid and comparing to the end value target, we do not get any information like we do above
the solution to this problem is to use a for loop to find the smallest element
 */
package Leetcode_Java.binary_search_hard;

/**
 *
 * @author Borui Wang
 */
public class FindMin {

    /* 
 *  Obveriously, to search any sorted array, the binary search is the common sense.
 * 
 *  To solve this problem, the idea is same as the search in rotated sorted array.
    
    Assume our array has two parts like so, maitains invariant start always >= target, and end < target
           /  |    
          /   |
         /    |
        /     |
start->/      |
    ----------------------
              |     /<--target, end
              |    /
              |   /
              |  /
              | /
     */
    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1, target = nums[end];
        while (start + 1 < end) {
            int mid = (start + end) >>> 1;
            if (nums[mid] >= target) {
                start = mid;
            } else { //nums[mid] < target
                end = mid;
            }
        }
        //invariant:  all vals >=,>=,>= [start,end] <, <, < target, value of start could be >= target, which >= end
        //or if we do not rotate array , after while loop we get vals[start,end] <, <, <, <, < target 
        if (nums[start] < nums[end]) {
            return nums[start];
        } else {
            return nums[end];
        }
    }
}
