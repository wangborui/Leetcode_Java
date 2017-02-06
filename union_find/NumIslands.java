// Source : https://leetcode.com/problems/number-of-islands/
// Date   : 02/06/2017

/********************************************************************************** 
 * 
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 * You may assume all four edges of the grid are all surrounded by water.
 * 
 * Example 1:
 *   11110
 *   11010
 *   11000
 *   00000
 * Answer: 1
 * 
 * Example 2:
 *   11000
 *   11000
 *   00100
 *   00011
 * Answer: 3
 * 
 * Credits:Special thanks to @mithmatt for adding this problem and creating all test cases.
 *               
 **********************************************************************************/
package Leetcode_Java.union_find;

/**
 *
 * @author Borui Wang
 */
public class NumIslands {
//DFS traversal, traverse each node in the grid, if this node is land, change it to water, and DFS traverse its 4 neighbors
    static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int islands = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    dfsMark(grid, i, j);
                }
            }
        }
        return islands;
    }

    static void dfsMark(char[][] grid, int i, int j) {
        //check if i and j are in bounds, and also make sure current node is not water
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        //top, down, left, right
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        for (int n = 0; n < 4; n++) {
            dfsMark(grid, i + dx[n], j + dy[n]);
        }
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '0', '0'},
        {'0', '1', '0', '0'},
        {'0', '0', '1', '1'}};
        System.out.println(numIslands(grid));
    }
}
