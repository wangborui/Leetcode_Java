// Source : https://oj.leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
// Date   : 03/13/2017

/********************************************************************************** 
* 
* Given inorder and postorder traversal of a tree, construct the binary tree.
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
public class BuildTree2 {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
//    这个题的思路和“给定中序遍历和前序遍历恢复二叉树”的思路是一样的
//    我们知道在后续遍历里面，最后一个访问的节点必定是整个二叉树的根节点，我们找到根部节点
//    再到中序遍历中找到这个根部节点的下标，我们知道在中序遍历中小于这个下标的所有元素代表需要恢复二叉树的左子树，大于是右子树部分
//    在我们确定了这个思路以后，把树的根节点找到，储存
//    再递归的完成下面树的建立就可以了
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(postorder.length - 1, 0, inorder.length - 1, inorder, postorder);
    }
    
    private TreeNode helper(int pStart, int inStart, int inEnd, int[] inorder, int[] postorder) {
        if (pStart < 0 || inStart > inEnd) {
            return null;
        }
        //find root index
        int rootVal = postorder[pStart];
        int inIndex = inStart;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                inIndex = i;
                break;
            }
        }

        //build tree
        TreeNode root = new TreeNode(rootVal);
        root.right = helper(pStart - 1, inIndex + 1, inEnd, inorder, postorder);
        root.left = helper(pStart - (inEnd - inIndex + 1), inStart, inIndex - 1, inorder, postorder);
        return root;
    }
}
