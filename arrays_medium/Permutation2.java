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
public class Permutation2 {
    static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lists = new ArrayList();
        List<Integer> list = new ArrayList();
        boolean [] marked = new boolean[nums.length];
        Arrays.sort(nums);
        helper(lists, list, nums, marked);
        return lists;
    }
    static void helper(List<List<Integer>> lists, List<Integer> list, int[] nums, boolean [] marked){
        if(list.size() == nums.length){
            //instantiate new instance of ArrayList
            lists.add(new ArrayList(list));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(!marked[i]){
                //remove duplicate solutions, if the previous one is unmarked, then duplicate, skip, other wise add because it could be part of the solution
                if(i != 0 && nums[i] == nums[i-1] && !marked[i-1]) {
                    continue;
                }
                list.add(nums[i]);
                marked[i] = true;
                helper(lists, list, nums, marked);
                list.remove(list.size() - 1);
                marked[i] = false;
            }
        }
    }
    public static void main(String[] args){
        System.out.println(permuteUnique(new int[]{1,1,2}));
    }
}
