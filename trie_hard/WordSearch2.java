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

********************************************************************************

Problem Anaylsis:

You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
yes, if we search a position out of bound of the board, or if we search a visited position

If the current candidate does not exist in all words' prefix, you could stop backtracking immediately?
no, because there may be more nodes down the trie tree path, we need to keep searching

What kind of data structure could answer such query efficiently? Does a hash table work? 
because we need to find a data structure with O(1) search time, it could be a hash table or a trie
hash table does not work in this case because we need to search one character at a time, hashtable does not allow us to do so
Trie tree saves space, because in hash table, we need to store every possible substring of every word in order to make sure it exists in the dictionary. too much space
for example, for word "pea", we need to store in hash table p : "pea", pe : "pea" pea : "pea"

Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.
 */
package Leetcode_Java.trie_hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Borui Wang
 * Problem overview:
 *      1. build a trie tree with every word in the dictionary
 *      2. starting at each position of the board, use bfs search to find matching words in trie tree
 */
//首先把字典里的所有词语都用trie树保存起来，我们要建立2个单独class，一个叫trie node，另外一个叫trie tree
//再遍历2D的矩阵每一个节点，当访问节点的时候，用深度优搜索查找当前节点的上，下，左，右，四个方向的节点，并查找trie 树的子树看看有没有找到字典里的单词

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
    private void bfsSearch(int row, int col, List<String> found, TrieNode root, char[][] board) {
        /**
         * 1.Word Check: Check if current root is word before check loop termination conditions due to the structure of trie
         * 2.Loop Termination Conditions: do not search.
         *      a. root is null, or
         *      b. either row or col is out of bound, or
         *      c. current position has been visited
         * 
         * Note: 1. must be before 2. otherwise, as the following example: 
         * 
         * if we have trie as the following, and we search for word 'a'
         * board = [['a']]                  [root]
         *                                 /       \
         *                          isString['a']  ['b']
         * if we have loop termination condition before word check:
         *      Search Steps:
         *      1. walker = [root], row = 0, col = 0, board[0][0] is valid
         *         does not trigger loop termination conditions, and is not a word, so we keep going
         *      2. walker = ['a'], row = 1, col = 0, board[1][0] is not valid
         *          triggers loop termination condition, but it is a word, and we skipped
         */
        if(root.isString && !found.contains(root.word)) {
            //we do not return after finding a word, because there may be lots of words on the same path
            found.add(root.word);
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
                //marks the board as visited
                board[row][col] = 0;
                bfsSearch(x, y, found, root.subtree.get(current), board);
                //unmark the board back to its original writing
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
                //bfs search up, down, left, right from position i, j
                bfsSearch(i, j, found, tree.root, board);
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
