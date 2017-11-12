//Date: 11/10/2017
//Source: https://leetcode.com/problems/queue-reconstruction-by-height/description/
/**
 * ******************************************************************************
 * Suppose you have a random list of people standing in a queue.
 * Each person is described by a pair of integers (h, k), where h is the height of the person
 * and k is the number of people in front of this person who have a height greater than or equal to h.
 * Write an algorithm to reconstruct the queue.
 *
 * Note:
 * The number of people is less than 1,100.
 *
 *
 * Example
 *
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 ********************************************************************************
 */
package Leetcode_Java.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Borui Wang
 */
public class ReconstructQueue {

    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) {
            return new int[0][0];
        }

        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                } else {
                    return b[0] - a[0];
                }
            }
        });

        List<int[]> list = new ArrayList();
        Set<String> s = new HashSet();
        for (String bbb : s) {

        }
        for (int[] p : people) {
            list.add(p[1], p);
        }
        int[][] res = new int[people.length][people[0].length];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
