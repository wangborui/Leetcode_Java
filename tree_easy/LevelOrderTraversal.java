/*
Source: https://leetcode.com/problems/binary-tree-level-order-traversal/

********************************************************************************
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
********************************************************************************

Solution:
1. use 2 queues
2. use 1 queue with dummy nodes
3. use 1 queue (code below)

 */
package Leetcode_Java.tree_easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class LevelOrderTraversal {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList();
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);

        while (!queue.isEmpty()) {
            //size needs to be kept in the begining of the loop, because size changes as more elements are polled out of queue
            int size = queue.size();
            List<Integer> level = new ArrayList();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    level.add(node.val);
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            if (!level.isEmpty()) {
                levels.add(level);
            }
        }
        return levels;
    }
}
