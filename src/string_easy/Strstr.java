/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.string_easy;

/**
 *
 * @author Borui Wang
 */
public class Strstr {
    public int strStr(String haystack, String needle) {
        //null check
        if(haystack == null || needle == null) return -1;
        int j = 0;
        //space needed: <space> +/-*= <space>
        for(int i = 0; i < haystack.length() - needle.length() + 1;i++){
            for(j = 0; j < needle.length();j++){
                if(haystack.charAt(i+j) != needle.charAt(j)) break;
            }
            if(j == needle.length()) return i;
        }
        return -1;
    }
}
