/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_easy;

import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;

/**
 *
 * @author Borui Wang
 */
public class MajorityElement {
//    static int majorityElement(int[] nums) {
//        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//        int n = nums.length;
//        for(int i = 0; i < n; i++){
//            if(map.containsKey(nums[i])){
//                int times = map.get(nums[i]);
//                if(++times > n/2) return nums[i];
//                map.put(nums[i], times);
//            }
//            else{
//                map.put(nums[i], 1);
//            }
//        }
//        return n;
//    }
    //solution found
    static int majorityElement(int[] nums) {
        int majority = nums[0]; int count = 1;
        for(int i = 1; i< nums.length; i ++){
            if(nums[i] == majority)
                count++;
            else if(count == 0){
                majority = nums[i];
                count++;
            }
            else
                count--;
        }
        return majority;
    }
    
    public static void main(String[] args){
        int [] a = {2147483647};
        StdOut.println(Integer.MAX_VALUE);
       StdOut.println(majorityElement(a));
    }
}
