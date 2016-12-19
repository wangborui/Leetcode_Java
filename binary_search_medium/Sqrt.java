/*
Source: https://leetcode.com/problems/sqrtx/

********************************************************************************
Implement int sqrt(int x).

Compute and return the square root of x.
********************************************************************************

Solution:
1. assume loop invariant hi^2 > x, lo^2 <= x, check exception, hi = 1, x = 1
2. assume loop invariant hi^2 >= x, lo^ < x 
 */
package Leetcode_Java.binary_search_medium;

/**
 *
 * @author Borui Wang
 */
public class Sqrt {
    public int mySqrt(int x) {
         
        if(x == 1) return 1;
        long lo = 0;
        long hi = (long) x;
        //invariant before loop
        //hi^2 > x, except when hi = 1, x = 1
        //lo^2 <= x
        while (lo + 1 < hi) {
            long mid = lo + (hi - lo) / 2;
            if (mid * mid > x) hi = mid;
            else lo = mid;
        }
        //after loop ends, 
        //hi^2 > x, except when hi = 1, x = 1
        //lo^2 <= x
        return (int)lo;
    }
    public int mySqrt2(int x) {
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
