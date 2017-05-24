//Source : https://leetcode.com/problems/longest-uncommon-subsequence-i/#/description
//Date   : 05/24/2017
/**
 * ******************************************************************************
 * Given an integer array, find a subarray with sum closest to zero.
 * Return the indexes of the first number and last number.
 *
 * Have you met this question in a real interview? Yes
 * Example
 * Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] or [0, 4].
 *
 * Challenge
 * O(nlogn) time
 *******************************************************************************
 */
package Leetcode_Java.string_easy;

/**
 *
 * @author Borui Wang
 */
public class FindLUSlength {

    public int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }
}
