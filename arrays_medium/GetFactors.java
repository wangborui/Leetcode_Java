//Source : https://leetcode.com/problems/factor-combinations/#/description
//Date   : 03/21/2017
/**
 * *************************************************************************************
 * Numbers can be regarded as product of its factors. For example,
 *
 * 8 = 2 x 2 x 2;
 * = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 *
 * Note:
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 * Examples:
 * input: 1
 * output:
 * []
 * input: 37
 * output:
 * []
 * input: 12
 * output:
 * [
 * [2, 6],
 * [2, 2, 3],
 * [3, 4]
 * ]
 * input: 32
 * output:
 * [
 * [2, 16],
 * [2, 2, 8],
 * [2, 2, 2, 4],
 * [2, 2, 2, 2, 2],
 * [2, 4, 4],
 * [4, 8]
 * ]
 **************************************************************************************
 */
package Leetcode_Java.arrays_medium;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class GetFactors {

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList();
        dfsHelper(res, new ArrayList(), n, 2);
        return res;
    }

    private void dfsHelper(List<List<Integer>> res, List<Integer> solution, int n, int start) {
        if (n == 1) {
            //this line is very important
            //because we are finding solutions from smallest factor to largest
            //and valid factors must exist in pairs, if we only found one solution that is the number n itself, we only have one solution[n]
            //which has a sise of 1, and therefore, not added to our result
            if (solution.size() > 1) {
                res.add(new ArrayList(solution));
            }
        }

        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                solution.add(i);
                dfsHelper(res, solution, n / i, i);
                solution.remove(solution.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        //System.out.println(getFactors(4));
    }
}
