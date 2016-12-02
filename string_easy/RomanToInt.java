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
public class RomanToInt {
    static int romanToInt(String s) {
        int n = s.length();
        if(n == 0) return 0;
        int result = 0;
        int last = 0;
        for(int i = n-1; i>=0; i--){
            int val = getVal(s.charAt(i));
            if(val < last) result -= val;
            else result += val;
            last  = val;
        }
        return result;
    }
    static int getVal(char c){
        switch (c){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
             case 'C': return 100;
             case 'D': return 500;
             case 'M': return 1000;
             default: return 0;
        }
                
    }
     public static void main(String[] args){
         System.out.println(romanToInt("LXVII"));
     }
}
