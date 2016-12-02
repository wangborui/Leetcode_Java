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
public class AddStrings {
    static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i,j,carry;  
        i=num1.length()-1;
        j = num2.length()-1;
        carry=0;
        while(i>=0 || j  >=0|| carry == 1){
            int one = i>=0 ?num1.charAt(i--)-'0':0;
            int two = j>=0 ?num2.charAt(j--)-'0':0;
            int sum = one+two+carry;
            carry = (sum) >= 10?1:0;
            sb.append(sum%10);
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args){
        System.out.println(addStrings("10","455"));
    }
}
