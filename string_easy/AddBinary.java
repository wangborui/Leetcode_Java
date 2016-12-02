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
public class AddBinary {
    static String addBinary(String a, String b) {
        int aL = a.length();
        int bL = b.length();
        
        if(aL == 0) return b;
        if(bL == 0) return a;
        
        int i = aL-1;
        int j = bL -1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        
       while(i>=0 || j >=0 || carry == 1){
            int sum = ((i>=0?a.charAt(i)-'0':0) + (j>=0?b.charAt(j)-'0':0)+carry);
            sb.append(sum&1);
            carry = sum>>1&1;
            i--;
            j--;
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args){
        addBinary("1111","1111");
        System.out.println(0&1);
    }
}
