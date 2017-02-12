/*
Source : https://github.com/shawnfan/LintCode/blob/master/Java/Max%20Tree.java 
Date   : 01/30/2017

********************************************************************************
Given an integer array with no duplicates. A max tree building on this array is defined as follow:
The root is the maximum number in the array
The left subtree and right subtree are the max trees of the subarray divided by the root number.
Construct the max tree by the given array.
Example
Given [2, 5, 6, 0, 3, 1], the max tree constructed by this array is:
    6
   / \
  5   3
 /   / \
2   0   1
Challenge
O(n) time and memory.
********************************************************************************

Solutions:
Recursion: 
Time O(n^2) worst case number[1,2,3,4,5,6,7]
Time O(n log n) average case
Iteration: Time O(n) Space O(n)
 */
package Leetcode_Java.stack_hard;

import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class MaxTree {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    /**
     * 
     * @param A
     * @return TreeNode
     * Analysis:
     * 
     * Given an element, we need to find that element's first left or right that are larger/smaller than this element,
     * therefore, we can use stack 
     * 
     * Iterate the array from left to right. push the element into stack
     * pop elements from stack if current element c is larger than top of stack, meaning that each smaller element can be left child of x
     * after finding the largest element smaller than x, if there are still elements left in stack, this element y must have x as right child. why?
     * because all elements smaller than y have become the left child of y and y is the "left-largest" element of x.
     * we now have y, x on the stack, because both elements haven't found the "right-largest" element larger than them both
     * we keep repeating this process till the end of iteration
     *
     * We push every element into stack during iteration
     * 
     *      1.) left-child: using while loop to find the largest element in the stack that is smaller than current element, and set that element as left child of current element
     *      2.) if there are still elements left in stack, that element must be the parent of current element, current element must be right child of that element 
     *      3.) after iterating all element, the last element in stack must be the root of all elements, therefore, we pop all elements in stack to get that last element
     * 
     * for example, given array [2, 5, 6, 0, 3, 1]
     * 
     *      stack = [2], because 5 > 2, so 2 is left child of 5, pop 2
     *      stack = [], push 5
     *      stack = [5], because 6 > 5，so 5 is left child of 6, pop 5
     *      stack = [], push 6(right-largest element hasn't been found)
     *      stack = [6], because 0 less than 6, stack = [6], set 0 to be the right child of 6, also push 0(right-largest element hasn't been found)
     *      stack = [6, 0] because 3 > 0, so 0 is left child of 3， pop 0, because 6 > 3, make 3 the right child of 6
     *      stack = [6], push 3(right-largest element hasn't been found)
     *      stack = [6, 3], push 1, because 1 is less than 3, so set 3 right child to be 1
     * 
     * Note: Interesting behavior:
     * Stack: always stores the largest value at bottom. In above example, when 6 gets in stack, it will never come back.
     * All smaller element (smaller than current point) will be popped out, 
     * and of course, the last-possible-smaller element will be the largest smaller point on stack, then we attach it to current node.
     * These behavior keeps larger value on upper level of the tree
     * 
     * Therefore, the number is the stack is in descending order 
     */
    static TreeNode maxTree(int[] A) {
        if(A == null || A.length == 0) {
            return null;
        }
        Stack<TreeNode> stack = new Stack();
        for(int i = 0; i < A.length; i++) {
            TreeNode node = new TreeNode(A[i]);
            //1.) left-child: using while loop to find the largest element in the stack that is smaller than current element, and set that element as left child of current element
            while(!stack.isEmpty() && node.val >= stack.peek().val) {
                node.left = stack.pop();
            }
            // 2.) if there are still elements left in stack, that element must be the parent of current element, current element must be right child of that element 
            if(!stack.isEmpty()) {
                stack.peek().right = node;
            }
            stack.push(node);
        }
        
        TreeNode res = stack.peek();
        //3.) after iterating all element, the last element in stack must be the root of all elements, therefore, we pop all elements in stack to get that last element
        while(!stack.isEmpty()) {
            res = stack.pop();
        }
        return res;
    }
    public static void main(String [] args) {
        maxTree(new int[]{2, 5, 6, 0, 3, 1});
    }
}
