//Source : https://leetcode.com/problems/number-of-islands-ii/?tab=Solutions
//Date   : 02/06/2017
/**
 * *****************************************************************************
 * A 2d grid map of m rows and n columns is initially filled with water.
 * We may perform an addLand operation which turns the water at position (row, col) into a land.
 * Given a list of positions to operate, count the number of islands after each addLand operation.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example:
 *
 * Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 *
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 *
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 *
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 *
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 *
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 * We return the result as an array: [1, 1, 2, 3]
 *
 * Challenge:
 *
 * Can you do it in time complexity O(k log mn), where k is the length of the positions?
 *******************************************************************************
 */
package Leetcode_Java.union_find;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class NumIslands2 {
    
//    Analysis:
//    
//    We should use union find to solve this question.
//    We are initially given an m * n matrix, and each element in the matrix has a parent which is itself,
//    we use a roots array to represent root of each element, root[i] means the root of element i, i = row * n + col
//    We use visited array to represent if a position has been added into the matrix, 
//    visited[i] = 0 is not visited, visited[i] = 1 is visited. To calculate i, i = row * n + col
//    When we add a position, we first increase islands count by 1, then reduce islands number if we can merge neighboring islands
//            
//    for example, m = 3, n = 3
//                        Index  0 1 2 3 4 5 6 7 8 9
//               0 0 0   visited[0 0 0 0 0 0 0 0 0 0]       
//               0 0 0     roots[0 1 2 3 4 5 6 7 8 9]
//               0 0 0    islands: 0
//               
//    addLand(0, 0):
//                        Index  0 1 2 3 4 5 6 7 8 9
//               1 0 0   visited[1 0 0 0 0 0 0 0 0 0]       
//               0 0 0     roots[0 1 2 3 4 5 6 7 8 9]
//               0 0 0    islands: 1
//            
//    addLand(0, 2):
//                        Index  0 1 2 3 4 5 6 7 8 9
//               1 0 1   visited[1 0 1 0 0 0 0 0 0 0]       
//               0 0 0     roots[0 1 2 3 4 5 6 7 8 9]
//               0 0 0    islands: 2
//    
//    addLand(0, 1):
//                        Index  0 1 2 3 4 5 6 7 8 9
//               1[1]1   visited[1 1 1 0 0 0 0 0 0 0]       
//               0 0 0     roots[0 0 0 3 4 5 6 7 8 9]
//               0 0 0    islands: 1
//    Note that at this position, we are merging islands. for our position at 0, 1, 
//    find 4 of its neighbors, up, down, left, right: 
//            neighbor:(-1,1) this element is out of bound, we discard it
//            neighbor:(1,1), this is a valid neighbor, and it has already been visited
//                            we find neighbor's ancestor and compare with our current node's ancestor
//                            if the two ancestors are different, we union them, islands reduce 1
//            neighbor:(0,0), this is a valid neighbor, and it has already been visited
//                            we find neighbor's ancestor and compare with our current node's ancestor
//                            if the two ancestors are different, we union them, islands reduce 1
//            neighbor:(0,2), this element hasn't been visited yet, we don't count it.
//  Bonus: we can use path compression union find
// Time O(m * n * 4k), k is the number of position additions
    static class UnionFind {

        private static final int VISITED = 1;
        private static final int UNVISITED = 0;
        private final int m;
        private final int n;
        private int[] visit;
        private int[] roots;
        private int islands;

        public UnionFind(int m, int n) {
            this.m = m;
            this.n = n;
            this.visit = new int[m * n];
            this.roots = new int[m * n];

            for (int i = 0; i < m * n; i++) {
                this.visit[i] = UNVISITED;
                this.roots[i] = i;
            }
            this.islands = 0;
        }

        private int getIslands() {
            return this.islands;
        }

        private void addPosition(int x, int y) {
            int node = x * n + y;
            //check if x and y are out of bound?
            if (visit[node] == UNVISITED) {
                int[] dx = {1, -1, 0, 0};
                int[] dy = {0, 0, -1, 1};
                visit[node] = VISITED;
                islands++;

                for (int i = 0; i < 4; i++) {

                    if (0 <= x + dx[i] && x + dx[i] < m && 0 <= y + dy[i] && y + dy[i] < n) {
                        int neighbor = (x + dx[i]) * n + (y + dy[i]);
                        if (visit[neighbor] == VISITED) {
                            int ancestorNode = find(x, y);
                            int ancestorNeighbor = find(x + dx[i], y + dy[i]);
                            //union
                            if (ancestorNode != ancestorNeighbor) {
                                roots[ancestorNode] = ancestorNeighbor;
                                islands--;
                            }
                        }
                    }
                }
            }
        }

        private int find(int x, int y) {
            int node = x * n + y;
            int ancestor = node;
            while (ancestor != roots[ancestor]) {
                ancestor = roots[ancestor];
            }

            //path compression
            int parent = roots[node];
            while (parent != ancestor) {
                roots[node] = ancestor;
                node = parent;
                parent = roots[parent];
            }
            return ancestor;
        }

    }

    static List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList();
        if (m <= 0 || n <= 0 || positions == null || positions.length == 0 || positions[0].length == 0) {
            return res;
        }

        UnionFind uf = new UnionFind(m, n);
        for (int[] position : positions) {
            uf.addPosition(position[0], position[1]);
            res.add(uf.getIslands());
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] ps = {{0, 0},
        {0, 1},
        {1, 2},
        {2, 1}};
        System.out.println(numIslands2(3, 3, ps));
//        int [][] ps = {{7,0}};
//        System.out.println(numIslands2(8,2,ps));
//        int[][] ps = {{0, 1},
//        {1, 2},
//        {2, 1},
//        {1, 0},
//        {0, 2},
//        {0, 0},
//        {1, 1}};
//        System.out.println(numIslands2(3, 3, ps));

    }
}
