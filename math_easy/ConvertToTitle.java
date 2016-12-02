/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math_easy;

/**
 *
 * @author Borui Wang
 */
public class ConvertToTitle {
    static String convertToTitle(int n) {
        StringBuilder sb=  new StringBuilder();
        while(n>0){
            char temp = (char)('A'+(n-1)%26);
            sb.append(""+temp);
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args){
        //convertToTitle(1);
        System.out.println();
    }
}
