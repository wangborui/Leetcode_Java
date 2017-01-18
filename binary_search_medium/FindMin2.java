/*
Source : https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
Date   : 01/18/2017
*********************************************************************************
154. Find Minimum in Rotated Sorted Array II   Add to List QuestionEditorial Solution  My Submissions
Total Accepted: 68751
Total Submissions: 189827
Difficulty: Hard
Contributors: Admin
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.
*********************************************************************************
 */
package Leetcode_Java.binary_search_medium;

/**
 *
 * @author Borui Wang
 */
public class FindMin2 {
/*
 * Need be very careful for the following cases:
 *
 *    [3, 3, 3, 3, 3]
 *  
 *    [3, 3, 3, 1, 3]
 *
 */
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
        }
        return min;
    }
}
