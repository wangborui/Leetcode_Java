// Source : https://oj.leetcode.com/problems/flatten-binary-tree-to-linked-list/
// Date   : 03/12/2017

/********************************************************************************** 
* 
* Given a binary tree, flatten it to a linked list in-place.
* 
* For example,
* Given
* 
*          1
*         / \
*        2   5
*       / \   \
*      3   4   6
* 
* The flattened tree should look like:
* 
*    1
*     \
*      2
*       \
*        3
*         \
*          4
*           \
*            5
*             \
*              6
* 
* 
* Hints:
* If you notice carefully in the flattened tree, each node's right child points to 
* the next node of a pre-order traversal.
*               
**********************************************************************************/

package Leetcode_Java.tree_medium;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class Flatten {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
//    首先先序遍历整个二叉树，并且把遍历结果储存到preorder数组里面
//    在preorder最后加一个null作为最后一个节点的右边节点
//    遍历所有在preorder里面的元素，把元素的左边设置成空，右边节点设置成下一个节点
            
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        List<TreeNode> preorder = new ArrayList();
        preorderHelper(root, preorder);
        preorder.add(null);

        for (int i = 0; i < preorder.size() - 1; i++) {
            TreeNode cur = preorder.get(i);
            cur.left = null;
            cur.right = preorder.get(i + 1);
        }
    }

    private void preorderHelper(TreeNode root, List<TreeNode> preorder) {
        if (root == null) {
            return;
        }
        preorder.add(root);
        preorderHelper(root.left, preorder);
        preorderHelper(root.right, preorder);
    }
}
