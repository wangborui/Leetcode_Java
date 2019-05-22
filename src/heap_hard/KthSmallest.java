//Source : https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/?tab=Description
//Date   : 02/27/2017
/**
 * ********************************************************************************
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order,
 * find the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 *
 * Example:
 *
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 *
 * return 13.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ n2.
 *********************************************************************************
 */
package Leetcode_Java.heap_hard;

import java.util.PriorityQueue;

/**
 *
 * @author Borui Wang
 */
public class KthSmallest {

    static class Tuple implements Comparable<Tuple> {

        int x;
        int y;
        int val;

        public Tuple(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        public int compareTo(Tuple that) {
            return this.val - that.val;
        }

    }

    static int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        //mark visited node
        boolean[][] visited = new boolean[m][n];
        //we only need to check right and bottom element
        int[] dx = {0, 1};
        int[] dy = {1, 0};

        PriorityQueue<Tuple> pq = new PriorityQueue();
        //add the smallest item into pq
        pq.add(new Tuple(0, 0, matrix[0][0]));
        visited[0][0] = true;

        //find the k - 1 smallest element
        for (int i = 0; i < k - 1; i++) {
            Tuple temp = pq.poll();
            //determine if we need to add its right and bottom element
            for (int j = 0; j < dx.length; j++) {
                int newX = temp.x + dx[j];
                int newY = temp.y + dy[j];
                if (0 <= newX && newX < m && 0 <= newY && newY < n && !visited[newX][newY]) {
                    pq.add(new Tuple(newX, newY, matrix[newX][newY]));
                    visited[newX][newY] = true;
                }
            }
        }

        return pq.poll().val;
    }

    public static void main(String[] args) {
        //[[1,5,9],[10,11,13],[12,13,15]]
        int[][] m = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        System.out.println(kthSmallest(m, 8));
    }
}
