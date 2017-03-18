// Source : https://oj.leetcode.com/problems/3sum-closest/
// Date   : 01/12/2017

/********************************************************************************** 
* 
* Given an array S of n integers, find three integers in S such that the sum is 
* closest to a given number, target. Return the sum of the three integers. 
* You may assume that each input would have exactly one solution.
* 
*     For example, given array S = {-1 2 1 -4}, and target = 1.
* 
*     The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
* 
*               
**********************************************************************************/

package Leetcode_Java.arrays_medium;

import java.util.Arrays;

/**
 *
 * @author Borui Wang
 */
//solution:  http://en.wikipedia.org/wiki/3SUM
//the idea as blow:
//  1) sort the array.
//  2) take the element one by one, calculate the two sum closest in sorted array.
//
//notes: be careful the duplication number.
//
// for example:
//    [-4,-1,-1,1,2]    target=1
// 
//    take -4, can cacluate the "two sum problem" of the reset array [-1,-1,1,2] while target=5
//    [(-4),-1,-1,1,2]  target=5  distance=4
//           ^      ^ 
//    because the -1+2 = 1 which < 5, then move the `low` pointer(skip the duplication)
//    [(-4),-1,-1,1,2]  target=5  distance=2
//                ^ ^ 
//    take -1(skip the duplication), can cacluate the "two sum problem" of the reset array [1,2] while target=2
//    [-4,-1,(-1),1,2]  target=2  distance=1
//                ^ ^ 
public class ThreeSumClosest {
//    这个题的做法和3 sum 类似，不同的是找到一个三元组nums[i] + nums[j] + nums[k] 他们和target 的差最小
     static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        //sum is farthest away from target
        int closest = target + Integer.MAX_VALUE;
        for(int i = 0; i < n - 2; i++) {
            //remove duplicates, and stop at the first different element
            if(i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int j = i + 1;
            int k = n - 1;
            
            while(j < k) {
                int sum = nums[i] + nums[j] + nums[k]; 
                int diff = Math.abs(target - sum);
                if(diff < Math.abs(target - closest)) {
                    closest = sum;
                }
                //method to move pointer j and k
                if(sum == target) {
                    return sum;
                } else if(sum < target) {
                    //remove duplicates from pointer j, stop at last identical element
                    while(j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    j++;
                } else {
                    //remove duplicates from pointer k, stop at last identical element
                    while(j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    k--;
                }
 
            }
        }
        
        return closest;
    }
    public static void main(String[] args){
        System.out.println(threeSumClosest(new int[]{-1,2,1,-4},1)) ;
    }
}
