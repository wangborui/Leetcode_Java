// Source : https://oj.leetcode.com/problems/search-in-rotated-sorted-array-ii/
// Date   : 01/18/2017
/**
 * ********************************************************************************
 *
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 *
 * Would this affect the run-time complexity? How and why?
 *
 * Write a function to determine if a given target is in the array.
 *
 *********************************************************************************
 */
// Using the same idea "Search in Rotated Sorted Array"
// but need be very careful about the following cases:
//   [3,3,3,4,5,6,3,3] 
//   [3,3,3,3,1,3]
// After split, you don't know which part is rotated and which part is not.
// So, you have to skip the ducplication
//   [3,3,3,4,5,6,3,3] 
//          ^       ^
//   [3,3,3,3,1,3]
//            ^ ^
//OR
//    We could just scan the array once to find the smallest element
package Leetcode_Java.binary_search_hard;

/**
 *
 * @author Borui Wang
 */
public class SearchRotatedArray2 {

    public boolean search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return true;
            }
        }
        return false;
    }
}
