/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_easy;

import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
public class TwoSum {
    public TwoSum(){
        
    }
    public int[] twoSum(int[] nums, int target) {
        int [] result = new int[2];
        int [] origin = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            origin[i]= nums[i];
        }
        Arrays.sort(nums);
           
        for(int i = 0; i < nums.length; i++){
            int temp = nums[i];
            int tempC = target - temp;
            if(biS(tempC, nums, i+1)){
                int indexOne = findIndex(origin, temp, -1);
                int indexTwo = findIndex(origin, tempC,indexOne);
                result[0] = Math.min(indexOne, indexTwo);
                result[1] = Math.max(indexOne, indexTwo);
            }
        }
        return result;
    }
    public boolean biS(int counter, int[] nums, int start){
        int lo = start; int hi = nums.length-1;
        while(lo <= hi){
            int mid = (lo+hi)/2;
            if(nums[mid] == counter) return true;
            else if(nums[mid] > counter) hi = mid -1;
            else lo = mid + 1;
        }
        return false;
    }
    public int findIndex(int [] nums, int toFind, int repeat){
       for(int i = 0; i < nums.length; i++){
           if(nums[i] == toFind && i != repeat) return i;
       }
       return -1;
    }
    public static void main(String []args){
        //{-1,-2,-3,-4,-5};-8
        //{3,2,4}; 6
        //{0,4,3,0}; 0
        int[]a = {3,2,4};  
        int target = 6;
        TwoSum b =  new TwoSum();
        for(int temp: b.twoSum(a,target)){
            StdOut.println(temp);
        }
    }
}
