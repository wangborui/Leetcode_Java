/*
Source:https://leetcode.com/problems/remove-duplicates-from-sorted-array/

********************************************************************************
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.

Solution:
Time O(n) space O(1)
use a pointer j to represent 0- - (unique vals) - - j - - (duplicate) - - i - (not processed num) - -n
check if  nums[i] != nums[i - 1] for duplicates

Follow up: what if we allow up to 2 duplicates in the array
********************************************************************************
invariant: [<- - - single element - - ->j<-removed elements->i<-unexplored area->]
Watch out: j starts out 1, because we do not ever remove the first element, 
if the second element is duplicate of 1st, we remove the second, otherwise we do not remove anything
 */
package Leetcode_Java.arrays_easy;

/**
 *
 * @author Borui Wang
 */
public class RemoveElementFromSortedArray {
    static int removeDuplicates(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        //skip the first elment, because the first one is always unique
        int j = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[i - 1]) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
    public static void main(String [] args){
         int [] dig = {2,2,3,3,3,4,33};
        // int [] dig = {44};
        System.out.println(removeDuplicates(dig) );
        for(int num:dig){
            System.out.print(num + " ");
        }
        
    }
}
