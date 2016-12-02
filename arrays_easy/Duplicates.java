package arrays_easy;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class Duplicates {
    //my solution runs out of time 
    static boolean containsDuplicate(int[] nums) {
        int n = nums.length;
        if(n <= 1) return false;
        HashSet<Integer> stack = new HashSet();
        for(int num: nums){
            if(stack.contains(num))
                return true;
            stack.add(num);
        }
        return false;
    }
    //solution found
    /*
    public boolean containsDuplicate(int[] nums) {
    Set<Integer> set = new HashSet<>(nums.length);
    for (int x: nums) {
        if (set.contains(x)) return true;
        set.add(x);
    }
    return false;
}
    */
      public static void main(String[] args){
        
 StdOut.println(containsDuplicate(new int[]{1,2,3,4,5,6,7,8,8}));
    }
}
