/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_easy;

import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
public class IntersectionOfTwoArrays2 {
    //my solution: fails on  int[] nums1 = {1};int[] nums2 = {1,1};
         
    static int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length == 0 || nums2.length == 0) return new int[0];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int count = 0;
        int nums2Index = 0;
        int [] result = new int[Math.min(nums1.length, nums2.length)];
        
        for(int i= 0; i< Math.max(nums2.length, nums1.length); i++){
            if(nums1[i] == nums2[nums2Index]){
                result[count++] = nums1[i];
                nums2Index++;
            }
            else if(nums1[i] <  nums2[nums2Index]) continue;
            else{
                i--;
                nums2Index++;
            }
            if(nums2Index == nums2.length) break;
        }
        int [] temp = new int[count];
        for(int i = 0 ;i< count; i++){
            temp[i] = result[i];
        }
        
        return temp;
    }
    public static void main(String[] args){
//        int[] nums1 = {1,2};
//        int[] nums2 = {1,1};
 int[] nums1 = {1};
        int[] nums2 = {1,1};
//        int[] nums1 = {1,2,2,1};
//        int[] nums2 = {2};
        intersect(nums1,nums2);
        
    }
}
