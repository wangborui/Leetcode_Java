//Source : https://www.lintcode.com/en/problem/backpack/
//Date   : 02/01/2017
/**
 * *****************************************************************************
 * Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
 *
 *
 * You can not divide any item into small pieces.
 *
 * Have you met this question in a real interview? Yes
 * Example
 * If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, 
 * we can select [2, 3, 5], so that the max size we can fill this backpack is 10. 
 * If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.
 *
 * You function should return the max size we can fill in the given backpack.
 *
 * Challenge
 * O(n x m) time and O(m) memory.
 *
 * O(n x m) memory is also acceptable if you do not know how to optimize memory.
 ******************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class Backpack {

    /**
     * 
     * @param m
     * @param A
     * @return Integer
     * 
     * Analysis:
     * 
     * We know that we have m + 1 possible weights: 0, 1, 2, ... m - 1, m
     * We know that we have n + 1 possible items  : item with no weight, first item A[0], second item A[1] and A[n]
     * We need to first create a 2D boolean array with m + 1 rows and n + 1 columns called dp
     * 
     * Defined
     * 
     *      dp[i][j] means we can take out all or a few of the first j items and have them sum to a total weight of exactly i
     * 
     * Initialization
     * 
     *      all values in dp are false initially except the first row dp[0][j] are all true
     *      because if our backpack carries 0 weight, then we can take no items out of the back to fulfill it, therefore, all items are possible candidates
     * 
     * Optimal function:
     *      
     *      dp[i][j] = dp[i - A[j - 1]][j - 1]  || dp[i][j - 1]
     *      if we take the first j items to sum to weight i, or we do not take the first j items to sum to weight i
     * 
     * Pitfalls : we have to make sure i - A[j - 1] >= 0 to make sure if we take the j - 1 th item, we dont exceed bag weight limit
     */
    static int backPack(int m, int[] A) {
        if (m == 0 || A.length == 0 || A == null) {
            return 0;
        }

        int n = A.length;

        //dp[i][j] means for the first i items, can we take out a few to sum to total weight of j
        boolean[][] dp = new boolean[m + 1][n + 1];
        //initialization: for the first i items, can we take out a few to sum to total weight of 0? yes, we dont take any
        for(int i = 0; i <= n; i++) {
            dp[0][i] = true;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                boolean notTake = dp[i][j - 1];
                boolean take = (i - A[j - 1] >= 0) ? dp[i - A[j - 1]][j - 1] : false;
                dp[i][j] = take || notTake;
            }
        }
        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (dp[i][j]) {
                    return i;
                }
            }
        }
        return 0;
    }
    static void printArray(boolean [][] a) {
        for(boolean []x : a) {
            for(boolean y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }
    public static void main(String [] args) {
        System.out.println(backPack(11, new int[]{2,3,5,7}));
    }
}
