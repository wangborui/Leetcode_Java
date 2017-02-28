/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.heap_hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class GetSkyline {

    static class Point {

        int height;
        int isStart;
        int position;

        public Point(int height, int isStart, int position) {
            this.height = height;
            this.isStart = isStart;
            this.position = position;
        }
    }
    private static final int START = 0;

    static List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return res;
        }

        List<Point> points = new ArrayList();
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());

        //scan buildings and put points into list
        for (int i = 0; i < buildings.length; i++) {
            int[] building = buildings[i];
            Point start = new Point(building[2], 0, building[0]);
            Point end = new Point(building[2], 1, building[1]);
            points.add(start);
            points.add(end);
        }

        //sort points based on position, if positions are the same, put start of building before end of building
        Collections.sort(points, (a, b) -> {
            if (a.position == b.position) {
                return a.isStart - b.isStart;
            } else {
                return a.position - b.position;
            }
        });

        int prevH = 0;
        pq.add(0);
        //start scanning points
        for (Point point : points) {
            if (point.isStart == 0) {
                pq.add(point.height);
            } else {
                pq.remove(point.height);
            }

            int curH = pq.peek();
            if (prevH != curH) {
                res.add(new int[]{point.position, curH});
                prevH = curH;
            }
        }
        return res;
    }

    static void printPoints(List<Point> l) {
        for (Point p : l) {
            System.out.println(" height: " + p.height + " isStart: " + p.isStart + " pos: " + p.position);
        }
    }

    static void printRes(List<int[]> l) {
        for (int[] p : l) {
            System.out.print("[" + p[0] + "," + p[1] + "]" + " ");
        }
    }

    static List<int[]> getSkyline2(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for (int[] b : buildings) {
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }
        Collections.sort(height, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);
        int prev = 0;
        for (int[] h : height) {
            if (h[1] < 0) {
                pq.offer(-h[1]);
            } else {
                pq.remove(h[1]);
            }
            int cur = pq.peek();
            if (prev != cur) {
                result.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //output [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
        int[][] in = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        //output [[0,3],[5,0]]
        int[][] in2 = {{0, 2, 3}, {2, 5, 3}};
        printRes(getSkyline(in2));
    }
}
