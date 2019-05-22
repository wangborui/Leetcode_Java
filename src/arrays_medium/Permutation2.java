// Source : https://oj.leetcode.com/problems/permutations-ii/
// Date   : 01/26/2017

/********************************************************************************** 
* 
* Given a collection of numbers that might contain duplicates, return all possible unique permutations.
* 
* For example,
* [1,1,2] have the following unique permutations:
* [1,1,2], [1,2,1], and [2,1,1].
* 
*               
**********************************************************************************/
package Leetcode_Java.arrays_medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class Permutation2 {
// To deal with the duplication number, we need do those modifications:
//    1) sort the array [pos..n].
//    2) skip the same number. How?
//      everytime we do dfs on the array, we need to choose a UNIQUE number as the start of dfs recursion
//      we know that if a number is the same as its previous number, and the previous number is not marked(not choosen as start of a dfs recursion)
//      this number then must be unique, because previous number must have been choosen in previous dfs recursion stack, so we can skip current number
//    for example we have nums{1,1,2}, [x] means x is already chosen, {y} means y can be viable candidate for next dfs
//                                                {1,1,2}
//                                             /         |    
//                                          /            |     
//                                     [1]{1,2}       [2]{1,1}    
//                                      /    \           |   
//                                    /       \          |    
//                                 [1,1]{2}[1,2]{1}   [2,1]{1} 
//    Therefore, we have answers
//    we have [1,1,2],[1,2,1],[2,1,1]
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
