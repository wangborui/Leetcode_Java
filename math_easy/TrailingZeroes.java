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
public class TrailingZeroes {
    static int trailingZeroes(int n) {
        int i = n-1;
        int m = 0;
        
        //i一直在递减
		while(i>1){
			n=n*i;
			i--;
			//看看后面有几个0
			while (n%10==0){
				n=n/10;
				m++;
			}
			//得到个位数
			n=n%10;
		}
		return m;
    }
    public static void main(String[] args){
        System.out.println(trailingZeroes(100));
    }
}
