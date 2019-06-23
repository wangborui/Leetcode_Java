// Source : https://oj.leetcode.com/problems/merge-intervals/
// Date   : 02/27/2017
// Update : 06/23/2019 using TreeMap

/********************************************************************************** 
* 
* Given a collection of intervals, merge all overlapping intervals.
* 
* For example,
* Given [1,3],[2,6],[8,10],[15,18],
* return [1,6],[8,10],[15,18].
* 
*               
**********************************************************************************/

package Leetcode_Java.arrays_medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Borui Wang
 */
public class Merge {

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

    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList();
        if (intervals == null || intervals.size() == 0) {
            return res;
        }
        //sort the intervals based on start 
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        int prevStart = intervals.get(0).start;
        int prevEnd = intervals.get(0).end;

        for (Interval i : intervals) {
            //if 2 intervals overlap, Merge them together
            if (i.start <= prevEnd) {
                prevEnd = Math.max(prevEnd, i.end);
            } else {
                res.add(new Interval(prevStart, prevEnd));
                prevStart = i.start;
                prevEnd = i.end;
            }
        }
        //add last interval
        res.add(new Interval(prevStart, prevEnd));
        return res;
    }

    //TreeMap Solution
    public int[][] mergeTreeMap(int[][] intervals) {
        Map<Integer, Integer> map = new TreeMap<>();

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
