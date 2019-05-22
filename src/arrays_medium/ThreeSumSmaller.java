/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_medium;

import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
public class ThreeSumSmaller {
    static int threeSumSmaller(int[] nums, int target) {
        int n = nums.length;
        if(n<3) return 0;
        Arrays.sort(nums);
        int count = 0;
        for(int i = 0; i < n;i++){
            if(i>0 && nums[i] == nums[i-1]) continue;
            int l = i+1, h = n-1;
            while(l<h){
                int sum = nums[i] + nums[l]+nums[h];
                if(sum < target){      
                    while(l<h && nums[l] == nums[l+1]) l++;
                    while(l<h && nums[h] == nums[h-1]) h--;
                    count += h-l;
                    l++;
                }
                else {
                    h--;
                }
            }
        }
        return count;
    }
    public static void main(String[] args){
        System.out.println(threeSumSmaller(new int[]{1,1,-2},1)) ;
    }
}
