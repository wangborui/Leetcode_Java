// Source : https://oj.leetcode.com/problems/subsets-ii/
// Author : Hao Chen
// Date   : 2014-07-05

/********************************************************************************** 
* 
* Given a collection of integers that might contain duplicates, S, return all possible subsets.
* 
* Note:
* 
* Elements in a subset must be in non-descending order.
* The solution set must not contain duplicate subsets.
* 
* For example,
* If S = [1,2,2], a solution is:
* 
* [
*   [2],
*   [1],
*   [1,2,2],
*   [2,2],
*   [1,2],
*   []
* ]
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
        lists.add(new ArrayList(list));
        
        for(int i = s; i < nums.length; i++){
            //if we were to choose one number amongst duplicate numbers [2`,2``,2```], only choose the first instance 2`, skip 2`` and 2```
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
