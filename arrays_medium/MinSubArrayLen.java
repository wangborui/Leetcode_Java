/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_medium;

/**
 *
 * @author Borui Wang
 */
public class MinSubArrayLen {
     static int minSubArrayLen(int s, int[] nums) {
         int [] sums = new int[nums.length];
         sums[0] = nums[0];
         int min = Integer.MAX_VALUE;
         for(int i = 1; i<nums.length;i++){
             sums[i] = sums[i-1] + nums[i];
         }
   
         for(int i = 0; i <nums.length;i++){
             int end = bs(i,nums.length-1,s+(i==0?0:sums[i-1]),sums);
             if(end == nums.length) break;
             min = Math.min(end-i+1, min);
         }
         return  min;
     }
     static int bs(int l, int r, int target, int[]sums){
         int min = 0;
         while(l <= r){
             int mid = (l+r)>>>1;
             if(sums[mid] >= target) r = mid - 1;
             else l = mid + 1;
         }
         return l;
     }
     public static void main(String[] args){
         System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,1,3}));
     }
}
