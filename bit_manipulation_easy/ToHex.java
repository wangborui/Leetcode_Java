/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bit_manipulation_easy;

/**
 *
 * @author Borui Wang
 */
public class ToHex {
     static String toHex(int num) {
        if(num == 0) return "0";
        char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        String hex = "";
        while(num!=0){
            hex = map[(num&15)] + hex;
            num = (num>>>4);
        }
        return hex;
    }
    //my solution
//    static String toHex(int num) {
//        String binary = "";
//        String hex = "";
//        for(int i = 0; i < 32; i++){
//            binary = (num&1)+binary;
//            num>>=1;
//        }
//        for(int i = 0; i < 8; i++){
//            int hexNum = 0;
//            for(int j = 0; j <4;j++){
//                hexNum = 2*hexNum +(binary.charAt(i*4+j) - '0');
//            }
//            if(hexNum == 10){
//                hex+="a";
//            }
//            else if(hexNum == 11){
//                hex+="b";
//            }else if(hexNum == 12){
//                hex+="c";
//            }else if(hexNum == 13){
//                hex+="d";
//            }else if(hexNum == 14){
//                hex+="e";
//            }else if(hexNum == 15){
//                hex+="f";
//            }else if(hexNum > 0){
//                hex+=""+hexNum;
//            }else if(hex.length()>0){
//                hex+="0";
//            }
//        }
//        return hex;
//    }
      public static void main(String[] args){
        System.out.println(toHex(-1 ));
    }
}
