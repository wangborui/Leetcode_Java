/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.tree_medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class InorderSuccessor {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    private static TreeNode prev = null;
    private static TreeNode successor = null;
//    这个题的难点在于中序遍历，并且在遍历每个节点的时候记录并查找上一个访问过的节点
//    如果上一个访问过的节点是p节点，那么当前节点就是我们要找的答案，不然的话继续中序访问整个二叉树
    static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inorder(root, p);
        return successor;
    }

    static void inorder(TreeNode root, TreeNode p) {
        if (root == null) {
            return;
        }

        inorder(root.left, p);

        if (prev == p) {
            successor = root;
//            注意：在找到了继任节点以后不能马上返回，因为如果返回的话之后所有节点的prev节点都不再会被更新了，是当前的prev节点
//            那么就是说继任节点会变成整个二叉树的根节点
            //return;
        }
        prev = root;

        inorder(root.right, p);
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
        TreeNode root = createTree(new Integer[]{5,3,6,2,4,null,null,1, null});
        inorderSuccessor(root,root.left.left.left);
    }
}
