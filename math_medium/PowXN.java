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
 *     1) We need to think about the `n` is negtive number.
 *
 *     2) We need to more wisely deal with the following cases:
 *
 *         pow(1, MAX_INT);
 *         pow(-1,BIG_INT);
 *         pow(2, BIG_INT);
 *
 *        To deal with such kind of case, we can use x = x*x to reduce the `n` more quickly
 *
 *        so, 
 *             if `n` is an negative, we can `x = 1/x`, and `n = -n;`
 *             if `n` is an even number, we can `myPow(x*x, n/2)`
 *             if `n` is an odd number, we can just `x*myPow(x*x, n/2)`
        Edge Case: x can get really big, so we need to check if x is inifinite then return 0
 *   Time O(log n)
 */
//    有三种情况需要考虑
//            n是一个负数，我们把x设置为 1/x, n变成-n
//            n是一个偶数，我们可以递归的计算 myPow（x * x，n /2)
//            n是一个奇数，我们可以递归的计算 x * myPow（x * x，n/2)
//    需要注意的有两个：
//            当n = 0的时候，我们直接返回1
//            当x变得无限大的时候，我们需要返回0
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
