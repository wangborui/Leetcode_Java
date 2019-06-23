/*
// Source : https://oj.leetcode.com/problems/word-ladder/
// Date   : 2017-01-02

/********************************************************************************** 
* 
* Given two words (start and end), and a dictionary, find the length of shortest 
* transformation sequence from start to end, such that:
* 
* Only one letter can be changed at a time
* Each intermediate word must exist in the dictionary
* 
* For example,
* 
* Given:
* start = "hit"
* end = "cog"
* dict = ["hot","dot","dog","lot","log"]
* 
* As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
* return its length 5.
* 
* Note:
* 
* Return 0 if there is no such transformation sequence.
* All words have the same length.
* All words contain only lowercase alphabetic characters.
* 
*               
**********************************************************************************/

// --------------------------- 
//  BFS non-recursive method
// ---------------------------
//
//    Using BFS instead of DFS is becasue the solution need the shortest transformation path.
//  
//    So, we can change every char in the word one by one, until find all possible transformation.
//
//    Keep this iteration, we will find the shorest path.
//
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
//     
//     1) queue <==  "hit"
//     2) queue <==  "dit", "hot", "hig"
//     3) queue <==  "dot", "lot", "dig"
//     4) queue <==  "dog", "log" 
  
package Leetcode_Java.graph_medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Borui Wang
 */
public class WordLadder {
    //One way BFS
    static int ladderLength(String beginWord, String endWord, Set<String> wordLists) {
        /********************************************
         * Test Case:
         * 1. wordList is null, return 0
         * 2. begin word equals end word, return 1
         * 3. begin and end words can be connected via word list
         *********************************************/
        Set<String> wordList = new HashSet<>(wordLists);
        if(wordList == null || beginWord == null || endWord == null) {
            return 0;
        }
        //2 purposes of hash map:
        //1.) keep track of visited words
        //2.) keep distance of each node from the source

        Map<String, Integer> visited = new HashMap();
        Queue<String> q = new LinkedList();

        visited.put(beginWord, 1);
        q.add(beginWord);

        while(!q.isEmpty()) {
            String word = q.poll();
            //find neighbors of word
            for(int i = 0; i < word.length(); i++) {
                char[] temp = word.toCharArray();
                for(char j = 'a'; j <= 'z'; j++) {
                    temp[i] = j;
                    String candidate = new String(temp);
                    if(!visited.containsKey(candidate) && wordList.contains(candidate)) {
                        q.add(candidate);
                        visited.put(candidate, visited.get(word) + 1);
                    }
                }
            }
        }
        return visited.getOrDefault(endWord, 0);
    }

    //Two way BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet(wordList);
        //start from begin word
        List<String> beginSet = new LinkedList();
        //start from end word
        List<String> endSet = new LinkedList();
        //keep track of all visited words
        Set<String> visited = new HashSet();

        beginSet.add(beginWord);
        if(dict.contains(endWord)) {
            endSet.add(endWord);
        } else {
            return 0;
        }

        for(int dist = 2; !beginSet.isEmpty() ; dist++) {
            List<String> temp = new LinkedList();

            //find neighbors
            for(String oldWord: beginSet) {
                for(int j = 0; j < oldWord.length(); j++) {
                    char[] wc = oldWord.toCharArray();
                    for(char c = 'a'; c <= 'z'; c++) {
                        //bypass the oldword itself
                        if(c == oldWord.charAt(j)) {
                            continue;
                        }
                        wc[j] = c;
                        String newWord = new String(wc);
                        //two ends meet
                        if(endSet.contains(newWord)) {
                            return dist;
                        }
                        if(dict.contains(newWord) && !visited.contains(newWord)) {
                            temp.add(newWord);
                            visited.add(newWord);
                        }
                    }
                }
            }

            //switch the smaller sized set to be begining set
            beginSet = temp;
            if(endSet.size() < beginSet.size()) {
                temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        String[] list = {"hot","dot","dog","lot","log"};
        System.out.println(ladderLength("hit", "cog", new HashSet(Arrays.asList(list))));
    }
}
