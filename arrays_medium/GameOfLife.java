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
public class GameOfLife {
    /*
      int [][] a = new int[][]{  {1,  0,  1},
                                 {1,  1,  0},
                                 {0,  1, 0},};
    */
   static void gameOfLife(int[][] board) {
        if(board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;
        //find next state
        for(int i = 0;i<m;i++){
            for(int j = 0; j< n;j++){
                int lives = count(i,j,m,n,board);
                if(board[i][j] == 1 && (lives == 2 || lives == 3)) board[i][j] = 3;
                if(board[i][j] == 0 && lives == 3) board[i][j] = 2;
            }
        }
        
        //update all
        for(int i = 0;i<m;i++){
            for(int j = 0; j< n;j++){
                board[i][j] >>= 1;
            }
        }
        
    }
    static int count(int i,int j, int m, int n, int[][]board){
        int lives = 0;
        for(int a = Math.max(i-1,0);a <= Math.min(i+1,m-1);a++){
            for(int b = Math.max(j-1,0);b<=Math.min(j+1,n-1);b++){
                lives += board[a][b] & 1;
            }
        }
        lives -= board[i][j]&1;
        return lives;
    }
    public static void main(String[] args){
        int [][] a = new int[][]{{1,  0,  1},
                                 {1,  1,  0},
                                 {0,  1, 0},};
        gameOfLife(a);
        for(int[] n:a){
            for(int x:n){
                System.out.print(x+" ");
            }
            System.out.println();
        }
         
     }
}
