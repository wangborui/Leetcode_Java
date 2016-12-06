/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.arrays_medium;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class Permutations {
    static List<List<Integer>> permutations(int[] nums) {
        List<List<Integer>> lists = new ArrayList();
        List<Integer> list = new ArrayList();
        //adding new boolean array variable to keep track of marked 
        boolean [] marked = new boolean[nums.length];
        permutationsHelper(lists, list, nums, marked);
        return lists;
    }
    //removed int s, because for permutation we always start with 0 and skips marked ones
    static void permutationsHelper(List<List<Integer>> lists, List<Integer> list, int[] nums,boolean [] marked){
        //use new ArrayList because each iteration contents in list gets altered. 
        if(list.size() == nums.length){
            lists.add(new ArrayList<Integer>(list));
            return;
        }
         for(int i = 0; i < nums.length; i++){
             if(!marked[i]){
                 list.add(nums[i]);
                 marked[i] = true;
                 permutationsHelper(lists, list, nums, marked);
                 list.remove(list.size()-1);
                 marked[i] = false;
             }
        }
    }
    public static void main(String[] args){
        System.out.println(permutations(new int[]{1,2,3}));
    }
}
