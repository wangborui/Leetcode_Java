// Source : https://oj.leetcode.com/problems/powx-n/
// Date   : 01/13/2017

/********************************************************************************** 
* 
* Implement pow(x, n).
* 
*               
**********************************************************************************/
package Leetcode_Java.math_medium;

/**
 *
 * @author Borui Wang
 */
public class PowXN {
    /*
 *   Basically, most people think this is very easy as below:
 *
 *      double result = 1.0;
 *      for (int i=0; i<n; i++){
 *           result *=x;
 *      }
 *   Time O(n)
 *   However, 
 *
 *     1) We need think about the `n` is negtive number.
 *
 *     2) We need more wisely deal with the following cases:
 *
 *         pow(1, MAX_INT);
 *         pow(-1,BIG_INT);
 *         pow(2, BIG_INT);
 *
 *        To deal with such kind case, we can use x = x*x to reduce the `n` more quickly
 *
 *        so, 
 *             if `n` is an negative, we can `x = 1/x`, and `n = -n;`
 *             if `n` is an even number, we can `myPow(x*x, n/2)`
 *             if `n` is an odd number, we can just `x*myPow(x*x, n/2)`
        Edge Case: x can get really big, so we need to check if x is inifinite then return 0
 *   Time O(log n)
 */
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        if(n<0){
            n = -n;
            x = 1/x;
        }
        //this line is optional, make sure x does not reach infinite
        if (Double.isInfinite(x)) 
			return 0.;
        if (n%2 == 0) {
            return myPow(x*x, n/2);
        } else {
            return x*myPow(x*x, n/2);
        }
    }
}
