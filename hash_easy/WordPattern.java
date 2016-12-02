/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Borui Wang
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        String [] strs = str.split(" ");
        int size = pattern.length();
        if(size != strs.length) return false;
        
        Map<Character, String> map = new HashMap<Character, String>();
        Set<String> set = new HashSet<String>();
        for(int i = 0; i < size; i++){
            if(map.containsKey(pattern.charAt(i))){
                if(!map.get(pattern.charAt(i)).equals(strs[i])) return false;
            }
            else{
                if(!set.contains(strs[i])){
                    map.put(pattern.charAt(i),strs[i]);
                    set.add(strs[i]);
                }
                else return false;
            }
            
        }
        return true;
    }
}
