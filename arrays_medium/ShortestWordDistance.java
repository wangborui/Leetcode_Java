/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arrays_medium;

/**
 *
 * @author Borui Wang
 */
public class ShortestWordDistance {
    static int shortestWordDistance(String[] words, String word1, String word2) {
        int n = words.length;
        boolean same = word1.equals(word2);
        long min = Integer.MAX_VALUE, i1 = min, i2 = -min;
        for(int i = 0; i < n; i++){
            if(words[i].equals(word1)){
                if(same){
                    i1 = i2;
                    i2 = i;
                }
                else{
                    i1 = i;
                }
            }
            else if(words[i].equals(word2)){
                i2 = i;
            }
            min = Math.min(Math.abs(i1-i2),min);
        }
        return (int)min;
    }
     public static void main(String[] args){
         System.out.println(shortestWordDistance(new String[]{"a","a","b","b"},"a","b")); 
 
     }
}
