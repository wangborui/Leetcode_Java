/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
