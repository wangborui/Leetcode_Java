/*
source: https://leetcode.com/problems/remove-element/
 Given an array and a value, remove all instances of that value in place and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example:
Given input array nums = [3,2,2,3], val = 3

Your function should return length = 2, with the first two elements of nums being 2.

Hint:

Try two pointers.
Did you use the property of "the order of elements can be changed"?
What happens when the elements to remove are rare?

Solution:
Time O(n) space O(1)
use a pointer j to represent 0- - (not val values) - - j - - (val) - - i - (not processed num) - -n
 */
package Leetcode_Java.arrays_easy;

import edu.princeton.cs.algs4.StdOut;
 
/**
 *
 * @author Borui Wang
 */
public class RemoveElement {
    static int removeElement(int[] nums, int val) {
 
         int j = 0;
         for(int i = 0; i < nums.length; i++){
             if(nums[i]!= val)
                 nums[j++] = nums[i];
         }
         return j;
    }
    public static void main(String [] args){
        //int [] dig = {3,2,2,3,33,3,4};
         int [] dig = {44};
        StdOut.println(removeElement(dig,3) );
    }
}
