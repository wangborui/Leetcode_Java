/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package string_easy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class ZigZag {
    static String convert(String s, int numRows) {
        int n = s.length();
        if(n == 0) return s;
        
        boolean increase = true;
        int index = 0;
        List [] lists = new ArrayList[numRows];
        for(int i = 0; i<numRows; i ++){
            lists[i] = new ArrayList<Character>();
        }
        
        for(int i = 0; i < n; i++){
            lists[index].add(s.charAt(i));
            if(increase) {
                if(index == numRows - 1){
                    increase = false;
                    index --;
                    
                }else{
                    index++;
                }
                 
            }
            else
            {
              if(index == 0){
                    increase = true;
                    index++;
                    
                }else{
                    index--;
                }  
                
            } 
        }
        StringBuilder sb = new StringBuilder();
        for(List a:lists){
            for(Object c: a){
                sb.append(""+(char)c);
            }
        }
        return sb.toString();
    }
      public static void main(String[] args){
         System.out.println(convert("AB",1));
     }
}
