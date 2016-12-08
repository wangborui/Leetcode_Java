/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.arrays_easy;

import edu.princeton.cs.algs4.StdOut;
 
/**
 *
 * @author Borui Wang
 */
public class RemoveElement {
    static int removeElement(int[] nums, int val) {
         int n = nums.length;
         if(n == 0) return 0;
         int count = 0;
         for(int i = 0; i < n; i++){
             if(nums[i]!= val)
                 nums[count++] = nums[i];
         }
         return count;
    }
    public static void main(String [] args){
        int [] dig = {3,2,2,3,33,3,4};
         
        StdOut.println(removeElement(dig,3) );
    }
}
