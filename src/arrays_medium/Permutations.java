// Source : https://oj.leetcode.com/problems/permutations/
// Date   : 01/26/2017

/********************************************************************************** 
* 
* Given a collection of numbers, return all possible permutations.
* 
* For example,
* [1,2,3] have the following permutations:
* [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
* 
*               
**********************************************************************************/
package Leetcode_Java.arrays_medium;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class Permutations {
//    time complexity is O(n!)
//    for example we have nums{1,2,3}, [x] means x is already chosen, {y} means y can be viable candidate for next dfs
//                                                {1,2,3}
//                                              /    |    \
//                                          /        |        \
//                                     [1]{2,3}   [2]{1,3}    [3]{1,2}
//                                 /    \          /    \         /    \ 
//                               /       \        /      \       /      \
//                           [1,2]{3}[1,3]{2} [2,1]{3}[2,3]{1}  [3,1]{2}[3,2]{1}
//    Therefore, we have answers
//    we have [1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]
    static List<List<Integer>> permute(int[] nums) {
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
            lists.add(new ArrayList(list));
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
        System.out.println(permute(new int[]{1,2,3}));
    }
}
