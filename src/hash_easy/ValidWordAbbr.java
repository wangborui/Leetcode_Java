/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_easy;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Borui Wang
 */
public class ValidWordAbbr {
    Set<String> set ;
    public ValidWordAbbr(String[] dictionary) {
        set = new HashSet<String>();
        for(String s: dictionary){
            String temp = abbreviate(s);
            set.add(temp);
        }
    }

    public boolean isUnique(String word) {
        String temp = abbreviate(word);
        return !set.contains(temp);
    }
    private String abbreviate(String s){
        int n = s.length();
        if(n<=2) return s;
        return ""+s.charAt(0)+(n-2)+s.charAt(n-1);
    }
        public static void main(String[] args){
            String [] dict = {"hello"};
             ValidWordAbbr vwa = new ValidWordAbbr(dict);
            vwa.isUnique("hello");
// vwa.isUnique("anotherWord");
        }
}
