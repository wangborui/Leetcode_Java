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
