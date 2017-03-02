//Source : http://www.lintcode.com/en/problem/submatrix-sum/
//Date   : 03/02/2017
/**
 * ******************************************************************************
 * Given an integer matrix, find a submatrix where the sum of numbers is zero. 
 * Your code should return the coordinate of the left-up and right-down number.
 *
 * Have you met this question in a real interview? Yes
 * Example
 * Given matrix
 *
 * [
 * [1 ,5 ,7],
 * [3 ,7 ,-8],
 * [4 ,-8 ,9],
 * ]
 * return [(1,1), (2,2)]
 *******************************************************************************
 */
package Leetcode_Java.dynamic_programming_medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class SubarraySum2 {

//    idea is:
//    
//    find prefix sum of entire matrix O(n^2) n is length of side
//    pSum[i][j] is the prefix sum from matrix[0][0] to matrix[i- 1][j - 1]
//    initialize pSum[0][j] = 0 for all j, p[i][0] = 0 for all i
//    
//    pick a start row and and end row, iterate through each column, and subtract end row col with start row col
//    
//    store the differenc in hash map, and when the same differenc appears again, found the target
//    
//    for example
//    
//                given matrix    [1, 5, 7]
//                                [3, 7, -8]
//                                [4, -8, 9]
//    
//                prefix sum:
//                                  [0, 0, 0, 0]
//                                  [0, 1, 6, 13]
//                                  [0, 4, 16, 15]
//                                  [0, 8, 12, 20]
//                                  
//     given pSum[x][j] - pSum[i][j]
//     
//                                  [0, 0, (i,j), 0]
//                                  [0, 1,   6,   13]
//                                  [0, 4, (x,y), 15]
//                                  [0, 8,   12,  20]
//    we are calculating submatrix sum
//                                  [X, X, 7]
//                                  [X, X, -8]
//                                  [4, -8, 9]
    static int[][] submatrixSum(int[][] matrix) {
        int[][] res = new int[2][2];
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        //pSum[i][j] is the prefix sum from matrix[0][0] to matrix[i- 1][j - 1]
        //initialize pSum[0][j] = 0 for all j, p[i][0] = 0 for all i
        int[][] pSum = new int[m + 1][n + 1];

        //calculate prefix sum
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pSum[i][j] = matrix[i - 1][j - 1] + pSum[i - 1][j] + pSum[i][j - 1] - pSum[i - 1][j - 1];
            }
        }
        printArray(pSum);
        //calculate submatrix sum
        for (int sRow = 0; sRow < m; sRow++) {
            for (int eRow = sRow + 1; eRow <= m; eRow++) {
                //map<diff value, column index>
                Map<Integer, Integer> map = new HashMap();
                //scan column 
                for (int j = 0; j <= n; j++) {
                    int diff = pSum[eRow][j] - pSum[sRow][j];
                    if (map.containsKey(diff)) {
                        int oldCol = map.get(diff);
                        res[0][0] = sRow;
                        res[0][1] = oldCol;
                        res[1][0] = eRow - 1;
                        res[1][1] = j - 1;
                        return res;
                    } else {
                        map.put(diff, j);
                    }
                }
            }
        }
        return res;
    }

    static void printArray(int[][] a) {
        for (int[] num : a) {
            System.out.print("[");
            for (int n : num) {
                System.out.print(n + ",");
            }
            System.out.println("]");
        }
        System.out.println("---------------------------");
    }

    public static void main(String[] args) {
        int[][] in = {{1, 5, 7},
        {3, 7, -8},
        {4, -8, 9}};
//        int[][] in = {{1 ,5 ,7},
//                      {3 ,7 ,-8},
//                      {4 ,-20 ,9}};
        printArray(submatrixSum(in));

    }
}
