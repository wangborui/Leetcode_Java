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
public class Combination {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> lists = new ArrayList();
        List<Integer> list = new ArrayList();
        combineHelper(lists, list, n, k, 1);
        return lists;
    }
    private void combineHelper(List<List<Integer>> lists, List<Integer> list, int n, int k, int s){
        if(list.size() == k) {
            //instantiate a new instance of arraylist
            lists.add(new ArrayList(list));
            return;
        }
        
        for(int i = s; i <= n; i++){
            list.add(i);
            combineHelper(lists, list, n, k, i+1);
            list.remove(list.size() - 1);
        }
    }
}
