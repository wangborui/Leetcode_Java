// Source : https://leetcode.com/problems/number-of-boomerangs/#/description
// Date   : 05/26/2017
/**
 * ********************************************************************************
 * Given n points in the plane that are all pairwise distinct,
 * a "boomerang" is a tuple of points (i, j, k) such that the distance between
 * i and j equals the distance between i and k (the order of the tuple matters).
 *
 * Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).
 *
 * Example:
 * Input:
 * [[0,0],[1,0],[2,0]]
 *
 * Output:
 * 2
 *
 * Explanation:
 * The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 *********************************************************************************
 */
package Leetcode_Java.hash_easy;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class NumberOfBoomerangs {

    public int numberOfBoomerangs(int[][] points) {
        if (points == null || points.length == 0 || points[0].length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap();
        int n = points.length;
        int res = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    int distance = getDistance(points[i], points[j]);
                    map.put(distance, map.getOrDefault(distance, 0) + 1);
                }
            }
            for (int val : map.values()) {
                res += val * (val - 1);
            }
            map.clear();
        }
        return res;

    }

    private int getDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];
        return dx * dx + dy * dy;
    }
}
