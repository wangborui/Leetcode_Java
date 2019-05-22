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
public class IsUgly {
     static boolean isUgly(int num) {
        if(num <=0) return false;
        if(num <= 5) return true;
        
        boolean [] isPrime = new boolean[num+1];
        for(int i = 0; i < num+1;i++){
            isPrime[i] = true;
        }
        for(int i = 2; i*i <=num;i++){
            if(isPrime(i)){
                for(int j = i;i*j<=num; j++){
                    isPrime[i*j] = false;
                }
            }
        }
        for(int i = 6; i <= num;i++){
            if(isPrime[i] && num%i == 0) return false;
        }
        
        return true;
        
    }
    
    static boolean isPrime(int n){
        for(int i = 2; i < n; i++){
            if(n%i == 0) return false;
        }
        return true;
    }
    public static void main(String[] args){
        System.out.println(70%16);
        int num = 1128;
        int ary = 16;
        StringBuilder sb = new StringBuilder();
        
        while(num >0){
            sb.append(num%ary);
            num/=ary;
        }
        System.out.println(sb.reverse().toString());
        
    }
}
