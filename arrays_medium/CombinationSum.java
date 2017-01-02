/*
Source: https://leetcode.com/problems/combination-sum/
Date: 01/02/2017
********************************************************************************
Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 
[
  [7],
  [2, 2, 3]
]
********************************************************************************
 */
package Leetcode_Java.arrays_medium;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList();
        List<Integer> list = new ArrayList();
        helper(lists, list, candidates, target, 0);
        return lists;
    }
    private void helper(List<List<Integer>> lists, List<Integer> list, int[] candidates, int remain, int s){
        if(remain == 0) {
            //new instance of list, because it changes NOT lists.add(list)
            lists.add(new ArrayList(list));
            return;
        }
        //cut off the recursion when remaining sum < 0 or sum of candidates > target
        else if(remain < 0){
            return;
        }
        for(int i = s; i < candidates.length; i++){
            list.add(candidates[i]);
            //use i instead of i + 1, because the same index can be used many times
            helper(lists, list, candidates, remain - candidates[i], i);
            list.remove(list.size() - 1);
        }
    }
}
