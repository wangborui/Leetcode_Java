/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.graph_medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * @author Borui Wang
 */
public class TheMaze2 {
    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        //each cell contains the shortest distance from start position, if a cell is not reachable its 00
        int[][] dist = new int[maze.length][maze[0].length];
        //set each cell to be infinitely large
        for(int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[start[0]][start[1]] = 0;
        helper(maze, start, dist);
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }
    
    private static void helper(int[][] maze, int[] start, int[][] dist) {
        //up, down, left, right
        int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
        //stores{x,y,d} coordinates x, y, and its distance to start
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        q.add(new int[]{start[0],start[1],0});
        //go 4 directions from start
        while(!q.isEmpty()) {
            int[] cell = q.poll();
            //if other calculated path to current cell has shorter distance than another path
            System.out.println(dist[cell[0]][cell[1]] + " : " + cell[2]);
            if(dist[cell[0]][cell[1]] < cell[2]) {
                continue;
            }
            for(int[] move : dir){
                int steps = 0;
                int dx = cell[0] + move[0];
                int dy = cell[1] + move[1];
                //a valid next move
                while(dx >= 0 && dx < maze.length && dy >= 0 && dy < maze[0].length && maze[dx][dy] == 0) {
                    dx += move[0];
                    dy += move[1];
                    steps++;
                }

                //at a stopping point, and found a shorter distance
                if(dist[dx - move[0]][dy - move[1]] > dist[cell[0]][cell[1]] + steps) {
                    dist[dx - move[0]][dy - move[1]] = dist[cell[0]][cell[1]] + steps;
                    //if visiting visited path, then longer distance wont be added
                    q.add(new int[]{dx - move[0], dy - move[1], dist[dx - move[0]][dy - move[1]]});
                }
            }   
        }
    }
    public static void main(String[] args) {
        int[][] m = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        int[] s = {0,4};
        int[] e = {4,4};
        System.out.println(shortestDistance(m,s,e));
    }
}
