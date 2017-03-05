//Source : https://leetcode.com/problems/meeting-rooms
//Date   : 03/05/2017
/**
 * ********************************************************************************
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],
 * [s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 *
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
 *********************************************************************************
 */
package Leetcode_Java.arrays_easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Borui Wang
 */
public class MeetingRoom {

    /**
     * Definition for an interval. public class Interval { int start; int end;
     * Interval() { start = 0; end = 0; } Interval(int s, int e) { start = s;
     * end = e; } }
     */
    public class Solution {

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

        public boolean canAttendMeetings(Interval[] intervals) {
            if (intervals == null || intervals.length == 0) {
                return true;
            }

            Arrays.sort(intervals, new Comparator<Interval>() {
                public int compare(Interval a, Interval b) {
                    return a.start - b.start;
                }
            });

            for (int i = 1; i < intervals.length; i++) {
                Interval cur = intervals[i];
                Interval pre = intervals[i - 1];
                if (cur.start < pre.end) {
                    return false;
                }
            }
            return true;
        }
    }
}
