/*
 *O(m+n) each step eliminate a row or column
*if cur is less than target go right, greater than target go up
 */
package Leetcode_Java.binary_search_medium;

/**
 *
 * @author Borui Wang
 */
public class Search2DMatrix2 {
     static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length - 1, n = matrix[0].length - 1, i = m, j = 0;
        //mistake: while(i > 0 && j < n), should be  while(i >= 0 && j <= n){
        while(i >= 0 && j <= n){
            if(matrix[i][j] == target) {
                return true;
            }
            else if(matrix[i][j] > target) {
                i--;
            }
            else {
                j++;
            }
        }
        return false;
    }
      public static void main(String[] args){
         // int [][] m = new int[][]{{-1},{-1}};
         int [][] m = new int[][]{{-5}};
        System.out.println(searchMatrix(m, -5));
    }
}
