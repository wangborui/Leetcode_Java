// Source : https://oj.leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
// Date   : 03/13/2017

/********************************************************************************** 
* 
* Follow up for problem "Populating Next Right Pointers in Each Node".
* What if the given tree could be any binary tree? Would your previous solution still work?
* 
* Note:
* You may only use constant extra space.
* 
* For example,
* Given the following binary tree,
* 
*          1
*        /  \
*       2    3
*      / \    \
*     4   5    7
* 
* After calling your function, the tree should look like:
* 
*          1 -> NULL
*        /  \
*       2 -> 3 -> NULL
*      / \    \
*     4-> 5 -> 7 -> NULL
* 
*               
**********************************************************************************/

package Leetcode_Java.tree_medium;

/**
 *
 * @author Borui Wang
 */
public class Connect2 {

    public class TreeLinkNode {

        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }
//    这个题的意思其实是“level order traversal”只是我们不用队列了，因为本题的每个元素都有一个next 指针，相当于代替了队列的性质
//    我们在访问每一层的时候添加一个levelDummy，levelDummy.next 指向下一层最左边的元素，随着下面的访问会逐渐增加长度并记录下一层所有不为空的节点
//    建立一个levelTail 记录在访问完了下一层后最右边的元素
//    最后建立一个cur 记录下一层当中正在访问的元素，当cur 为空的时候我们就访问了下一层所有的元素了
//    如果levelDummy.next 为空的话，说明我们已经没有需要访问的下一层元素了，返回
    public void connect(TreeLinkNode root) {
        TreeLinkNode levelDummy = new TreeLinkNode(-1);
        levelDummy.next = root;

        while (levelDummy.next != null) {
            TreeLinkNode levelTail = levelDummy;
            TreeLinkNode cur = levelDummy.next;
            levelDummy.next = null;

            for (; cur != null; cur = cur.next) {
                if (cur.left != null) {
                    levelTail.next = cur.left;
                    levelTail = levelTail.next;
                }

                if (cur.right != null) {
                    levelTail.next = cur.right;
                    levelTail = levelTail.next;
                }
            }
        }
    }
}
