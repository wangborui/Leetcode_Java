// Source : https://oj.leetcode.com/problems/symmetric-tree/
// Date   : 03/12/2017

/********************************************************************************** 
* 
* Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
* 
* For example, this binary tree is symmetric:
* 
*     1
*    / \
*   2   2
*  / \ / \
* 3  4 4  3
* 
* But the following is not:
* 
*     1
*    / \
*   2   2
*    \   \
*    3    3
* 
* Note:
* Bonus points if you could solve it both recursively and iteratively.
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

/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
package Leetcode_Java.tree_easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class IsSymmetric {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    //recursive solution found
    //递归的来判断一个二叉树是否是对称的，如果根节点是空，那么此树肯定对称的
    //不然分别判断其左子树和右子树是不是对称的，我们需要一个辅助函数
    public boolean isSymmetricRecursive(TreeNode root) {
        if(root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }
    private boolean isMirror(TreeNode left, TreeNode right) {
        //如果左右子树根同时为空，返回他们是对称的
        if(left == null && right == null) {
            return true;
        }
        //如果左右子树只有其中一个为空，返回他们是不对称的
        if(left == null || right == null) {
            return false;
        }
        //如果左右子树的根节点值不同的话，返回他们不是对称的
        if(left.val != right.val) {
            return false;
        }
        //递归的检查左子树的左子树和右子树的右子树是否对称，
        //递归的减产左子树的右子树和右子树的左子树是否对称
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }
    //迭代做法：
    //假设我们的二叉树有两个，分别在队列中加入这两个二叉树的根节点
    //然后一层层的访问这两个树的节点，对比是否对称的
    public boolean isSymmetric(TreeNode root){
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        q.add(root);
        while(!q.isEmpty()){
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if(t1 == null && t2 == null) continue;
            if(t1 == null || t2 == null) return false;
            if(t1.val != t2.val) return false;
            //对比第一个树的右子树和第二个树的左子树是否对称
            q.add(t1.right);
            q.add(t2.left);
            //对比第一个树的左子树和第二个树的右子树是否对称
            q.add(t1.left);
            q.add(t2.right);
        }
        return true;
    }
}
