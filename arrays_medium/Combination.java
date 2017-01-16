
// Source : https://oj.leetcode.com/problems/combinations/
// Date   : 01/16/2017

/********************************************************************************** 
* 
* Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
* 
* For example,
* If n = 4 and k = 2, a solution is:
* 
* [
*   [2,4],
*   [3,4],
*   [2,3],
*   [1,2],
*   [1,3],
*   [1,4],
* ]
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

//remember the starting position, then add the next charracter to create a new subset and do DFS, 
//remove the last element in list before next backtracking
//when list size reaches k, return recursion
public class Combination {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> lists = new ArrayList();
        List<Integer> list = new ArrayList();
        combineHelper(lists, list, n, k, 1);
        return lists;
    }
    private void combineHelper(List<List<Integer>> lists, List<Integer> list, int n, int k, int start){
        if(list.size() == k) {
            //instantiate a new instance of arraylist
            lists.add(new ArrayList(list));
            return;
        }
        
        for(int i = start; i <= n; i++){
            list.add(i);
            combineHelper(lists, list, n, k, i+1);
            list.remove(list.size() - 1);
        }
    }
}
