/*
Source: https://leetcode.com/problems/word-ladder-ii/
Date  : 01/06/2017
********************************************************************************
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the word list
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.
********************************************************************************
 */
package Leetcode_Java.graph_medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

// For example:
//
//     start = "hit"
//     end = "cog"
//     dict = ["hot","dot","dog","lot","log","dit","hig", "dig"]
//
//                      +-----+
//        +-------------+ hit +--------------+
//        |             +--+--+              |
//        |                |                 |
//     +--v--+          +--v--+           +--v--+
//     | dit |    +-----+ hot +---+       | hig |
//     +--+--+    |     +-----+   |       +--+--+
//        |       |               |          |
//        |    +--v--+         +--v--+    +--v--+
//        +----> dot |         | lot |    | dig |
//             +--+--+         +--+--+    +--+--+
//                |               |          |
//             +--v--+         +--v--+       |
//        +----> dog |         | log |       |
//        |    +--+--+         +--+--+       |
//        |       |               |          |
//        |       |    +--v--+    |          |
//        |       +--->| cog |<-- +          |
//        |            +-----+               |
//        |                                  |
//        |                                  |
//        +----------------------------------+
/**
 *
 * @author Borui Wang
 */
public class WordLadder2 {

// *************************************************************************
// 1.) Add both begin word and end word to word list dictionary. 
// 2.) use BFS to find shortest path from begin word to end, not using DFS because it
//     could explore a lot more undesired nodes before reaching end word.
// 3.) starting from the end word using shortest path from step 1, traverse to
//     begin word via DFS using only visited nodes, we do not traverse from
//     begin word due to the following reasons
// *************************************************************************
// 
//     * for example 
//                    [s2] 
//                     |   
//                    [s1] 
//                     | 
//    [s2] -- [s1] -- [s0] -- [e1] -- [e1] -- [e0] 
//                     | 
//                    [s1]
//                     |
//                    [s2]
//     
//  We would traverse all neighbors of that is one distance away from s0, and
//  2 distances away from s, then get to the end But if we were to use DFS we
//  would reach start in one pass
    static List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> res = new ArrayList();
        //use hashmap to keep shortest distance from begin word and keep track of visited words
        if (wordList == null || beginWord == null || endWord == null) {
            return res;
        }

        //BFSMap stores the word its next immediate neighbors
        Map<String, List<String>> BFSMap = new HashMap();
        //distances keeps visited words, and their distance from beginword
        Map<String, Integer> distances = new HashMap();

        //add begin and end words into the graph
        wordList.add(beginWord);
        wordList.add(endWord);

        //find shortest path from start to end using BFS 
        bfsShortestPath(beginWord, endWord, BFSMap, distances, wordList);
        
        //find all path from end word to begin word from visited words in BFSMap
        dfsAllPaths(res, new ArrayList(), BFSMap, distances, wordList, beginWord, endWord);
        return res;
    }

    static void dfsAllPaths(List<List<String>> res, List<String> path, Map<String, List<String>> BFSVisited, Map<String, Integer> distances, Set<String> wordList, String beginWord, String endWord) {
        path.add(endWord);
        if (endWord.equals(beginWord)) {
            //we added path from end word to begin word, therefore, we need to reverse its order
            Collections.reverse(path);
            res.add(new ArrayList(path));
            Collections.reverse(path);
        } else {
            for (String neighbor : BFSVisited.get(endWord)) {
                //neighbor is visited by previous BFS and neighbor is one distance closer to begin word than end word
                if (distances.containsKey(neighbor) && distances.get(neighbor) + 1 == distances.get(endWord)) {
                    dfsAllPaths(res, path, BFSVisited, distances, wordList, beginWord, neighbor);
                }
            }
        }
        path.remove(path.size() - 1);
    }

    static void bfsShortestPath(String beginWord, String endWord, Map<String, List<String>> BFSMap, Map<String, Integer> distances, Set<String> wordList) {
        //distance from begin word
        Queue<String> q = new LinkedList();
        q.add(beginWord);
        distances.put(beginWord, 0);
        //make sure all words are in the map
        for(String word : wordList) {
            BFSMap.put(word, new ArrayList());
        }
         

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String word = q.poll();
                //find all neighbors
                for (String neighbor : findNeighbors(word, wordList)) {
                    BFSMap.get(word).add(neighbor);
                    if (!distances.containsKey(neighbor)) {
                        distances.put(neighbor, distances.get(word) + 1);
                        q.add(neighbor);
                    }
                }
            }
        }
    }

    static List<String> findNeighbors(String word, Set<String> wordList) {
        List<String> res = new ArrayList();
        for (int i = 0; i < word.length(); i++) {
            char[] temp = word.toCharArray();
            for (char j = 'a'; j <= 'z'; j++) {
                temp[i] = j;
                String candidate = new String(temp);
                if (!candidate.equals(word) && wordList.contains(candidate)) {
                    res.add(candidate);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        //String[] list = {"hot", "dot", "dog", "lot", "log", "dit", "hig", "dig"};
        //String[] list = {"hot", "dot", "dog"};
        //String[] list = {"hot", "dot", "dog", "lot", "log"};
//        String[] list = {"hot", "dog"};
//        System.out.print(findLadders("hot", "dog", new HashSet(Arrays.asList(list))));
        String[] list = {"a", "b","c"};
        System.out.print(findLadders("a", "c", new HashSet(Arrays.asList(list))));
    }
}
