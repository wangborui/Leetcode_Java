// Source : https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/?tab=Description
// Date   : 03/11/2017
/**
 * ********************************************************************************
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the
 * tree.
 *
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is
 * defined between two nodes v and w as the lowest node in T that has both v and w as
 * descendants (where we allow a node to be a descendant of itself).”
 *
 *         _______3______
 *        /              \
 *     ___5__          ___1__
 *    /      \        /      \
 *    6      _2       0       8
 *          /  \
 *          7   4
 *
 * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example
 * is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according
 * to the LCA definition.
 *
 *
 *
 *********************************************************************************
 */
package Leetcode_Java.tree_medium;

import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class VerifyPreorder {
//我们仿造先序遍历的顺序来访问整个数组，先序遍历是中左右
//利用一个栈，记录所有遍历过的“左子树”值
//在碰到一个大于栈顶元素的时候（当前访问元素），说明我们碰到了一个在BST右子树的值
//现在我们需要一直弹出栈的元素，找出栈里最大小于当前元素的节点，设置为low， 将当前元素进栈
//low值代表了说我们以后访问的节点值都不能小于low值，因为后面访问左右值不然是当前节点的子节点（大于low）或者是另外一个子树也是大于low，不然这个数组就不是BST的先序遍历
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length <= 1) {
            return true;
        }

        int low = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack();
        for (int p : preorder) {
            if (p < low) {
                return false;
            }
            while (!stack.isEmpty() && p > stack.peek()) {
                low = stack.pop();
            }
            stack.push(p);
        }
        return true;
    }
}
