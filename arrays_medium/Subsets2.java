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
public class Subsets2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> lists = new ArrayList();
        List<Integer> list = new ArrayList();
        Arrays.sort(nums);
        subsetsHelper(lists, list, nums, 0);
        return lists;
    }
    private void subsetsHelper(List<List<Integer>> lists, List<Integer> list, int[] nums, int s){
        //create new instance of list
        lists.add(new ArrayList<Integer>(list));
        
        for(int i = s; i < nums.length; i++){
            if(i != s && nums[i] == nums[i - 1]){
                continue;
            }
            list.add(nums[i]);
            //this is i+1 not s+1
            subsetsHelper(lists, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
