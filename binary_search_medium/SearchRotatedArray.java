/*
Source: https://leetcode.com/problems/search-in-rotated-sorted-array/
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Solutions:
Time: O(log n) Space O(1)
1. The array can be categorized into two types, rotated array and non-rotated array. In the case of a rotated array, there are two rising halves
- we first identify which half the middle element is in.
- if mid is the first rising segment, we identify if target is between start and mid
(we get rid of the second rising segment and this becomes normal binary search), 
or mid and end (we then cut off some parts of the first segment, and recursively use our special binary search technique again.
- else mid is in the second rising segment, again we identify where target is.
- this completes a full circle of binary search recursively

Time: O(log n) Space O(1)
2. We find the index of the smallest element in the rotated integer array, the use binary search to separately find the target in each half
-this method uses at least 3 binary search loops 

Mistakes:
1. if(nums[0] <= target && target <= nums[mid]) should be  if(nums[s] <= target && target <= nums[mid])
2. if(nums[mid] <= target && target <= nums[nums.length - 1]) should be if(nums[mid] <= target && target <= nums[e])


Follow up:
Assume there may be duplicate in the array? 
This is similar to find min element in rotated array with duplicates
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
Solution: O(n), since the array can contain duplicates if target = 0, we get 1110111111, we get a mid of 1, there is no telling if we should go right or left, where is the cut off point
*/
package Leetcode_Java.binary_search_medium;

/**
 *
 * @author Borui Wang
 */
public class SearchRotatedArray {
    public int search(int[] nums, int target) {
        int s = 0, e = nums.length - 1;
        while(s + 1 < e) {
            int mid = (s + e) >>> 1;
          
            if(nums[s] <= nums[mid]) {
                //on the first rising half
                //mistake 
                if(nums[s] <= target && target <= nums[mid]) {
                    e = mid;
                } else {
                    s = mid;
                }
            } else {
                //on the second rising half
                if(nums[mid] <= target && target <= nums[e]) {
                    s = mid;
                } else {
                    e = mid;
                }
            }
        }
        if(nums[s] == target) {
            return s;
        } else if (nums[e] == target) {
            return e;
        } else {
            return -1;
        }
    }
}
