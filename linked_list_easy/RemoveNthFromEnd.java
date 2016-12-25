/*
Source: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
********************************************************************************
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
********************************************************************************
 */
package Leetcode_Java.linked_list_easy;

import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Borui Wang
 */
public class RemoveNthFromEnd {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
 
    static ListNode removeNthFromEnd(ListNode head, int n) {
        //use dummy node because we are not sure what the start element is
        //1->null, n = 1, head = null, or 1->2->null, n = 1, head = 1;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        //move fast pointer n spaces ahead of slow pointer
        for(int i = 0; i < n; i++) {
            if(fast == null) {
                return null;
            }
            fast = fast.next;
        }
        //move fast and slow pointers together one at a time
        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
 

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode result = removeNthFromEnd(head, 3);
        while (result != null) {
            StdOut.println(result.val);
            result = result.next;
        }

    }
}
