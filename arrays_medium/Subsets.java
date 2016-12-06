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
public class Subsets {
    static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList();
        List<Integer> list = new ArrayList();
        subsetsHelper(lists, list, nums, 0);
        return lists;
    }
    static void subsetsHelper(List<List<Integer>> lists, List<Integer> list, int[] nums, int s){
        //use new ArrayList because each iteration contents in list gets altered. 
        lists.add(new ArrayList<Integer>(list));
        //add checks for null nums
        if(nums == null) return;
         for(int i = s; i < nums.length; i++){
            list.add(nums[i]);
            subsetsHelper(lists, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
    public static void main(String[] args){
        System.out.println(subsets(new int[]{1,2,3}));
    }
}
