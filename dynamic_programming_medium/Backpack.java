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
     * Time O(n * m) Space O(m)
     * We know that we have m + 1 possible weights: 0, 1, 2, ... m - 1, m We
     * know that we have n + 1 possible items : item with no weight, first item
     * A[0], second item A[1] and A[n] We need to first create a 2D boolean
     * array with m + 1 rows and n + 1 columns called dp
     */
//     * Defined
//     * 
//     *      dp[i][j] means we can take out all or a few of the first i items, not including the ith item, and have them sum to a total weight of exactly j
//     * 
//     * Initialization
//     * 
//     *      all values in dp are false initially except the first value dp[0][0] is true
//     *      because if our backpack carries 0 weight, then we can take no items out of the back to fulfill it, therefore, first item is possible candidate
//     * 
//     * Optimal function:
//     *      
//     *     $Take the (i - 1)th item     : dp[i - 1][j - A[i - 1]]
//     *      if we take the (i - 1)th item, we need to know the first (i - 1)th items, 0 ... i - 2 can sum to a total weight of j - A[i - 1](current item weight)
//     * 
//     *     $Not take the (i - 1)th item : dp[i - 1][j]
//     *     if we dont take the (i - 1)th item, we need to know the first (i - 1)th items, 0...i - 2 can sum to a total weight of j
//     * 
//     * 
//     *      dp[i][j] = Take the (i - 1)th item || Not take the (i - 1)th item
//     * 
//     * Pitfalls : we have to make sure j - A[j - 1] >= 0 to make sure if we take the i - 1 th item, we dont exceed bag weight limit
    static int backPack(int m, int[] A) {
        if (m == 0 || A.length == 0 || A == null) {
            return 0;
        }

        int n = A.length;
        int maxWeight = 0;
        //dp[i][j] means for the first i items, not including the ith item, can we take out a few to sum to total weight of j
        boolean[][] dp = new boolean[n + 1][m + 1];
        //initialization: for the first 0 items, can we take out a few to sum to total weight of 0? yes, we dont take any
        dp[0][0] = true;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                //if we take current item, can we sum up to total of j?
                //meaning we we take first i - 1 items, to sum to to total weight of i - A[j - 1]
                boolean take = (j - A[i - 1] >= 0 && dp[i - 1][j - A[i - 1]]);
                //if we do not take current item, can we sum up to total of j?
                boolean notTake = dp[i - 1][j];
                dp[i][j] = take || notTake;
                maxWeight = dp[i][j] ? Math.max(j, maxWeight) : maxWeight;
            }
        }

        return maxWeight;
    }
    //Optimized Space to O(m)
    static int backPackOptimizedSpace(int m, int[] A) {
        if (m == 0 || A.length == 0 || A == null) {
            return 0;
        }

        int n = A.length;
        int maxWeight = 0;
        //dp[i][j] means for the first i items, not including the ith item, can we take out a few to sum to total weight of j
        boolean[][] dp = new boolean[2][m + 1];
        //initialization: for the first 0 items, can we take out a few to sum to total weight of 0? yes, we dont take any
        dp[0][0] = true;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 0; j <= m; j++) {
                //if we take current item, can we sum up to total of j?
                //meaning we we take first i - 1 items, to sum to to total weight of i - A[j - 1]
                boolean take = (j - A[i - 1] >= 0 && dp[(i - 1) % 2][j - A[i - 1]]);
                //if we do not take current item, can we sum up to total of j?
                boolean notTake = dp[(i - 1) % 2][j];
                dp[i % 2][j] = take || notTake;
                //if dp[i][j] is a valid weight, we compare it with max weight
                maxWeight = dp[i % 2][j] ? Math.max(j, maxWeight) : maxWeight;
            }
        }

        return maxWeight;
    }

    static void printArray(boolean[][] a) {
        for (boolean[] x : a) {
            for (boolean y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println(backPack(11, new int[]{2, 3, 5, 7}));
    }
}
