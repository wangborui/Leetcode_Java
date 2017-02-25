// Source : https://oj.leetcode.com/problems/rotate-list/
// Date   : 02/24/2017

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
public class RotateRight {

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode walker = head;
        //find length of entire list
        while (walker.next != null) {
            n++;
            walker = walker.next;
        }
        //connect tail to head
        walker.next = head;
        //calculate how many steps to walk before cutting
        k = n - k % n;
        walker = dummy;
        for (int i = 0; i < k; i++) {
            walker = walker.next;
        }

        //start rotating and cutting
        dummy.next = walker.next;
        walker.next = null;

        return dummy.next;
    }
}
