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
public class ThreeSumClosest {
    static int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        if(n<3) return 0;
        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE;
        for(int i = 0; i<n-2;i++){
            int sum = target - nums[i] -twoSumClosest(nums, i+1, target-nums[i]);
            closest = (Math.abs(target-closest)>Math.abs(sum))?sum:closest;
        }
        return closest;
    }
    static int twoSumClosest(int[] nums, int start, int target){
        //find a sum closest to target
        int lo = start, hi = nums.length-1;
        int closestSum = Integer.MAX_VALUE;
        while(lo<hi){
            int sum = nums[lo]+nums[hi];
            closestSum = (Math.abs(target-closestSum) > Math.abs(target-sum))?sum:closestSum;
            if(sum == target)
                return sum;
            else if(sum < target){
                lo++;
            }
            else{
                hi--;
            }
        }
        return closestSum;
    }
    public static void main(String[] args){
        System.out.println(threeSumClosest(new int[]{-1,2,1,-4},1)) ;
    }
}
