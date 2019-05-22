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
public class SpiralMatrix2 {
    static int[][] generateMatrix(int n) {
        if(n <= 0)
            return new int [0][0];
         
             
            
        int left = 0,top =0;
        int bot = n-1,right=n-1;
        int [][] res = new int[n][n];
        int num = 1;
        
        while(true){
            for(int i =left; i<=right;i++){
                res[top][i] = num++;
            }
            top++;
            if(num > n*n) break;
            
            for(int i = top; i <= bot;i++){
                res[i][right] = num++;
            }
            right--;
            if(num > n*n) break;
            
            for(int i = right; i >= left;i--){
                res[bot][i] = num++;;
            }
            bot--;
            if(num > n*n) break;
            
            for(int i = bot; i>=top;i--){
                res[i][left] = num++;
            }
            left++;
            if(num > n*n) break;
        }
        return res;
    }
     public static void main(String[] args){
        for(int[] num: generateMatrix(1)){
            for(int n: num ){
                System.out.print(n);
            }
            System.out.println();
        } 
        //System.out.println(spiralOrder(new int[][]{{2,5},{8,4},{0,-1}}));
     }
}
