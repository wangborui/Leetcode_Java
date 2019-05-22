// Source : https://oj.leetcode.com/problems/search-a-2d-matrix/
// Date   : 01/17/2017

/********************************************************************************** 
* 
* Write an efficient algorithm that searches for a value in an m x n matrix. 
* This matrix has the following properties:
* 
* Integers in each row are sorted from left to right.
* The first integer of each row is greater than the last integer of the previous row.
* 
* For example,
* 
* Consider the following matrix:
* 
* [
*   [1,   3,  5,  7],
*   [10, 11, 16, 20],
*   [23, 30, 34, 50]
* ]
* 
* Given target = 3, return true.
*               
**********************************************************************************/
package Leetcode_Java.binary_search_medium;

/**
 *
 * @author Borui Wang
 */
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        //View the entire matrix as one row from 0 to m*n -1, m is row n is col. 
        int m = matrix.length, n = matrix[0].length, start = 0, end = m * n - 1;
        //Do a binary search on that one whole matrix
        while(start + 1 < end){
            int mid = (start + end) >>> 1;
            //when we find a mid, we need to correspond the value of mid to some val in matrix, matrix[mid/n][mid%n] is that value
            if(matrix[mid / n][mid % n] >= target) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
        if(matrix[start / n][start % n] == target || matrix[end / n][end % n] == target) {
            return true;
        }
        return false;
    }
}
