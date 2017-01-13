// Source : https://oj.leetcode.com/problems/sqrtx/
// Date   : 01/13/2017

/********************************************************************************** 
* 
* Implement int sqrt(int x).
* 
* Compute and return the square root of x.
*               
**********************************************************************************/
package Leetcode_Java.math_medium;

/**
 *
 * @author Borui Wang
 */
public class Sqrt {
    //use binary search assume low = 0, high = x
        public int mySqrt(int x) {
         
        long lo = 0;
        long hi = (long) x;
        //invariant before loop
        //hi^2 >= x
        //lo^2 < x
        while (lo + 1 < hi) {
            long mid = lo + (hi - lo) / 2;
            if (mid * mid > x) hi = mid;
            else lo = mid;
        }
        //after loop ends, 
        //hi^2 >= x
        //lo^2 < x
        if(hi*hi == x) {
            return (int)hi;
        } else {
            return (int)lo;
        }
    }
}
