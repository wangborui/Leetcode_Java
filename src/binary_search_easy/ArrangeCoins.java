/*
Source: https://leetcode.com/problems/arranging-coins/

********************************************************************************
You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
********************************************************************************

Solution:
create a private method "needed" to calculate the current number of needed coins at this level
start is the lowest level of the coins
end is the highest level of the coins
use binary search to find the middle point between start and end level that uses the totol available coins

invariant:
start is the level < total number of coins needed
end is the level >= the total number of coins needed

Loop termination:
start is one less than end
if needed(end) == total, then return end
else return start
 */
package Leetcode_Java.binary_search_easy;

/**
 *
 * @author Borui Wang
 */
public class ArrangeCoins {
    static int arrangeCoins(int n) {
        int start = 1;
        int end = n;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            long needs = needed(mid);
            if(needs >= (long)n) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if(needed(end) == (long)n) {
            return end;
        } else {
            return start;
        }
    }
    static long needed(int level) {
        return (long)level * (long)(level + 1) / 2;
    }
    public static void main(String[] args){
        System.out.println(arrangeCoins(1804289383));
    }
}
