/*
Source : https://leetcode.com/problems/unique-binary-search-trees/
Date   : 01/19/2017

********************************************************************************
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
********************************************************************************

Analysis:
Let's iterate all the possibilities of this question
    1 way    n = 1   1
    2 ways   n = 2   1,2
                                1        2
                                 \      /
                                  2    1
    5 ways   n = 3   1,2,3
                                1         3     3      2      1
                                 \       /     /      / \      \
                                  3     2     1      1   3      2
                                 /     /       \                 \
                                2     1         2                 3
    14 ways  n = 4   1,2,3,4
                                1                    2                     3                4
                                 \                  / \                  /   \             /
                                {2,3,4}(5 ways)   {1}*{3,4}- -2 ways- -{1,2}* {4}       {1,2,3} 5 ways 
                                5 ways    +        1 * 2         +      2 * 1       +      5  = 14 ways
    42 ways  n = 5   1,2,3,4,5
                                T[4] + T[1] * T[3] + T[2] * T[2] + T[3] * T[1] + T[4] = 14 + 5 + 4 + 5 + 14 = 42 ways

So, we first know that 
when n = 0 there is one way for the BST, which is null
when n = 1 there is one way for the BST, which is just n
when n = i, we need to iterate all values of n, and find total ways we can make its left subtree and right subtree, and multiply the possibilities of the two

So here is the optimal function:
    Intialization: T[0] = 1 and T[1] = 1
        T[i] = Sum(T[0 : i - 1] * T[i - 1 : 0])
 */
package Leetcode_Java.tree_medium;

/**
 *
 * @author Borui Wang
 */
public class UniqueBSTs {
        public int numTrees(int n) {
        int [] dp = new int[n + 1];
        //initialization
        dp[0] = 1;
        dp[1] = 1;
        //        T[i] = Sum(T[0 : i - 1] * T[i - 1 : 0])
        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        
        return dp[n];
    }
}
