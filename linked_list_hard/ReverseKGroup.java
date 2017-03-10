// Source : https://oj.leetcode.com/problems/reverse-nodes-in-k-group/
// Date   : 03/10/2017

/********************************************************************************** 
* 
* Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
* 
* If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
* 
* You may not alter the values in the nodes, only nodes itself may be changed.
* 
* Only constant memory is allowed.
* 
* For example,
* Given this linked list: 1->2->3->4->5
* 
* For k = 2, you should return: 2->1->4->3->5
* 
* For k = 3, you should return: 3->2->1->4->5
* 
*               
**********************************************************************************/
package Leetcode_Java.linked_list_hard;

/**
 *
 * @author Borui Wang
 */
public class ReverseKGroup {

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public class Solution {
 /**
     * Reverse a link list between begin and end exclusively
     * an example:
     * a linked list:
     * Dummy
     *   |
     * (-1)->1->2->3->4->5->6
     *   |            |   
     * begin         end
     * after call begin = reverse(begin, end)
     * Dummy
     * |
     * 0->3->2->1->4->5->6
     *           |  |
     *       begin end
     * @return the reversed list's 'begin' node, which is the precedence of node end
     */
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || head.next == null || k == 1) {
                return head;
            }
            //建立虚拟节点，因为我们不确定在反转链表之后的开始节点
            ListNode dummy = new ListNode(-1);
            ListNode begin = dummy;
            dummy.next = head;

            int count = 0;
            //用while循环一直把链表开头往下面一个节点移动，当移动到k + 1个节点的时候，反转之前的k个节点
            while (head != null) {
                head = head.next;
                count++;
                if (count % k == 0) {
                    begin = reverseList(begin, head);
                }
            }
            return dummy.next;
        }
        //反转所有从开始到结尾节点链表里的所有节点，不包括开始和结尾节点
        private ListNode reverseList(ListNode begin, ListNode end) {
            ListNode swappedEnd = begin.next;
            ListNode cur = begin.next;
            ListNode prev = begin;

            while (cur != end) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            begin.next = prev;
            swappedEnd.next = end;
            return swappedEnd;
        }
    }
}
