// Source : https://leetcode.com/problems/trapping-rain-water-ii/
// Date   : 02/08/2017

/**
 * ********************************************************************************
 *
  Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, 
  compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.
 *********************************************************************************
 */
package Leetcode_Java.two_pointers_hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Borui Wang
 */
//Analysis:
//    1.)This question is similar to trapping water 1, where we start at the boundary of the array, choose lower height of 2 boundary, move inward
//       Because water can only be "trapped" by the lower bound.
//    2.)We use the same idea here, start at the boundary of matrix, choose the lowest point on boundary, move inward
//    3.)After visited a block, we mark it visited and not visit again, essentially making visited blocks into a new wall

//Process:
//        We create a visited boolean array to mark which block has been visited
//        We create a priority queue to initially hold all blocks on the perimeter of the matrix 
//        We poll min height element from priority queue, start flooding from this block
//        This block has 4 neighbors, a valid neighbor is in bound of matrix, hasnt been visited before.
//        We choose a valid neighbor, if neighbor height is less than current block height, totalWater trapped += curHeight - neighborHeight, why?  
//        Similar to trapping rain water 2, starting at the lowest block, we can find another block lower than lowest block to flood water
//        We then add neighbor to pq. If neighbor has height lower than curHeight, we change neighbor's height to curHeight and add it to pq, mark visited
//        Otherwise, we use neighbor's own height to add to pq. repeat the process until no more blocks in pq
//        for example, we have 2D arrays, we first put perimeter blocks into heap, sorted by their height
//
//    0 1 2 3 4 5  n
// 0 [1,4,3,1,3,2],    pq[1(0,0),1(0,3),1(2,5),2(0,5),3(0,4) 
// 1 [3,2,1,3,2,4],       ,2(2,0),2(2,3),4(0,1),3(2,1),3(2,2),4(1,5),3(2,4),3(1,0)]
// 2 [2,3,3,2,3,1]
// m
//
//    0 1 2 3 4 5  n
// 0 [X,X,X,X,X,X],    pq[1(0,0),1(0,3),1(2,5),2(0,5),3(0,4) 
// 1 [X,2,1,3,2,X],       ,2(2,0),2(2,3),4(0,1),3(2,1),3(2,2),4(1,5),3(2,4),3(1,0)]
// 2 [X,X,X,X,X,X]
// m
//
//poll min = 1(0,0), its neighbors are all visited, do nothing
//    0 1 2 3 4 5  n
// 0 [X,X,X,X,X,X],    pq[1(0,3),1(2,5),2(0,5),3(0,4) 
// 1 [X,2,1,3,2,X],       ,2(2,0),2(2,3),4(0,1),3(2,1),3(2,2),4(1,5),3(2,4),3(1,0)]
// 2 [X,X,X,X,X,X]
// m
//
//poll min = 1(0,3), visit its valid neighbor (1,3), neighbor has a height of 3, higher than current, so we can not add water, just put neighbor into pq and mark visited
//    0 1 2 3 4 5  n
// 0 [X,X,X,X,X,X],    pq[1(2,5),2(0,5),3(0,4) 
// 1 [X,2,1,X,2,X],       ,2(2,0),2(2,3),4(0,1),3(2,1),3(2,2),4(1,5),3(2,4),3(1,0),3(1,3)]
// 2 [X,X,X,X,X,X]
// m
//
//poll min = 1(2,5), its neighbors are all visited, do nothing
//    0 1 2 3 4 5  n
// 0 [X,X,X,X,X,X],    pq[2(0,5),3(0,4) 
// 1 [X,2,1,X,2,X],       ,2(2,0),2(2,3),4(0,1),3(2,1),3(2,2),4(1,5),3(2,4),3(1,0),3(1,3)]
// 2 [X,X,X,X,X,X]
// m
//
//poll min = 2(0,5), its neighbors are all visited, do nothing
//    0 1 2 3 4 5  n
// 0 [X,X,X,X,X,X],    pq[,2(2,0),2(2,3),3(0,4) 
// 1 [X,2,1,X,2,X],        ,4(0,1),3(2,1),3(2,2),4(1,5),3(2,4),3(1,0),3(1,3)]
// 2 [X,X,X,X,X,X]
// m
//
//poll min = 2(2,0), its neighbors are all visited, do nothing
//    0 1 2 3 4 5  n
// 0 [X,X,X,X,X,X],    pq[2(2,3),3(0,4) 
// 1 [X,2,1,X,2,X],        ,4(0,1),3(2,1),3(2,2),4(1,5),3(2,4),3(1,0),3(1,3)]
// 2 [X,X,X,X,X,X]
// m
//
//poll min = 2(2,3), its neighbors are all visited, do nothing
//    0 1 2 3 4 5  n
// 0 [X,X,X,X,X,X],    pq[3(0,4) 
// 1 [X,2,1,X,2,X],        ,4(0,1),3(2,1),3(2,2),4(1,5),3(2,4),3(1,0),3(1,3)]
// 2 [X,X,X,X,X,X]
// m

//eventually we would have visited all m * n blocks and fills water in the wholes
//Time O(m * n log n), we visit all m * n blocks, there can be at most 4n(4 sides) blocks in the pq, therefore, we have pq time log n
//Space O(m * n)
public class TrapWater2 {
    private final int [] dx = {-1, 1, 0, 0};
    private final int [] dy = {0, 0, -1, 1};
    private class Block {
        int height;
        int row;
        int col;
        public Block(int h, int r, int c) {
            this.height = h;
            this.row = r;
            this.col = c;
        }
    }
    public int trapRainWater(int[][] heightMap) {
        if(heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        int water = 0;
        
        PriorityQueue<Block> pq = new PriorityQueue<Block>(new Comparator<Block>(){
            public int compare(Block a, Block b) {
                return a.height - b.height;
            }
        });
        boolean [][] visited = new boolean[m][n];
        
        //put surrouding blocks into pq and mark them visited
        for(int i = 0; i < m; i++) {
            Block left = new Block(heightMap[i][0], i, 0);
            Block right = new Block(heightMap[i][n - 1], i, n - 1);
            
            visited[i][0] = true;
            visited[i][n - 1] = true;
            
            pq.add(left);
            pq.add(right);
        }
        
        for(int j = 0; j < n; j++) {
            Block top = new Block(heightMap[0][j], 0, j);
            Block bot = new Block(heightMap[m - 1][j], m - 1, j);
            
            visited[0][j] = true;
            visited[m - 1][j] = true;
            
            pq.add(top);
            pq.add(bot);
        }
        
        //start flooding water at block with lowest height
        while(!pq.isEmpty()) {
            Block lowest = pq.poll();
            
            //visit neighbors of lowest block
            for(int i = 0; i < 4; i++) {
                int height = lowest.height;
                int row = lowest.row;
                int col = lowest.col;
                row += dx[i];
                col += dy[i];
                
                if(0 <= row && row < m && 0 <= col && col < n && !visited[row][col]) {
                    int neighborHeight = heightMap[row][col];
                    if(neighborHeight < height) {
                        water += height - neighborHeight;
                        neighborHeight = height;
                    }
                    visited[row][col] = true;
                    pq.add(new Block(neighborHeight, row, col));
                }
            }
        }
        
        return water;
    }
}
