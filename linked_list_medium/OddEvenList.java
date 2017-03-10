// Source : https://oj.leetcode.com/problems/rotate-list/
// Date   : 03/10/2017

/********************************************************************************** 
* 
* Given a list, rotate the list to the right by k places, where k is non-negative.
* 
* For example:
* Given 1->2->3->4->5->NULL and k = 2,
* return 4->5->1->2->3->NULL.
*               
**********************************************************************************/
package Leetcode_Java.linked_list_medium;

/**
 *
 * @author Borui Wang
 */
public class OddEvenList {
    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        //找到原链表第一个单数节点，和第一个双数节点，并记录双数开始节点
        //把单数节点连接到下一个单数节点，双数节点连接到下一个双数节点
        //将单数节点结尾连接到双数节点开头，并返回原链表开始节点
        
        while (even != null && even.next != null) {
            odd.next = even.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
