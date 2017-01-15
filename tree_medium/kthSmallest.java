/*
Source : https://leetcode.com/problems/kth-smallest-element-in-a-bst/
Date   : 01/14/2017

********************************************************************************
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).
********************************************************************************
 */
package Leetcode_Java.tree_medium;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
//this question can be solved using iterative inorder traversal, because inorder traveral naturally makes BST in order 
public class kthSmallest {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
//iterative traversal
    static int kthSmallest(TreeNode root, int k) {
        int count = 0;
        Stack<TreeNode> stack = new Stack();
        TreeNode walker = root;
        while (!stack.isEmpty() || walker != null) {
            if (walker != null) {
                stack.push(walker);
                walker = walker.left;
            } else {
                TreeNode temp = stack.pop();
                count++;
                if (count == k) {
                    return temp.val;
                }
                if (temp.right != null) {
                    walker = temp.right;
                }

            }
        }
        return -1;
    }

    static TreeNode createTree(Integer[] nodes) {
        Queue<TreeNode> q = new LinkedList();
        int index = 0;
        TreeNode root = new TreeNode(nodes[index++]);
        q.add(root);

        while (!q.isEmpty() && index < nodes.length) {
            TreeNode temp = q.poll();

            TreeNode left = nodes[index++] == null ? null : new TreeNode(nodes[index - 1]);
            TreeNode right = nodes[index++] == null ? null : new TreeNode(nodes[index - 1]);
            temp.left = left;
            temp.right = right;
            if (left != null) {
                q.add(left);
            }
            if (right != null) {
                q.add(right);
            }
        }
        return root;
    }
    public static void main(String[] args) {
        TreeNode tn = createTree(new Integer[]{3,1,4,null,2});
        kthSmallest(tn , 3);
     }
}
