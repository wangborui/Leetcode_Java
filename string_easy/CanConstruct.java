/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package string_easy;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class CanConstruct {
     static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        
        for(char c:ransomNote.toCharArray()){
            int idx = magazine.indexOf(""+c, map.containsKey(c)?map.get(c):0);
            if(idx == -1) return false;
            else{
                map.put(c, ++idx);
            }
        }
        return true;
    }
     public static void main(String[] args){
        // canConstruct("aa","ab");
        StringBuilder sb = new StringBuilder("abcd");
        System.out.println("aaa".indexOf("aaa"));
        
     }
}
