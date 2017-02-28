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

/**
 *
 * @author Borui Wang
 */
public class GetSkyline {

    static class Point {

        int height;
        boolean isStart;
        int position;

        public Point(int height, boolean isStart, int position) {
            this.height = height;
            this.isStart = isStart;
            this.position = position;
        }
    }

    static List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return res;
        }

        List<Point> points = new ArrayList();
        PriorityQueue<Integer> pq = new PriorityQueue(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        //scan buildings and put points into list
        for (int i = 0; i < buildings.length; i++) {
            int[] building = buildings[i];
            Point start = new Point(building[2], true, building[0]);
            Point end = new Point(building[2], false, building[1]);
            points.add(start);
            points.add(end);
        }

        //sort points based on position
        Collections.sort(points, new Comparator<Point>() {
            public int compare(Point a, Point b) {
                return a.position - b.position;
            }
        });
        printPoints(points);
        int prevH = 0;
        int i = 0;
        //start scanning points
        while (i < points.size()) {
            Point point = points.get(i);
            //there are multiple points at the same position, keep processing until the last identical point
            if (i < points.size() - 1 && points.get(i + 1).position == point.position) {
                while (i < points.size() - 1 && points.get(i + 1).position == point.position) {
                    point = points.get(i++);
                    if (point.isStart) {
                        pq.add(point.height);
                    } else {
                        pq.remove(point.height);
                    }
                }
            } 
            //no points at the same location
            point = points.get(i);
            if (point.isStart) {
                pq.add(point.height);
            } else {
              pq.remove(point.height);
            }
            

            if (pq.isEmpty()) {
                res.add(new int[]{point.position, 0});
                prevH = 0;
            } else {
                if (prevH != pq.peek()) {
                    res.add(new int[]{point.position, pq.peek()});
                    prevH = pq.peek();
                 }
            }
            i++;
        }
        return res;
    }
    static void printPoints(List<Point> l) {
        for(Point p : l) {
            System.out.println(" height: " + p.height + " isStart: " + p.isStart + " pos: " + p.position);
        }
    }
    static void printRes(List<int[]> l) {
        for(int[] p : l) {
            System.out.print("[" + p[0] + "," + p[1] + "]" + " ");
        }
    }
    public static void main(String[] args) {
        //output [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
        int[][] in = {{2,9,10},{3, 7 ,15},{5, 12, 12},{15, 20, 10},{19, 24 ,8}};
        //output [[0,3],[5,0]]
        int[][] in2 = {{0,2,3},{2,5,3}};
        printRes(getSkyline(in2));
    }
}
