/*
Source: https://leetcode.com/problems/triangle/
********************************************************************************
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
********************************************************************************

Solutions:
n is the number of rows
1. recursive traversal Time O(2^n) space(1) OJ time limit exceeds
2. recursive divide and conquer Time O(2^n) space(1) OJ time limit exceeds
 */
package Leetcode_Java.dynamic_programming_medium;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class Triangle {

    /*
********************************************************************************
1. recursive traversal Time O(2^n) space(1) OJ time limit exceeds
[
     [2],           l,r             2
    [3,4],       l,r  l,r           4
   [6,5,7],    l,r  l,r l,r         8(node in the middle is chosen twice by top left.right and top right.left
  [4,1,8,3]  l,r  l,r  l,r  l,r     16
]
********************************************************************************
     */
    private int best;

    public int minimumTotalTraversal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0 || triangle.get(0) == null || triangle.get(0).size() == 0) {
            return -1;
        }
        best = Integer.MAX_VALUE;
        //start at top level going down
        dfsHelperTraversal(0, 0, 0, triangle);
        return best;

    }

    private void dfsHelperTraversal(int row, int col, int sum, List<List<Integer>> triangle) {
        if (row == triangle.size()) {
            if (sum < best) {
                best = sum;
            }
            return;
        }
        dfsHelperTraversal(row + 1, col, sum + triangle.get(row).get(col), triangle);
        dfsHelperTraversal(row + 1, col + 1, sum + triangle.get(row).get(col), triangle);
    }

    /*
********************************************************************************
2. recursive divide and conquer Time O(2^n) space(1) OJ time limit exceeds 
    similar analysis to method 1
********************************************************************************
     */
    public int minimumTotalDivideNConquer(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0 || triangle.get(0) == null || triangle.get(0).size() == 0) {
            return -1;
        }
        //going from top to bottom
        return minimumTotalDivideNConquerHelper(0,0,triangle);
    }
    private int minimumTotalDivideNConquerHelper(int row, int col, List<List<Integer>> triangle) {
        if(row == triangle.size()) {
            return 0;
        }
        int left = minimumTotalDivideNConquerHelper(row + 1, col, triangle);
        int right = minimumTotalDivideNConquerHelper(row + 1, col + 1, triangle);
        
        return Math.min(left, right) + triangle.get(row).get(col);
    }
    /*
********************************************************************************
    n is the number of levels, there are n^2 number of nodes, and we visit node
3. Bottom up memoization Time(n^2) unoptimized space O(n^2)
********************************************************************************
     */
    
static int minimumTotalBottomUp(List<List<Integer>> triangle) {
    if (triangle == null || triangle.size() == 0 || triangle.get(0) == null || triangle.get(0).size() == 0) {
            return -1;
        }
    int n = triangle.size();
    //hash[i][j] means the shortest path from bottom of triangle to node i j
    int [][] hash = new int[n][n];
    //initialize hash to oo for each level
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n;j++) {
            hash[i][j] = Integer.MAX_VALUE;
        }
    }
    
    //populate last level to triangle last level
    for(int i = 0; i < n; i++) {
        hash[n - 1][i] = triangle.get(n - 1).get(i);
    }
    //start at second til bottom level
    for(int i = n - 2; i >= 0; i--) {
        for(int j = 0; j <= i; j++) {
            int left = hash[i + 1][j];
            int right = hash[i + 1][j + 1];
            int cur = triangle.get(i).get(j);
            hash[i][j] = Math.min(left, right) + cur;
        }
    }
    //return top of triangle
    return hash[0][0];
 }
 /*
********************************************************************************
********************************************************************************
     */

 /*
********************************************************************************
********************************************************************************
     */
     public static void main(String [] args) {
         List<Integer> a = new ArrayList();
         List<Integer> b = new ArrayList();
         List<List<Integer>> c = new ArrayList();
         a.add(-10);
         b.add(3);
         b.add(7);
         c.add(a);
         c.add(b);
         minimumTotalBottomUp(c);
     }
}
