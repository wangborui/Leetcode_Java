// Source : https://oj.leetcode.com/problems/validate-binary-search-tree/
// Date   : 2016-12-21

/********************************************************************************** 
* 
* Given a binary tree, determine if it is a valid binary search tree (BST).
* 
* Assume a BST is defined as follows:
* 
* The left subtree of a node contains only nodes with keys less than the node's key.
* The right subtree of a node contains only nodes with keys greater than the node's key.
* Both the left and right subtrees must also be binary search trees.
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
**********************************************************************************/
package Leetcode_Java.tree_medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class ValidateBinarySearchTree {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    //首先用中序遍历访问整个二叉查找树，记录访问结果
    //检查访问的结果是否已排好序
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList();
        Stack<TreeNode> stack = new Stack();
        TreeNode cur = root;
        //inorder traversal 
        while (cur != null || !stack.isEmpty()) {
             if (cur != null) {
                stack.push(cur);
                cur = cur.left;
             } else {
                TreeNode temp = stack.pop();
                list.add(temp.val);
                cur = temp.right;
            }
        }

        //validate list is sorted
        for (int i = 0; i < list.size(); i++) {
            if (i != 0 && list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
