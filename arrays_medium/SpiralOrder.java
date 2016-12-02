/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class SpiralOrder {
    static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiral = new ArrayList();
        if(matrix.length == 0 || matrix[0].length ==0) return spiral;
    
        int m = matrix.length-1;
        int n = matrix[0].length-1;
        int i = 0, j = 0;
        while(true){
            for(int a = i; a<=n;a++) spiral.add(matrix[j][a]);
            j++;
            if(i > n || j > m) break;
            for(int a = j;a<=m;a++) spiral.add(matrix[a][n]);
            n--;
            if(i > n || j > m) break;
            for(int a=n;a>=i;a--) spiral.add(matrix[m][a]);
            m--;
            if(i > n || j > m) break;
            for(int a=m;a>=j;a--) spiral.add(matrix[a][i]);
            i++;
            if(i > n || j > m) break;
        }
        return spiral;
    }
//    static List<Integer> spiralOrder(int[][] matrix) {
//        
//         List<Integer> spiralList = new ArrayList<>();
//    if(matrix == null || matrix.length == 0) return spiralList;
//    
//    // declare indices
//    int top = 0;
//    int bottom = matrix.length - 1;
//    int left = 0;
//    int right = matrix[0].length - 1;
//    
//    while(true){
//        // 1. print top row
//        for(int j=left; j <=right;j++){
//            spiralList.add(matrix[top][j]);
//        }
//        top++;
//        if(left>right || bottom<top)
//            break;
//        
//        // 2. print rightmost column
//        for(int i=top; i <= bottom; i++){
//            spiralList.add(matrix[i][right]);
//        }
//        right--;
//        if(left>right || bottom<top)
//            break;
//            
//        // 3. print bottom row
//        for(int j=right; j >=left; j--){
//            spiralList.add(matrix[bottom][j]);
//        }
//        bottom--;
//        if(left>right || bottom<top)
//            break;    
//            
//        // 4. print leftmost column
//        for(int i=bottom; i >= top; i--){
//            spiralList.add(matrix[i][left]);
//        }
//        left++;
//        if(left>right || bottom<top)
//            break;    
//    }// end while true
//    
//    return spiralList;
//    }
    public static void main(String[] args){
       System.out.println(spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
        //System.out.println(spiralOrder(new int[][]{{2,5},{8,4},{0,-1}}));
     }
}
