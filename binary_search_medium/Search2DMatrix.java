/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
