/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binary_search_medium;

/**
 *
 * @author Borui Wang
 */
public class SearchRange {
    static int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0) return new int[]{-1,-1};
        int s = 0, e = nums.length - 1, left = -1, right = -1;
        //find left start
        while(s + 1 < e){
            int mid = (s + e) >>> 1;
            if(nums[mid] == target) {
                e = mid;
            }
            else if(nums[mid] > target) { 
                e = mid;
            }
            else {
                s = mid;
            }
        }
        //when searching for left index compare end first, ex: 4,8,8 t = 8 e = 2, s = 1, so we need e first then s
        if(nums[e] == target) {
            left = e;
        }
        if(nums[s] == target){
            left = s;
        }
         
        s = 0;
        e = nums.length - 1;
        //find right index
        while(s + 1 < e){
            int mid = (s + e) >>> 1;
            if(nums[mid] == target) {
                s = mid;
            }
            if(nums[mid] > target) {
                e = mid;
            }
            else {
                s = mid;
            }
        }
        //when searching for right index compare end first, ex: 4,8,8,9 t = 8 e = 2, s = 3, so we need e first then s
        if(nums[s] == target){
            right = s;
        }
        if(nums[e] == target) {
            right = e;
        }
        return new int[]{left, right};
    }
 
  
    public static void main(String[] args){
        System.out.println(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)[0] + ":" + searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)[1]);
    }
}
