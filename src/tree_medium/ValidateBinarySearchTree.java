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
 * June 10, 2019
 * https://leetcode.com/problems/validate-binary-search-tree/
 * @author Borui Wang
 *
 * Solutions:
 * 1. Iteratively tranverse BST and see if elements are sorted in order
 * 2. preorder traversal of the BST and validate
 *    Analysis:
 *      A BST is valid if left subtree root is smaller than root, and right subtree root is larger than root.
 *      Recursion will work if we compare the value of current ROOT, and respective subtrees root, it works in this case
 *       1
 *      /\
 *     0  2
 *
 *     However, this approach will fail in this case, because the leaf node(2) is smaller than tree root(5).
 *       5
 *     /  \
 *    1    9
 *        /  \
 *(failed)2  10
 *
 *
 *   Solution:
 *      To resovle problem above, we can carry two variables upper and lower limit when preorder traversing the tree
 *                                       5(lowerlimit = null, upperlimit = null)
 *                                     /  \
 *(lowerlimit = null, upperlimit = 5) 1    9(lowerlimit = 5, upperlimit = null)
 *                                       /  \
 *      (lowerlimit = 5, upperlimit = 9)2  10 (lowerlimit = 9, upperlimit = null)
 *
 *   Implementation:
 *      Recursively traverse the tree and compare each node with its upper and lower limit to validate the BST
 *   Complexity:
 *      Time complexity : O(N) since we visit each node exactly once.
 *      Space complexity : O(1)
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

    /**
     * Solution 2 Implementations
     * @param root
     * @return
     */
    public boolean isValidBSTSolution2(TreeNode root) {
        return preorder(root, null, null);
    }

    public boolean preorder(TreeNode root, Integer lowerLimit, Integer upperLimit) {
        if(root == null) {
            return true;
        }

        int val = root.val;
        if(lowerLimit != null && val <= lowerLimit) {
            return false;
        }
        if(upperLimit != null && val >= upperLimit) {
            return false;
        }

        return preorder(root.left, lowerLimit, val) && preorder(root.right, val, upperLimit);
    }
}
