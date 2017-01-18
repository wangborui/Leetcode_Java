// Source : https://oj.leetcode.com/problems/remove-duplicates-from-sorted-array/
// Date   : 01/18/2017

/********************************************************************************** 
* 
* Given a sorted array, remove the duplicates in place such that each element appear 
* only once and return the new length.
* 
* Do not allocate extra space for another array, you must do this in place with constant memory.
* 
* For example,
* Given input array A = [1,1,2],
* 
* Your function should return length = 2, and A is now [1,2].
* 
*               
**********************************************************************************/
package Leetcode_Java.arrays_medium;

/**
 *
 * @author Borui Wang
 */
public class RemoveDuplicates {
        public int removeDuplicates(int[] nums) {
        //initialize i = 0, j = 1
         //use two pointers i and j, from 0:i - 1 are all non-duplicate elements, from i:j are all removed duplications, from j + 1:n are unknown areas
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int i = 0;
        int j = 1;
        int n = nums.length;
        
        while(j < n) {
            if(j < n && nums[j] == nums[j - 1]) {
                j++;
            } else {
                nums[++i] = nums[j++];
            }
        }
        return i + 1;
    }
}
