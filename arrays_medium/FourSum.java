// Source : https://oj.leetcode.com/problems/4sum/
// Date   : 01/12/2017

/********************************************************************************** 
* 
* Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
* Find all unique quadruplets in the array which gives the sum of target.
* 
* Note:
* 
* Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
* The solution set must not contain duplicate quadruplets.
* 
*     For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
* 
*     A solution set is:
*     (-1,  0, 0, 1)
*     (-2, -1, 1, 2)
*     (-2,  0, 0, 2)
* 
*               
**********************************************************************************/
package Leetcode_Java.arrays_medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class FourSum {
/*
 * 1) Sort the array,
 * 2) traverse the array, and solve the problem by using "3Sum" soultion.
 * Time O (n^3) Space O(1)
 */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            //remove duplicate and stop on the first different element
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int diff = target - nums[i];
            //there may be multiple three sums starting at index i + 1
            List<List<Integer>> threeSums = getThreeSums(nums, i + 1, diff);
            //if we find three sums, add them to result
            if (!threeSums.isEmpty()) {
                for (List<Integer> threeSum : threeSums) {
                    threeSum.add(0, nums[i]);
                    res.add(new ArrayList(threeSum));
                }
            }
        }
        return res;
    }

    private List<List<Integer>> getThreeSums(int[] nums, int start, int target) {
        List<List<Integer>> res = new ArrayList();
        int n = nums.length;
        for (int i = start; i < n - 2; i++) {
            //remove duplicate and stop on the first different element 
            if (i != start && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    //create list for each unique triple 
                    List<Integer> triple = new ArrayList();
                    triple.add(nums[i]);
                    triple.add(nums[j]);
                    triple.add(nums[k]);
                    res.add(triple);
                    //remove duplicates, stop on the last identical element
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    //remove duplicates, stop on the last identical element
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    j++;
                    k--;
                } else if (sum > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
    }
}
