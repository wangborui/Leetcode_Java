/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hash_easy;

import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Borui Wang
 */
public class CountPrimes {
    /* solution found
    public int countPrimes(int n) {
   boolean[] isPrime = new boolean[n];
   for (int i = 2; i < n; i++) {
      isPrime[i] = true;
   }
   // Loop's ending condition is i * i < n instead of i < sqrt(n)
   // to avoid repeatedly calling an expensive function sqrt().
   for (int i = 2; i * i < n; i++) {
      if (!isPrime[i]) continue;
      for (int j = i * i; j < n; j += i) {
         isPrime[j] = false;
      }
   }
   int count = 0;
   for (int i = 2; i < n; i++) {
      if (isPrime[i]) count++;
   }
   return count;
}
    */
    public int countPrimes(int n) {
        if(n <= 2) return 0;
        int count = 0;
        boolean [] marked = new boolean[n];
        int i;
        for(i = 2; i*i <= n;i++){
            if(!marked[i] && isPrime(i)){
               count++; 
               for(int j = 1; j * i < n; j++){
                marked[i*j] = true;
                }
            }  
             
        }
        while(i < n){
            if(!marked[i]) count++;
            i++;
        }
         return count;
    }
    private boolean isPrime(int num){
        for(int i = 2; i * i <= num; i++){
            if(num%i == 0) return false;
        }
        return true;
    }
    public static void main(String[] args){
        boolean [] b = new boolean[2];
        StdOut.println(b[0]);
    }
}
