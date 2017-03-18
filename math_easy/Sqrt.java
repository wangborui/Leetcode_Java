// Source : https://oj.leetcode.com/problems/sqrtx/
// Date   : 01/13/2017

/********************************************************************************** 
* 
* Implement int sqrt(int x).
* 
* Compute and return the square root of x.
*               
**********************************************************************************/
package Leetcode_Java.math_easy;

/**
 *
 * @author Borui Wang
 */
可以用二分查找来解决，二分查找找到一个整数的平方是刚刚好等于x，或者找到最大的一个数他的平方刚刚小于x
为了防止溢出，我们先把low 设置为0，类型设置为long；high设置为x，类型设置为long
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
