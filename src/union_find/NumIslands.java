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
//    首先遍历一次整个2D 数组，再创建一个变量记录我们所有的岛屿
//    在遍历的时候如果碰到一个陆地，就把我们岛屿数量加一
//    然后再深度优先的从上，下，左，右四个方向把该岛屿附近的陆地全部变成海洋
//    这样的话我们相当于是把整个独立的陆地全部变成了海洋
//    继续遍历整个数组，最后返回岛屿个数
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
