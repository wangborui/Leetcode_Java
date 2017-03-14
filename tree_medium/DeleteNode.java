/*
Source: https://leetcode.com/problems/delete-node-in-a-bst/
Date: 12/22/2016

********************************************************************************
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
********************************************************************************

Solution:
first find the node that needs to be deleted, if not found return the tree as is
after finding the node to be deleted, find its left sub-tree max value or right sub-tree min value as temp
switch deleting node value with temp, then recursively delete temp from left or right sub-tree

Note: inorder to delete a node, we must find the node's parent, then do parent.node = null to delete it
For example,
        if node -> 2, doing node =  null; would change node to null but 2 still exists and is the children of its parent
        need to find parent of node, and set parent.right = null or parent.left = null to delete desired node
 */
package Leetcode_Java.tree_medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class DeleteNode {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    /*
    Solution: find either the max value in left tree or min value in right tree,
    change deleting node value to that value, then delete either the min value in right subtree or max value in left tree
    */
//    这里我们有还几个点需要注意的
//    首先，如果在二叉树中删除一个点，不能直接把这个点设置成空，因为这个点还是被他的父亲节点所指向的，所以其实没有删除
//    第二，我们在找寻和删除一个节点的时候，可以一边搜索一边删除
//    第三，当我们在删除某个节点的时候，如果其左子树是空的，就返回右子树，如果其右子树是空的，返回左子树
//            如果两个子树都不为空，找到左子树的最大值（或者右子树的最小值），储存为val，把要删除的节点的值换成val
//            然后再从左子树递归的删除这个最大值
    static TreeNode deleteNode2(TreeNode root, int key) {
        if(root == null) {
            return null;
        } else if(root.val > key) {
            root.left = deleteNode2(root.left, key);
        } else if(root.val < key) {
            root.right = deleteNode2(root.right, key);
        } else { //root.val = key
            if(root.left == null) {
                return root.right;
            } else if(root.right == null) {
                return root.left;
            } else { //both right and left subtrees are not null
                root.val = findMaxVal(root.left);
                root.left = deleteNode2(root.left, root.val);
            }
        }
        return root;
    }
    static int findMaxVal(TreeNode root) {
        while(root.right != null) {
            root = root.right;
        }
        return root.val;
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
        //create tree
        /*
            5
           / \
          3   6
         / \   \
        2   4   7
         */

        TreeNode root = createTree(new Integer[]{5, 3, 6, 2, 4, null, 7});
        deleteNode2(root, 3);
        System.out.print(root);
    }
}
