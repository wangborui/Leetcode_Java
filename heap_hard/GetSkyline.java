// Source : https://leetcode.com/problems/the-skyline-problem/
// Date   : 02/28/2017
/**
 * ********************************************************************************
 *
 * A city's skyline is the outer contour of the silhouette formed by all the buildings
 * in that city when viewed from a distance. Now suppose you are given the locations and
 * height of all the buildings as shown on a cityscape photo (Figure A), write a program
 * to output the skyline formed by these buildings collectively (Figure B).
 *
 *  ^                                        ^
 *  |                                        |
 *  |                                        |
 *  |    +-----+                             |    O-----+
 *  |    |     |                             |    |     |
 *  |    |     |                             |    |     |
 *  |    |  +--+------+                      |    |     O------+
 *  |    |  |         |                      |    |            |
 *  |  +-+--+----+    |   +------+           |  O-+            |   O------+
 *  |  |         |    |   |      |           |  |              |   |      |
 *  |  |         |    |   |    +-+--+        |  |              |   |      O--+
 *  |  |         |    |   |    |    |        |  |              |   |         |
 *  |  |         |    |   |    |    |        |  |              |   |         |
 *  |  |         |    |   |    |    |        |  |              |   |         |
 *  |  |         |    |   |    |    |        |  |              |   |         |
 *  +--+---------+----+---+----+----+--->    +--+--------------O---+---------O--->
 *
 *   https://leetcode.com/static/images/problemset/skyline1.jpg
 *   https://leetcode.com/static/images/problemset/skyline2.jpg
 *
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi],
 * where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively,
 * and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 , and Ri - Li > 0.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * For instance, the dimensions of all buildings in Figure A are recorded as:
 *  [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 *
 * The output is a list of "key points" (red dots in Figure B) in the format of
 * [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline.
 * A key point is the left endpoint of a horizontal line segment.
 *
 * Note that the last key point, where the rightmost building ends, is merely used to mark
 * the termination of the skyline, and always has zero height. Also, the ground in between
 * any two adjacent buildings should be considered part of the skyline contour.
 *
 * For instance, the skyline in Figure B should be represented as:
 *  [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 *
 * Notes:
 *
 *  - The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 *  - The input list is already sorted in ascending order by the left x position Li.
 *  - The output list must be sorted by the x position.
 *  - There must be no consecutive horizontal lines of equal height in the output skyline.
 *    For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable;
 *    the three lines of height 5 should be merged into one in the final output as such:
 *    [...[2 3], [4 5], [12 7], ...]
 *
 * Credits: Special thanks to @stellari for adding this problem,
 *          creating these two awesome images and all test cases.
 *
 *********************************************************************************
 */
package Leetcode_Java.heap_hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 *
 * @author Borui Wang
 */
/*
 * Sweep line with max-heap
 * ------------------------
 * Notice that "key points" are either the left or right edges of the buildings. 
 * 
 * Therefore, we first obtain both the edges of all the N buildings, and store the 2N edges in a sorted array. 
 * When sorting the array, put start edge before end edge if they have the same position
 * Maintain a max-heap of building heights while scanning through the edge array: 
 * 1) If the current edge is a left edge, then add the height of its associated building to the max-heap; 
 * 2) If the edge is a right one, remove the associated height from the heap. 
 * 
 * Then we take the top value of the heap (yi) as the maximum height at the current edge position (xi). 
 * Now (xi, yi) is a potential key point. 
 * 
 * If yi is the same as the height of the last key point in the result list, it means that this key point 
 * is not a REAL key point, but rather a horizontal continuation of the last point, so it should be discarded; 
 * 
 * otherwise, we add (xi,yi) to the result list because it is a real key point. 
 * 
 * Repeat this process until all the edges are checked.
 * 
 * It takes O(NlogN) time to sort the edge array. For each of the 2N edges, 
 * it takes O(1) time to query the maximum height but O(logN) time to add 
 * or remove elements. Overall, this solution takes O(NlogN) time.
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

    /**
     * ********************************************************************************
     * Use TreeMap to reduce remove time from O(n) to O(log n),
     * We use key to represent height, and value to represent how many times height appears
     * ********************************************************************************
     */
    static List<int[]> getSkyline2(int[][] buildings) {
        List<int[]> res = new ArrayList();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return res;
        }

        List<Point> points = new ArrayList();
        //map<height, times>
        TreeMap<Integer, Integer> map = new TreeMap(Collections.reverseOrder());

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
        map.put(0, 1);
        //start scanning points
        for (Point point : points) {
            Integer times = map.get(point.height);
            if (point.isStart == 0) {
                times = times == null ? 1 : ++times;
                map.put(point.height, times);
            } else if (times == 1) {
                map.remove(point.height);
            } else {
                map.put(point.height, --times);
            }

            int curH = map.firstKey();
            if (prevH != curH) {
                res.add(new int[]{point.position, curH});
                prevH = curH;
            }
        }
        return res;
    }

    /**
     * ********************************************************************************
     * Use building start and end pair, start has negative height, end has
     * positive height
     * ********************************************************************************
     */
    static List<int[]> getSkyline3(int[][] buildings) {
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

    public static void main(String[] args) {
        //output [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
        int[][] in1 = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        //output [[0,3],[5,0]]
        int[][] in2 = {{0, 2, 3}, {2, 5, 3}};
        printRes(getSkyline2(in1));
    }
}
