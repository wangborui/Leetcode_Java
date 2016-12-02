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
public class MaxKilledEnemies {
    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length, n =grid[0].length;
        if(grid == null || m == 0|| n == 0) return 0;
        int max = 0, row = 0;
        int [] col = new int[n];
        for(int i = 0; i < m;i++){
            for(int j = 0; j < n;j++){
                if(grid[i][j] == 'W' || grid[i][j] == 'E') continue;
                if(j == 0 || grid[i][j-1] == 'W') row = calcRow(grid,i,j);
                if(i == 0 || grid[i-1][j] == 'W') col[j] = calcCol(grid,i,j);
                if(grid[i][j] == '0') max = Math.max(row+col[j],max);
            }
        }
        return max;
        /*
        {{'0','E','0','0',},
         {'E','0','W','E',},
         {'0','E','0','0',},}
        */
    }
    private int calcRow(char[][]grid, int i, int j){
        int killed = 0;
        for(;j<grid[0].length && grid[i][j] != 'W';j++)
            if(grid[i][j] == 'E')
                killed++;
        return killed;
    }
    private int calcCol(char[][]grid, int i, int j){
        int killed = 0;
        for(;i<grid.length && grid[i][j] != 'W';i++)
            if(grid[i][j] == 'E')
                killed++;
        return killed;
    }
    public static void main(String[] args){
        char [][] grid = {{'0','E','0','0',},
                          {'E','0','W','E',},
                          {'0','E','0','0',},};
        MaxKilledEnemies ms = new MaxKilledEnemies();
       System.out.println( ms.maxKilledEnemies(grid)); 
       System.out.print(true || grid[-1][2] == 'W');
    }
}
