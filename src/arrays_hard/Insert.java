// Source : https://oj.leetcode.com/problems/insert-interval/
// Date   : 03/05/2017
// Update : 06/23/2019 New Solution Using TreeMap Changing Time from O(n) to O(n log n) space O(n)
//Reference: https://leetcode.com/problems/meeting-rooms-ii/discuss/203658/HashMapTreeMap-resolves-Scheduling-Problem
/**
 * ********************************************************************************
 *
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 *
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 *
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 *
 *
 *********************************************************************************
 */
package Leetcode_Java.arrays_hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Borui Wang
 */
public class Insert {

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
//    idea is:
//    
//    because all intervals are sorted based on start of each interval.
//            
//    remove all existing intervals whose end is less than new interval start
//            
//    After removal, merge intervals whose start is less than new interval's end
//
//    then add the rest of the intervals if there are any
//    Time O(n) Space O(n)
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || newInterval == null) {
            return intervals;
        }
        List<Interval> res = new ArrayList();

        int i = 0;
        int n = intervals.size();
        //remove unmergable intervals
        while (i < n && intervals.get(i).end < newInterval.start) {
            res.add(intervals.get(i++));
        }

        //merge intervals
        while (i < n && intervals.get(i).start <= newInterval.end) {
            Interval old = intervals.get(i++);
            newInterval = new Interval(Math.min(old.start, newInterval.start), Math.max(old.end, newInterval.end));
        }
        //add new interval
        res.add(newInterval);

        //add the rest of intervals
        while (i < n) {
            res.add(intervals.get(i++));
        }
        return res;
    }

    //TreeMap Solution
    public int[][] insertTreeMapSolution(int[][] intervals, int[] newInterval) {
        Map<Integer, Integer> map = new TreeMap<>();
        //Adding new interval
        map.put(newInterval[0], map.getOrDefault(newInterval[0], 0) + 1);
        map.put(newInterval[1], map.getOrDefault(newInterval[1], 0) - 1);

        for (int[] itl : intervals) {
            map.put(itl[0], map.getOrDefault(itl[0], 0) + 1);
            map.put(itl[1], map.getOrDefault(itl[1], 0) - 1);
        }
        List<int[]> res = new ArrayList();
        int start = 0; int count = 0;
        for(Map.Entry<Integer,Integer> e : map.entrySet()) {
            if(count == 0) {
                start = e.getKey();
            }

            count += e.getValue();
            if(count == 0) {
                res.add(new int[]{start, e.getKey()});
            }
        }
        int[][] temp = new int[res.size()][2];
        for(int i = 0; i < res.size(); i++) {
            temp[i] = res.get(i);
        }
        return temp;
    }
}
