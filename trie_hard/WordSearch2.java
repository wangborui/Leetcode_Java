/*
Source: https://leetcode.com/problems/word-search-ii/
Date: 01/04/2017
********************************************************************************
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.

click to show hint.

You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. 
What kind of data structure could answer such query efficiently? Does a hash table work? 
Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.
********************************************************************************
 */
package Leetcode_Java.trie_hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class WordSearch2 {
     private class TrieNode {
        private boolean isString;
        private String word;
        private Map<Character, TrieNode> subtree;
        public TrieNode() {
            this.isString = false;
            this.subtree = new HashMap();
            this.word = "";
        }
    }
    private class Trie {
        private TrieNode root;
        public Trie(TrieNode node) {
            this.root = node;
        }
        private void insert(String s) {
            TrieNode current = root;
            for(int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if(!current.subtree.containsKey(c)) {
                    current.subtree.put(c, new TrieNode());
                }
                current = current.subtree.get(c);
            }
            
            //mark word in trie tree
            current.isString = true;
            current.word = s;
        }
    }
    
    //pair dx[i] and dy[i] means to go either 
    //up(0, 1) x does not move, y plus one
    //down, left, and right share the same rational
    int [] dx = new int[] {0, 0,-1, 1};
    int [] dy = new int[] {1,-1, 0, 0};
    private void dfsSearch(int row, int col, List<String> found, TrieNode root, char[][] board) {
        /**
         * Do not search if.
         * 1. root is null, or
         * 2. either row or col is out of bound, or
         * 3. current position has been visited
         */
        if(root.isString) {
            if(!found.contains(root.word)) {
                found.add(root.word);
            }
        }
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] == 0) {
                return;
        }
         
        
        if(root.subtree.containsKey(board[row][col])) {
           //search 4 directions from up, down, left, and right
            for(int i = 0; i < 4; i++) {
                int x = row + dx[i];
                int y = col + dy[i];
                char current = board[row][col];
                board[row][col] = 0;
                dfsSearch(x, y, found, root.subtree.get(current), board);
                board[row][col] = current;
            } 
        }
         
    }
    public List<String> findWords(char[][] board, String[] words) {
        List<String> found = new ArrayList();
        Trie tree = new Trie(new TrieNode());
        
        //build trie from words
        for(String word : words) {
            tree.insert(word);
        }
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                //dfs search up, down, left, right from position i, j
                dfsSearch(i, j, found, tree.root, board);
            }
        }
        return found;
    }
    public static void main(String[] args) {
        WordSearch2 ws = new WordSearch2();
        char[][] board = new char[][] {{'a'}};
        String[] words = new String[] {"a"};
        System.out.println(ws.findWords(board,words));
    }
}
