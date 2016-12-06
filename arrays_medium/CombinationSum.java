/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.arrays_medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList();
        List<Integer> list = new ArrayList();
        //there may be duplicate values in candidates, but our answer must not have duplicates
        Arrays.sort(candidates);
        helper(lists, list, candidates, target, 0);
        return lists;
    }
    private void helper(List<List<Integer>> lists, List<Integer> list, int[] candidates, int remain, int s){
        if(remain == 0) {
            //new instance of list, because it changes 
            lists.add(new ArrayList(list));
            return;
        }
        //cut off the recursion when remaining sum < 0 or sum of candidates > target
        else if(remain < 0){
            return;
        }
        for(int i = s; i < candidates.length; i++){
            //remove duplicates from the solution 
            if(i != s && candidates[i] == candidates[i-1]) {
                continue;
            }
            list.add(candidates[i]);
            //use i instead of i + 1, because the same index can be used many times
            helper(lists, list, candidates, remain - candidates[i], i);
            list.remove(list.size() - 1);
        }
    }
}
