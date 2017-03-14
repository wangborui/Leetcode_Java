/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode_Java.tree_medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class FindLeaves {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    //这个题目其实是“find binary tree max depth”的一个延伸
    //先建立一个数组 res，在数组里储存每一层的节点从左到右排序的数组
    //如果我们知道每个节点所在的高度是多少，我们找到在res中代表当前节点的数组 level，并且把当前节点加入level
    //要注意的地方是我们先访问左子树，再访问右子树，这样的话每一层的节点都可以是从左往右的排列在res相对应的level里面
    static List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res =  new ArrayList();
        if(root == null) {
            return res;
        }
        dfsHelper(root, res);
        return res;
    }
    static int dfsHelper(TreeNode root, List<List<Integer>> res) {
        if(root == null) {
            return -1;
        }
        int left = dfsHelper(root.left, res);
        int right = dfsHelper(root.right, res);
        int height = Math.max(left, right) + 1;
        if(res.size() == height) {
            res.add(new ArrayList());
        }
        res.get(height).add(root.val);
        root.left = root.right = null;
        return height;
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
        Integer[] in = {1,2,3,4,5,null, null};
        TreeNode root = createTree(in);
        System.out.println(findLeaves(root));
    }
}
