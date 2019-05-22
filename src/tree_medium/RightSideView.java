// Source : https://leetcode.com/problems/binary-tree-right-side-view/
// Date   : 03/12/2017

/********************************************************************************** 
 * 
 * Given a binary tree, imagine yourself standing on the right side of it, return 
 * the values of the nodes you can see ordered from top to bottom.
 * 
 * For example:
 * Given the following binary tree,
 * 
 *      1            <---
 *    /   \
 *   2     3         <---
 *    \     \
 *     5     4       <---
 * 
 * You should return [1, 3, 4].
 * 
 * Credits:Special thanks to @amrsaqr for adding this problem and creating all test cases.
 *               
 **********************************************************************************/
package Leetcode_Java.tree_medium;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class RightSideView {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    //二叉树顶端算作第0层，先递归的访问当前节点的右子树，再递归的访问当前节点的左子树
    //用arraylist来记录所有最右边边上的节点
    //在递归的过程中，如果层数等于arraylist的长度，就把当前节点放入arraylist，再递归访问右子树和左子树
    //因为我们所有的访问都是从右子树开始的，所以如果碰到一层的层数是arraylist的长度，就证明我们已经碰到了一个新的未被记录的右边节点
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList();
        helper(res, root, 0);
        return res;
    }

    private void helper(List<Integer> res, TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level == res.size()) {
            res.add(root.val);
        }
        helper(res, root.right, level + 1);
        helper(res, root.left, level + 1);
    }
}
