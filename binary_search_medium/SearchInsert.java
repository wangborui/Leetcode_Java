/*
Source: https://leetcode.com/problems/search-insert-position/

********************************************************************************
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
********************************************************************************

Solution: searchInsert2
*assume index 0, start, is always less than target, use if to check if it is greater than or equal to target, if so return 0;
*assume index nums.length, end, is always greater than or equal to target
*after loop termination, the same invariant holds, and we can return end
 */
package Leetcode_Java.binary_search_medium;

/**
 *
 * @author Borui Wang
 */
public class SearchInsert {
    //jiuzhang template
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int s = 0, e = nums.length - 1;
         //find the first number >= target, if that num is not found return nums.length
        //possibilities, number >= target or number < target
        while(s + 1 < e){
            int mid = (s + e) >>> 1;
            if (nums[mid] >= target) {
                e = mid;
            }
            else {
                s = mid;
            }
        }
        //
        if(nums[s] >= target) {
            return s;
        }
        else if(nums[e] >= target) {
            return e;
        }else{
            //after searching if all nums are < target, then e points to the last number in the array, therefore e + 1;
            return e + 1;
        }
    }
    
    public int searchInsert2(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
             return 0;
         }
        //make sure start is always less than target, exception when start is greater than or equal to target
         if(nums[0] >= target) {
             return 0;
         }
         int start = 0;
         int end = nums.length;
         //before loop starts
         //start < target
         //end >= target
         while(start + 1 < end) {
             int mid = start + (end - start) / 2;
             if(nums[mid] < target) {
                 start = mid;
             } else {
                 end = mid;
             }
         }
         //after loop ends
         //start < target
         //end >= target
         return end;
    }
}
