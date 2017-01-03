/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author Borui Wang
 */
public class WordLadder2 {

    /**
     * **************************************
     * 1.) use BFS to find shortest path from begin word to end, not using DFS
     * because it could explore a lot more undesired nodes before reaching end
     * word 2.) starting from the end word using shortest path from step 1,
     * traverse to begin word via DFS using only visited nodes.
     ***************************************
     */

//    static List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
//        List<List<String>> res = new ArrayList();
//        //use hashmap to keep shortest distance from begin word and keep track of visited words
//        if (wordList == null || beginWord == null || endWord == null) {
//            return res;
//        }
//        //BFSMap stores string, and its list of neighbors
//        Map<String, List<String>> BFSMap = new HashMap();
//        //stores string and its shortest distance from begin word
//        Map<String, Integer> distance = new HashMap();
// 
//        //add begin and end words into the graph
//        wordList.add(beginWord);
//        wordList.add(endWord);
//
//        //find shortest path from start to end using BFS 
//        bfsShortestPath(beginWord, endWord, BFSMap, distance, wordList);
//        
//        //find all path from end word to begin word from visited words
//       // dfsAllPaths(res, BFSMap, wordList, beginWord, endWord);
//        return res;
//    }
//
//    static void dfsAllPaths(List<List<String>> res, Map<String, String> BFSVisited, Set<String> wordList, String beginWord, String endWord) {
//        Stack<String> prevs = new Stack();
//        //find all valid neighbors of end word
//        for (int i = 0; i < endWord.length(); i++) {
//            char[] temp = endWord.toCharArray();
//            for (char j = 'a'; j <= 'z'; j++) {
//                temp[i] = j;
//                String neighbor = new String(temp);
//                if (BFSVisited.containsKey(neighbor) && wordList.contains(neighbor)) {
//                    prevs.push(neighbor);
//                }
//            }
//        }
//        while (!prevs.isEmpty()) {
//            String prev = prevs.pop();
//            List<String> path = new ArrayList();
//            path.add(endWord);
//            while (BFSVisited.containsKey(prev)){
//               path.add(prev);
//               prev = BFSVisited.get(prev);
//           }
//            res.add(path);
//        }
//    }
//
//    static void bfsShortestPath(String beginWord, String endWord, Map<String, List<String>> BFSMap, Map<String, Integer> distance, Set<String> wordList) {
//        Queue<String> q = new LinkedList();
//        q.add(beginWord);
//        
//        distance.put(beginWord, 1);
//        
//        while (!q.isEmpty()) {
//            String word = q.poll();
//            BFSMap.put(word, new ArrayList());
//            //find all neighbors
//            for(String neighbor : findNeighbors(word, wordList)) {
//                if(!distance.containsKey(neighbor)) {
//                     BFSMap.get(word).add(neighbor);
//                     distance.put(neighbor, distance.get(word) + 1);
//                     q.add(neighbor);
//                }
//            } 
//        }
//    }
//    static List<String> findNeighbors(String word, Set<String> wordList) {
//        List<String> res = new ArrayList();
//        for (int i = 0; i < word.length(); i++) {
//                char[] temp = word.toCharArray();
//                for (char j = 'a'; j <= 'z'; j++) {
//                    temp[i] = j;
//                    String candidate = new String(temp);
//                    if (wordList.contains(candidate)) {
//                        res.add(candidate);
//                    }
//                }
//            }
//        return res;
//    }
    
    //jiuzhang template
    static List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> ladders = new ArrayList();
        Map<String, List<String>> map = new HashMap();
        Map<String, Integer> distance = new HashMap();
        
        dict.add(start);
        dict.add(end);
        
        bfs(map, distance, start, end, dict);
        
        List<String> path = new ArrayList();
        
        dfs(ladders, path, end, start, distance, map);
        
        return ladders;
    }
    static void dfs(List<List<String>> ladders, List<String> path, String crt,
            String start, Map<String, Integer> distance,
            Map<String, List<String>> map) {
        path.add(crt);
        
        if(crt.equals(start)) {
            Collections.reverse(path);
            ladders.add(new ArrayList(path));
            Collections.reverse(path);
        } else {
            for(String next: map.get(crt)){
                if(distance.containsKey(next) && distance.get(crt) == distance.get(next) + 1) {
                    dfs(ladders, path, next, start, distance, map);
                }
            }
        }
        
    }
    static void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
            String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList();
        q.offer(start);
        distance.put(start, 0);
        
        for(String s : dict) {
            map.put(s, new ArrayList());
        }
        
        while(!q.isEmpty()) {
            String crt = q.poll();
            
            List<String> nextList = expand(crt,dict);
            for(String next : nextList) {
                map.get(next);
                if(!distance.containsKey(next)) {
                    distance.put(next, distance.get(crt) + 1);
                    q.offer(next);
                }
            }
        }
    }
    
    static List<String> expand(String crt, Set<String> dict) {
        List<String> expansion = new ArrayList();
        
        for(int i = 0; i < crt.length(); i++) {
            char[] tmp = crt.toCharArray();
            for(char ch = 'a'; ch <= 'z'; ch++) {
                if(ch != tmp[i]) {
                    tmp[i] = ch;
                    String expanded = new String(tmp);
                    if(dict.contains(expanded)) {
                        expansion.add(expanded);
                    }
                }
            }
        }
        return expansion;
    }
    public static void main(String[] args) {
        String[] list = {"hot", "dot", "dog", "lot", "log"};
        System.out.print(findLadders("hit", "cog", new HashSet(Arrays.asList(list))));
    }
}
