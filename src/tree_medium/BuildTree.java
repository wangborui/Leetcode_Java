// Source : https://oj.leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
// Date   : 03/12/2017

/********************************************************************************** 
* 
* Given preorder and inorder traversal of a tree, construct the binary tree.
* 
* Note:
* You may assume that duplicates do not exist in the tree.
* 
*               
**********************************************************************************/
package Leetcode_Java.tree_medium;

/**
 *
 * @author Borui Wang
 */
public class BuildTree {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }
//我们拥有两个数组，一个是二叉树的前序遍历，另外一个是二叉树的中序遍历
//前序遍历的第一个元素pre【0】必定是我们整个二叉树的根
//然后我们利用这个根元素来访问中序遍历数组并找到根在中序遍历中的下标假设是in【5】
//下面我们就可以确定几个事情：
//    1.）in【5】必定是整棵树的根部
//    2.）从in【0】到in【4】一定是整棵树的左子树部分，从in【6】到n 一定是整棵树的右子树部分
//    3.）相对应的是pre【1】必定是左子树的开始根部节点， pre【0 + （5-0）+ 1】 必定是右子树开始的根部节点
//根据以上条件我们可以递归的建立出整颗树
    private TreeNode helper(int pStart, int iStart, int iEnd, int[] preorder, int[] inorder) {
        //reached a null node 
        if (pStart >= preorder.length || iStart > iEnd) {
            return null;
        }

        int rootVal = preorder[pStart];
        TreeNode root = new TreeNode(rootVal);
        int inIndex = iStart;
        for (int i = iStart; i <= iEnd; i++) {
            if (inorder[i] == rootVal) {
                inIndex = i;
                break;
            }
        }

        root.left = helper(pStart + 1, iStart, inIndex - 1, preorder, inorder);
        root.right = helper(pStart + inIndex - iStart + 1, inIndex + 1, iEnd, preorder, inorder);

        return root;
    }
}
