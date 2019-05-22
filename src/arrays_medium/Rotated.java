/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_medium;

/**
 *
 * @author Borui Wang
 */
public class Rotated {
//    static void rotate(int[][] matrix) {
//        int n = matrix.length;
//        int [][] rotated = new int[n][n];
//        
//        for(int r = 0; r < n; r++){
//            for(int c = 0; c < n; c++){
//                rotated[c][n-1-r] = matrix[r][c];
//            }
//        }
//        matrix = rotated;
//    }
      static void rotate(int[][] matrix) {
        for(int i = 0; i<matrix.length; i++){
            for(int j = i; j<matrix[0].length; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;
            }
        }
    }
    public static void main(String[] args){
       rotate(new int[][]{{1,2},{3,4}});
    }
}
