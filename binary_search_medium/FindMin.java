/*
These are arrays without duplicate element, this can be done with binary search

Input: 1,2,3,4,5
No rotation   : 1,2,3,4,5
Rotate one    : 5,1,2,3,4
Normal Rotate : 3,4,5,1,2
 */
package Leetcode_Java.binary_search_medium;

/**
 *
 * @author Borui Wang
 */
public class FindMin {
    public int findMin(int[] nums) {
        int s = 0, e = nums.length - 1, target = nums[e];
        while(s + 1 < e) {
            int mid = (s + e) >>> 1;
            if(nums[mid] >= target) {
                s = mid;
            } else { //nums[mid] < target
                e = mid;
            }
        }
        //all vals >=,>=,>= [s,e] <, <, < target, value of s could be >= target, which >= e
        //or vals[s,e] <, <, <, <, < target 
        if(nums[s] < nums[e]) {
            return nums[s];
        } else {
            return nums[e];
        }
    }
}
