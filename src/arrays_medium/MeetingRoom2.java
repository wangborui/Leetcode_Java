//Source : https://leetcode.com/problems/meeting-rooms-ii/?tab=Description
//Date   : 03/05/2017
/**
 * ********************************************************************************
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
 * (si < ei), find the minimum number of conference rooms required.
 *
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 *********************************************************************************
 */
package Leetcode_Java.arrays_medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class MeetingRoom2 {

    public class Interval {

        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    private class Point implements Comparable<Point> {

        int time;
        int status;

        public Point(int t, int s) {
            this.time = t;
            this.status = s;
        }

        public int compareTo(Point that) {
            if (this.time == that.time) {
                return this.status - that.status;
            } else {
                return this.time - that.time;
            }
        }
    }
    private static final int START = 1;
    private static final int END = -1;

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        List<Point> points = new ArrayList();
        //put intervals into points
        for (Interval i : intervals) {
            points.add(new Point(i.start, START));
            points.add(new Point(i.end, END));
        }

        Collections.sort(points);

        int rooms = 0;
        int minRooms = 0;
        for (Point p : points) {
            rooms += p.status;
            minRooms = Math.max(minRooms, rooms);
        }
        return minRooms;
    }
}
