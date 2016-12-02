/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package string_easy;

/**
 *
 * @author Borui Wang
 */
public class CountAndSay {
    static String countAndSay(int n) {
        if(n<=1) return "1";
        String s = "1";
        while(n>1){
            char cur = s.charAt(0);
            int count = 0;
            StringBuilder sb = new StringBuilder();
            for(char c:s.toCharArray()){
                if(c!=cur){
                    sb.append(""+count+cur);
                    cur = c;
                    count = 1;
                }
                else{
                    count++;
                }
            }
            sb.append(""+count+cur);
                n--;
                s = sb.toString();
        }
        return s;
    }
    public static void main(String[] args){
        countAndSay(3);
        
        System.out.println(3&1);
    }
}
