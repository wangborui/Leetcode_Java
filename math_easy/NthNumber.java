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
public class NthNumber {
    
    /*
    solution found
     public int findNthDigit(int n) {
       	int len = 1;
		long count = 9;
		int start = 1;

		while (n > len * count) {
			n -= len * count;
			len += 1;
			count *= 10;
			start *= 10;
		}

		start += (n - 1) / len;
		String s = Integer.toString(start);
		return Character.getNumericValue(s.charAt((n - 1) % len));
        
    }
    */
    static int findNthDigit(int n) {
        int count = 1; 
        long multiple = 9;
        int base_ten = 1;
        while(n > multiple*count ){
            n -=multiple*count;
            multiple*=10; base_ten *=10;count++;
        }
        base_ten = base_ten + (n-1)/count;
        String s = Integer.toString(base_ten);
        return  s.charAt((n-1)%count)-'0';
    }
      public static void main(String[] args){
        //System.out.println(214748364 );
         System.out.println( findNthDigit(2147483647));
    }
}
