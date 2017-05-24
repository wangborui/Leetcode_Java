//Source : https://leetcode.com/problems/valid-word-square/#/description
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
package Leetcode_Java.trie_hard;

import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class ValidWordSquare {

    public boolean validWordSquare(List<String> words) {
        if (words == null || words.size() == 0) {
            return true;
        }
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (!isValid(words, j, i, c)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(List<String> words, int i, int j, char c) {
        if (i < words.size()) {
            String word = words.get(i);
            if (j < word.length()) {
                return word.charAt(j) == c;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
