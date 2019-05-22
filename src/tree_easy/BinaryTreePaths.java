// Source : https://leetcode.com/problems/binary-tree-paths/
// Date   : 03/12/2017

/*************************************************************************************** 
 *
 * Given a binary tree, return all root-to-leaf paths.
 * 
 * For example, given the following binary tree:
 * 
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * 
 * All root-to-leaf paths are:
 * ["1->2->5", "1->3"]
 * 
 * Credits:
 * Special thanks to @jianchao.li.fighter for adding this problem and creating all test 
 * cases.
 *               
 ***************************************************************************************/
package Leetcode_Java.tree_easy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class BinaryTreePaths {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    private List<String> paths = new ArrayList();
//    使用全局变量paths来记录所有从根节点能到达的叶子节点
//    如果发现当前的根是个叶子节点，即左右子树均为空，那么就把当前路径加入到paths当中
//    如果左子树不为空，在已经走过的路径中加入当前节点值，并访问递归访问左子树
//    如果右子树不为空，在已经走过的路径中加入当前节点值，并访问递归访问右子树
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return paths;
        }
        buildPath("", root);
        return paths;
    }

    private void buildPath(String path, TreeNode root) {
        if (root.left == null && root.right == null) {
            paths.add(path + root.val);
        } else {
            if (root.left != null) {
                buildPath(path + root.val + "->", root.left);
            }
            if (root.right != null) {
                buildPath(path + root.val + "->", root.right);
            }
        }
    }
}
