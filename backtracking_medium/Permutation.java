/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backtracking_medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class Permutation {
    static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        boolean [] marked = new boolean[nums.length];
        for(int i=0; i<nums.length; i++){
            if(i>0 && nums[i] == nums[i-1]) continue;
            perm(lists, new ArrayList(), nums, marked);
        }
         
        return lists;
    }
    static void perm(List<List<Integer>> lists, List<Integer> temp, int[]nums, boolean[] marked){
        if(temp.size() == nums.length) lists.add(new ArrayList(temp));
        else{
            for(int i=0; i<nums.length; i++){
                 
                if(!marked[i]){
                    temp.add(nums[i]);
                    marked[i] = true;
                    perm(lists,temp,nums, marked);
                    temp.remove(temp.size()-1);
                    marked[i] = false;
                }   
            }
        }
    }
      public static void main(String[] args){
        System.out.println(permute(new int[]{1,2,1})) ;
    }
}
