//Source : http://www.lintcode.com/en/problem/number-of-airplanes-in-the-sky/
//Date   : 02/28/2017

/**
 * *****************************************************************************
 * Given an interval list which are flying and landing time of the flight. 
 * How many airplanes are on the sky at most?
 *
 * Notice
 *
 * If landing and flying happens at the same time, we consider landing should happen at first.
 *
 * Have you met this question in a real interview? Yes
 * Example
 * For interval list
 *
 * [
 * [1,10],
 * [2,3],
 * [5,8],
 * [4,7]
 * ]
 * Return 3
 ******************************************************************************
 */
package Leetcode_Java.arrays_medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
//idea is:
//
//        1.) take out each interval and convert them to wrapper class(Status) with time and inair, -1 is landing, 1 is taking off
//        2.) sort wrapper object based on time
//        3.) iterate through each wrapper object and add its inair value, find max of such value
//
//for example:
//                            plane 2[3----4]
//                   plane 1[1--------3]
//                time 0----1----2----3----4
//Status(time, inair) after sorted based on time: 
//
//        (1, 1)       plane 1 taking off.
//        (3,-1)       plane 1 landing(higher precedence than taking off)
//        (3, 1)       plane 2 taking off
//        (4,-1)       plane 2 landing
//        count = 0;
//        max planes in air = 1, max = max(max, count += status(0 : n))
                                         
public class CountOfAirplanes {

    static class Interval {

        int start, end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * @param intervals: An interval array
     * @return: Count of airplanes are in the sky.
     */
    static class Status implements Comparable<Status> {

        private final int time;
        private final int inAir;

        public Status(int t, int i) {
            this.time = t;
            this.inAir = i;
        }

        @Override
        public int compareTo(Status that) {
            if (this.time == that.time) {
                //if 2 status have same time, then landing should(-1) take precedence over taking off(1)
                return this.inAir - that.inAir;
            } else {
                return this.time - that.time;
            }
        }
    }
    private static final int TAKING_OFF = 1;
    private static final int LANDING = -1;

    static int countOfAirplanes(List<Interval> airplanes) {
        if (airplanes == null || airplanes.size() == 0) {
            return 0;
        }
        //convert all interval numbers into status
        List<Status> statuses = new ArrayList();
        int max = 0;
        int count = 0;
        for (Interval i : airplanes) {
            statuses.add(new Status(i.start, TAKING_OFF));
            statuses.add(new Status(i.end, LANDING));
        }

        //sort all status based on time
        Collections.sort(statuses);

        for (Status s : statuses) {
            count += s.inAir;
            max = Math.max(count, max);
        }
        return max;
    }

    public static void main(String[] args) {
        List<Interval> in = new ArrayList();
        in.add(new Interval(1, 2));
        in.add(new Interval(2, 4));
//        for(int i = 0; i < 3; i++) {
//            in.add(new Interval(1,4));
//        }
        System.out.println(countOfAirplanes(in));
    }
}
