/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamic_programming_medium;

/**
 *
 * @author Borui Wang
 */
public class UniquePathWithObstacles2 {
       static int uniquePathsWithObstacles(int[][] obstacleGrid) {
         
        if(obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 1;
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        int [] moves = new int [n];
        
        for(int i = 0; i < m;i++){
            for(int j = 0; j < n;j++){
                //first row
                if(i == 0){
                    if(obstacleGrid[i][j] == 1)
                        break;
                    moves[j] =1;
                     
                }
                //first column
                else if(j == 0){
                    moves[j] = (obstacleGrid[i][j] == 1)?0:1;
                }
                else{
                    moves[j] = (obstacleGrid[i][j] == 1)?0:moves[j] + moves[j-1];
                }
            }
        }
        return moves[n-1];
    
     
    }
       public static void main(String[] args){
           System.out.println(uniquePathsWithObstacles(new int[][]{{1},{0}}));
       }
}
