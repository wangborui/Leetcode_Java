/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.binary_search_medium;

/**
 *
 * @author Borui Wang
 */
public class SearchInsert {
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
}
