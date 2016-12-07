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
        //find the first number >= target, if that num is not found return nums.length
        //possibilities, number >= target or number < target
        int s = 0, e = nums.length - 1, res = nums.length;
        while(s + 1 < e){
            int mid = (s + e) >>> 1;
            if (nums[mid] >= target) {
                e = mid;
            }
            else {
                s = mid;
            }
        }
        if(nums[e] >= target) {
            res = e;
        }
        if(nums[s] >= target) {
            res = s;
        }
        return res;
    }
}
