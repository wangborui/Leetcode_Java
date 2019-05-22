/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_easy;

import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Borui Wang
 */

//Solution Answer
public class ShortestDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
        int shortestDistance = Integer.MAX_VALUE; 
        int i1,i2;
        i1=i2=-1;
        for(int i = 0; i < words.length; i ++){
            if(words[i].equals(word1)){
                i1 = i;
            }
            else if(words[i].equals(word2)){
                i2 = i;
            }
            
            if(i1!=-1 && i2 != -1){
                shortestDistance = Math.min(shortestDistance, Math.abs(i1-i2));
            }
        }
        
        return shortestDistance;
    }
    public static void main(String[] args){
 
         String[] words = {"a","b","c","d","d"};
        String word1 = "a";
        String word2 = "b";
//        
//        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
//        String word1 = "practice";
//        String word2 = "coding";
        
//          String[] words = {"a","c","b","a"};
//        String word1 = "a";
//        String word2 = "b";
        StdOut.println(new ShortestDistance().shortestDistance(words, word1, word2));
    }
}
