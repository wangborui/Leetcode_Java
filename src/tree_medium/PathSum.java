// Source : https://oj.leetcode.com/problems/path-sum-ii/
// Date   : 03/13/2017

/********************************************************************************** 
* 
* Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
* 
* For example:
* Given the below binary tree and sum = 22,
* 
*               5
*              / \
*             4   8
*            /   / \
*           11  13  4
*          /  \    / \
*         7    2  5   1
* 
* return
* 
* [
*    [5,4,11,2],
*    [5,8,4,5]
* ]
* 
*               
**********************************************************************************/

package Leetcode_Java.tree_medium;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class PathSum {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
//    这道题的难点在于深度优先搜索，和在搜索的时候我们先把节点放到搜索结果里面，在搜索结束的时候我们把节点从搜索结果里面取出来
//    如果当前根节点为空，我们可以直接返回一个空的搜索结果
//    如果访问到一个叶子节点，而且当前节点的值正好是我们要找的sum值，我们把当前节点和之前的节点加入搜索值里面返回
//    不然的话，继续搜索左右子树
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> paths = new ArrayList();
        if (root == null) {
            return paths;
        }
        dfsHelper(paths, new ArrayList(), root, sum);
        return paths;
    }

    private void dfsHelper(List<List<Integer>> paths, List<Integer> path, TreeNode root, int remain) {
        path.add(root.val);
        if (root.left == null && root.right == null && remain == root.val) {
            paths.add(new ArrayList(path));
        } else {
            if (root.left != null) {
                dfsHelper(paths, path, root.left, remain - root.val);
            }
            if (root.right != null) {
                dfsHelper(paths, path, root.right, remain - root.val);
            }
        }
        path.remove(path.size() - 1);
    }
}
