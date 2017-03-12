// Source : https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
// Date   : 03/11/2017
/********************************************************************************** 
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
 **********************************************************************************/
package Leetcode_Java.tree_medium;

/**
 *
 * @author Borui Wang
 */
public class Connect {

    public class TreeLinkNode {

        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }
//    这道题里的重点是理解题意
//    我们被给予了一个当前节点，要把节点下面一个层次的所有节点从左到右连接起来
//    用while循环来把当下节点的下层节点全部连接起来，每次连接完毕当前节点往左下移，直到当前节点为空
//    在上面while循环的时候，我们用另外一个while循环来把当前节点下面所有的节点都连接起来
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        TreeLinkNode levelStart = root;
        while (levelStart != null) {
            TreeLinkNode cur = levelStart;

            while (cur != null) {
                if (cur.left != null) {
                    cur.left.next = cur.right;
                }
                if (cur.right != null && cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            levelStart = levelStart.left;
        }
    }
}
