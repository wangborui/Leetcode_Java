/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.arrays_medium;

/**
 *
 * @author Borui Wang
 */
public class SetZeroes {

    static void setZeroes(int[][] matrix) {
        int col0 = 1, rows = matrix.length, cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                col0 = 0;
            }
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col0 == 0) {
                matrix[i][0] = 0;
            }
        }
        print(matrix);
    }
    static void print(int[][] a) {
        for(int[] nums : a) {
            for(int n : nums) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        int[][] m = {{0,2,3},{4,5,6},{2,6,0}};
        setZeroes(m);
        //print(m);
    }
}
