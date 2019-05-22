/*
Source: https://leetcode.com/problems/two-sum/
Date  : 01/12/2017
********************************************************************************
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
********************************************************************************
 */

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class TwoSum {
    //use hashmap, time O(n), space O(n)
//    遍历整个数组，将每个元素记录到哈希表，并且记录元素下标
//    当我们遍历第i个数字时，计算target 和元素i的差，然后检查这个差是否已经存在哈希表中
//    如果有的话，我们就找到了两个元素他们的和为target，输出他们的下标
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap();
        int [] res = new int[2];
        for(int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            //see if diff already exists in hashmap
            if(map.containsKey(diff)) {
                res[0] = map.get(diff);
                res[1] = i;
                return res;
            } else {
                //stores number into hashmap
                map.put(nums[i], i);
            }
        }
        
        return res;
    }
    public static void main(String []args){
        //{-1,-2,-3,-4,-5};-8
        //{3,2,4}; 6
        //{0,4,3,0}; 0
        int[]a = {3,2,4};  
        int target = 6;
        TwoSum b =  new TwoSum();
        for(int temp: b.twoSum(a,target)){
            System.out.println(temp);
        }
    }
}
