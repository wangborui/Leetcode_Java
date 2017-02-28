// Source : https://oj.leetcode.com/problems/merge-intervals/
// Date   : 02/27/2017

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
}
