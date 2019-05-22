// Source : https://leetcode.com/problems/combination-sum-iii/
// Date   : 01/17/2016

/********************************************************************************** 
 * 
 * Find all possible combinations of k numbers that add up to a number n, 
 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * Ensure that numbers within the set are sorted in ascending order.
 * 
 *  Example 1:
 * Input:  k = 3,  n = 7
 * Output: 
 * 
 * [[1,2,4]]
 * 
 *  Example 2:
 * Input:  k = 3,  n = 9
 * Output: 
 * 
 * [[1,2,6], [1,3,5], [2,3,4]]
 * 
 * Credits:Special thanks to @mithmatt for adding this problem and creating all test cases.
 *               
 **********************************************************************************/
package Leetcode_Java.arrays_medium;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        int [] candidates = new int[] {1,2,3,4,5,6,7,8,9};
        List<List<Integer>> lists = new ArrayList();
        List<Integer> list = new ArrayList();
        helper(lists, list, candidates, n, k, 0);
        return lists;
        
    }
    private void helper(List<List<Integer>> lists , List<Integer> list, int[] candidates, int rem, int size, int s) {
        if(rem == 0 && list.size() == size) {
            lists.add(new ArrayList(list));
            return;
        }
        else if(rem < 0) {
            return;
        }
        
        for(int i = s; i < candidates.length; i++) {
            list.add(candidates[i]);
            helper(lists, list, candidates, rem - candidates[i], size, i + 1);
            list.remove(list.size() - 1);
        }
    }

}
