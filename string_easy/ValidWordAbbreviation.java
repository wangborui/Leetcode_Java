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
public class ValidWordAbbreviation {
    /*
    solution found
    public boolean validWordAbbreviation(String word, String abbr) {
        if(word == null || abbr == null) return false;
        int num = 0;
        int idx = 0;
        
        for(char c: abbr.toCharArray()){
            if(c == '0' && num == 0) return false;
            if(c >= '0' && c <= '9'){
                num = num*10 + (c-'0');
            }else{
                idx += num;
                if(idx >= word.length() || c != word.charAt(idx)) return false;
                num = 0;
                idx++;
            }
        }
        
        return idx+num == word.length() ? true : false;
    }
    */
      static boolean validWordAbbreviation(String word, String abbr) {
        int W = word.length();
        int A = abbr.length();
         
        int a = 0;
        int w = 0;
        while(a < A && w < W){
            if(Character.isDigit(abbr.charAt(a))){
                int numLength = 0;
                 while(a+numLength < A && Character.isDigit(abbr.charAt(a+numLength))){
                     numLength++;
                 }
                 int num = Integer.parseInt(abbr.substring(a, a+numLength));
                 w += num;
                 a += numLength;
                 
            }
            else{
                if(word.charAt(w) != abbr.charAt(a)) return false;
                a++;
                w++;
            }
        }
         
        return w == W && a == A;
//        for(int i = 0; i < abbr.length(); i++){
//            if(Character.isDigit(abbr.charAt(i))){
//                while(i+numLength < abbr.length() && Character.isDigit(abbr.charAt(i+numLength))){
//                     numLength++;
//                     
//                }
//                int num = Integer.parseInt(abbr.substring(i, i+numLength));
//                i = i + numLength - 1;
//                w+= num;
//                numLength = 0;
//            }else{
//                if(word.charAt(w) != abbr.charAt(i)){
//                    return false;
//                }
//                w++;
//            }
//        }
//        return true;
    }
      public static void main(String[] args){
         System.out.println(validWordAbbreviation("hi","2i"));
          System.out.println(validWordAbbreviation("internationalization","i12iz4n"));
      }
}
