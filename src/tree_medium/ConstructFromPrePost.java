package tree_medium;

import java.util.Arrays;

/**
 * June 8, 2019
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal
 * Preorder and postorder build a tree
 *      1
 *     /\
 *    2  3
 *  / \  /\
 * 4  5 6  7
 *
 * pre order   [1] + [2, 4, 5] + [3, 6, 7]
 * post order  [4, 5, 2] + [6, 7, 3] + [1]
 *
 * Analysis:
 * preorder traversal : root -> left -> right
 * postorder traversal: left -> right -> root
 *
 * The first element in preorder array pre[0] must be the root of entire tree
 * The last element in postorder array post[n - 1] must be the root of entire tree
 * The second element in preorder array pre[1] must be the root of left sub tree
 * find index of post[pre[1]] in postorder array as index, then all elements from
 *      post[0 : index] is left subtree,
 *      post[(index + 1) : (n - 2)] is right subtree;
 *
 * Recursively build the tree;
 * Time O(N^2)
 */
public class ConstructFromPrePost {

  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

    public TreeNode constructFromPrePost(int[] preOrder, int[] postOrder) {
        if (preOrder == null || preOrder.length == 0) {
            return null;
        }
        int n = preOrder.length;
        int val = preOrder[0];
        if (n == 1){
            return new TreeNode(val);
        }

        TreeNode root = new TreeNode(val);
        int leftSubTreeRootIndex = 0;

        for (int i = 0; i < n; i++) {
            if (postOrder[i] == preOrder[1]) {
                leftSubTreeRootIndex = i;
                break;
            }
        }

        root.left = constructFromPrePost(Arrays.copyOfRange(preOrder, 1, leftSubTreeRootIndex + 2),
                Arrays.copyOfRange(postOrder, 0, leftSubTreeRootIndex + 1));
        root.right = constructFromPrePost(Arrays.copyOfRange(preOrder, leftSubTreeRootIndex + 2, n),
                Arrays.copyOfRange(postOrder, leftSubTreeRootIndex + 1, n - 1));
        return root;
    }

}
