/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_easy;

import edu.princeton.cs.algs4.StdOut;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Borui Wang
 */
public class IsIsomorphic {
    /*
    solutoin found
    public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (Integer i = 0; i < s.length(); i++)
        {
            if (map1.put(s.charAt(i), i) != map2.put(t.charAt(i), i)) return false;
        }
        return true;
    }
}
    */
    //my solution works
    public boolean isIsomorphic(String s, String t) {
        int sizeS = s.length();
        int sizeT = t.length();
        if(sizeS != sizeT) return false;
        Map<Character, Character> map = new HashMap<Character, Character>();
        Set<Character> set = new HashSet<Character>();
        
        for(int i =0; i < sizeS;i++){
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if(map.containsKey(sc) && map.get(sc) != tc) return false;
            else if(!map.containsKey(sc)){
                if(set.contains(tc)) return false;
                map.put(sc,tc);
                set.add(tc);
            }
            
        }
        return true;
    }
    public static void main(String[] args){
        Map<Character, Integer> map1 = new HashMap<>();
//        StdOut.println(map1.put('c', 2));
//        StdOut.println(map1.put('c', 3));
//        StdOut.println(map1.put('c', 3));
 StdOut.println('0'^'c');
      
    }
}
