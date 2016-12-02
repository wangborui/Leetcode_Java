/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_medium;

import java.util.ArrayList;
import java.util.Arrays;
 import java.util.LinkedList;
import java.util.List;
 
/**
 *
 * @author Borui Wang
 */
public class ThreeSum {
//    static List<List<Integer>> threeSum(int[] nums) {
//        int n = nums.length;
//        List<List<Integer>> lists = new LinkedList<>();
//        Arrays.sort(nums);
//        for(int i = 0; i < n-1; i++){
//            if(i-1 >=0 &&nums[i] == nums[i-1]) continue;
//            int lo = i+1, hi = n-1;
//            while(lo<hi){
//                int sum = nums[i]+nums[lo]+nums[hi];
//                if(sum == 0){
//                    lists.add(Arrays.asList(nums[i],nums[lo],nums[hi]));
//                    while(lo<hi && nums[lo] == nums[lo+1]) lo++;
//                    while(lo<hi && nums[hi] == nums[hi-1]) hi--;
//                    lo++;
//                    hi--;
//                }
//                else if(sum < 0){
//                    lo++;
//                }
//                else{
//                    hi--;
//                }
//            }
//        }
//    
//        return lists;
// 
//    }
    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new LinkedList();
        Arrays.sort(nums);
        for(int i =0;i<nums.length-1;i++){
            if(i!=0&&nums[i] == nums[i-1])continue;
            int j = i+1, k = nums.length - 1;
            while(j<k){
                while(j<k && nums[j] == nums[j+1]) j++;
                while(j<k && nums[k] == nums[k-1]) k--;
                int sum = nums[i]+nums[j]+nums[k];
                if(sum == 0){
                    lists.add(Arrays.asList(nums[i],nums[j],nums[k]));
                }
                j++;
                k--;
            }
        }
        
        return lists;
    }
     public static void main(String[] args){
         System.out.println(threeSum(new int[]{1,-1,-1,0})); 
 
     }
}
