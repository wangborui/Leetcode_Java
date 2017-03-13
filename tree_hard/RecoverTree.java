// Source : https://oj.leetcode.com/problems/recover-binary-search-tree/
// Date   : 03/12/2017
/**
 * ********************************************************************************
 *
 * Two elements of a binary search tree (BST) are swapped by mistake.
 *
 * Recover the tree without changing its structure.
 *
 * Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 *
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 *
 * OJ's Binary Tree Serialization:
 *
 * The serialization of a binary tree follows a level order traversal, where '#' signifies
 * a path terminator where no node exists below.
 *
 * Here's an example:
 *
 *    1
 *   / \
 *  2   3
 *     /
 *    4
 *     \
 *      5
 *
 * The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
 *
 *
 *********************************************************************************
 */
/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
//
// We can convert the BST to a sorted array,  then we can find the two nodes which missed the order.
//
// To cover the BST to sorted array, we needn't use an extra array, we just traverse the tree in order.
//  
//                   8
//           _______/ \_______
//          /                 \
//         4                  12
//      __/ \__             __/ \__
//     /       \           /       \
//    2         6        10        14
//   / \       / \       / \       / \
//  1   3     5   7     9  11    13  15
//  
//  
package Leetcode_Java.tree_hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class RecoverTree {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    private static TreeNode first = null;
    private static TreeNode second = null;
    private static TreeNode prev = null;

    static void recoverTree(TreeNode root) {
        inorder(null, root);

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
//    中序遍历整个BST，我们可以得到一个排序数组
//    但是我们不需要储存整个数组，只需要在遍历的时候对比当前节点和之前的节点
    static void inorder(TreeNode prev, TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(prev, root.left);

        if (prev != null && prev.val > root.val) {
            if (first == null) {
                first = prev;
            }
            //每次找到有一个节点错位了，就把第二个错位的节点更新一次
            //因为如果整棵树只有有两个节点，并且两个节点是错位的话我们需要在第一次访问的时候就设置第二个节点
            second = root;
        }
        //每次处理完成一个节点就把此节点设置成下个节点的上一个节点
        prev = root;
        inorder(root, root.right);
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
    public static void main(String [] args) {
        TreeNode root = createTree(new Integer[]{0,1, null});
        recoverTree(root);
    }
}
