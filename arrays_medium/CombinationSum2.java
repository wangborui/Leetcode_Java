/*
Source: https://leetcode.com/problems/combination-sum/
Date: 01/02/2017
********************************************************************************
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
********************************************************************************
 */
package Leetcode_Java.arrays_medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        if(candidates.length == 0) {
            return lists;
        }
        //sort numbers to get all duplicate numbers next to each other
        Arrays.sort(candidates);
        combo(lists, new ArrayList(), candidates, 0, target);
        return lists;
    }
    private void combo(List<List<Integer>> lists,List<Integer> temp, int[] candidates, int start, int remain ){
        if(remain < 0) return;
        else if(remain == 0) lists.add(new ArrayList(temp));
        else{
            for(int i = start; i < candidates.length; i++){
                //if we were to choose one number amongst duplicate numbers [2`,2``,2```], only choose the first instance 2`, skip 2`` and 2```
                if(i != start && candidates[i] == candidates[i-1]) continue;
                temp.add(candidates[i]);
                combo(lists, temp, candidates, i+1, remain-candidates[i]);
                temp.remove(temp.size()-1);
            }
        }
    }
}
