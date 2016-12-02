/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_easy;

/**
 *
 * @author Borui Wang
 */
public class FindTheDifference {
    // my solution
     public char findTheDifference(String s, String t) {
        int sum = 0;
        for(char c:s.toCharArray()){
            sum+=c;
        }
        for(char c:t.toCharArray()){
            sum-=c;
        }
        
        return (char)(sum*-1);
    }
}
