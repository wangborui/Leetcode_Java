/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_easy;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class LongestPalindrome {
    /*
    solution found
    int length = s.length();
        if(length <= 1) return length;
        int count = 0;
        Set<Character> set = new HashSet<Character>();
        
        for(char temp: s.toCharArray()){
            if(set.contains(temp)){
                set.remove(temp);
                count+=2;
            }
            else{
                set.add(temp);
            }
        }
        
        if(set.isEmpty()){
            return count;
        }
        else{
            return count+1;
        }
    */
    //my solution: did not work
//    public int longestPalindrome(String s) {
//        int length = s.length();
//        if(length <= 1) return length;
//        int count = 0;
//        Map<Character,Integer> map = new HashMap<Character, Integer>();
//        for(int i = 0; i < length; i ++){
//            char temp = s.charAt(i);
//            if(map.containsKey(temp)){
//                int times = map.get(temp);
//                times++;
//                map.put(temp,times);
//            }
//            else{
//                map.put(temp,1);
//            }
//        }
//        for(char c:map.keySet()){
//            if(map.get(c) % 2 == 0){
//                count++;
//            }
//        }
//        
//        return count;
//    }
}
