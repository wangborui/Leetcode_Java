/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package two_pointer_easy;

import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Borui Wang
 */
public class strStr {
    static int strStr(String haystack, String needle) {
        int sizeH = haystack.length();
        int sizeN = needle.length();
        
        if(sizeH < sizeN) return -1;
        if(  sizeN == 0) return 0;
        
        for(int i = 0; i < sizeH; i++){
            for(int j = 0; j<sizeN && i+j < sizeH; j++){
                if(haystack.charAt(i+j) != needle.charAt(j))
                {
                    i = i+j;
                    break;
                }
                if(j == sizeN-1){
                    return i;
                }
            }
        }
        return -1;
    }
    /*
    solution found
     public int strStr(String haystack, String needle) {
          for (int i = 0; ; i++) {
    for (int j = 0; ; j++) {
      if (j == needle.length()) return i;
      if (i + j == haystack.length()) return -1;
      if (needle.charAt(j) != haystack.charAt(i + j)) break;
    }
  }
    }
    */
    public static void main(String[] args){
        StdOut.println(strStr("missi","missi"));
    }
}
