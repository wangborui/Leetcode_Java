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

Note: if node -> 2, doing node =  null; would change node to null but 2 still exists and is the children of its parent
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
    
    //find the node
    //if node.left is null node = node.right
    //else node.val = node.left.val, delete(node.left);
    //fails test case [5,3,6,2,4,null,7], key 3
    static TreeNode deleteNode(TreeNode root, int key) {
        //find node
        TreeNode node = findNode(root, key);
        if (node != null) {
            delete(node);
        }
        return root;
    }

    static TreeNode findNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        } else if (root.val == key) {
            return root;
        } else if (root.val < key) {
            return findNode(root.right, key);
        } else {
            return findNode(root.left, key);
        }
    }

    static void delete(TreeNode node) {
        if (node.left == null) {
            //sets pointer of node to null, but the original values pointed by node does not change
            //if node -> 2, doing node =  null; would change node to null but 2 still exists and is the children of its parent
            node = null;
        } else {
            node.val = node.left.val;
            delete(node.left);
        }
    }
    //errors out under the following condition
    //tree becomes, and we delete node 3
    /*
            3                  1 
           / \                 \ \
          1   4  ==>            2 4
           \   
            2   
    Solution: find either the max value in left tree or min value in right tree, change deleting node value to that value, then delete either the min value in right subtree or max value in left tree
    */
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
