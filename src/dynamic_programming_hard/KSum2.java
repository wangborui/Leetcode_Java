//Source : http://www.lintcode.com/en/problem/k-sum-ii/
//Date   : 02/03/2017

/**
 *******************************************************************************
Given n unique integers, number k (1<=k<=n) and target.

Find all possible k integers where their sum is target.

Have you met this question in a real interview? Yes
Example
Given [1,2,3,4], k = 2, target = 5. Return:

[
  [1,4],
  [2,3]
]
*******************************************************************************
 */
package Leetcode_Java.dynamic_programming_hard;

import java.util.ArrayList;

/**
 *
 * @author Borui Wang
 */
public class KSum2 {
     /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return a list of lists of integer 
     */ 
    public ArrayList<ArrayList<Integer>> kSumII(int[] A, int k, int target) {
        // write your code here
        ArrayList<ArrayList<Integer>> res = new ArrayList();
        if(A == null || A.length == 0 || k > A.length) {
            return res;
        }
        dfsHelper(res, new ArrayList(), 0, A, k, target);
        return res;
    }
    
    private void dfsHelper(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> list, int start, int[] A, int k, int remain) {
        if(list.size() == k) {
            if(remain == 0) {
                res.add(new ArrayList(list));
            }
            return;
        }
        
        for(int i = start; i < A.length; i++) {
            list.add(A[i]);
            dfsHelper(res, list, i + 1, A, k, remain - A[i]);
            list.remove(list.size() - 1);
        }
    }
}
