/*
Source: http://www.lintcode.com/en/problem/remove-duplicates-from-sorted-list-ii/
Date: 12/23/2016

********************************************************************************
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Have you met this question in a real interview? Yes
Example
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
********************************************************************************

Note:
use dummy node, because if we have  1->1->1->2->3, return 2->3. meaning that we are deleting the first node
 */
package Leetcode_Java.linked_list_medium;

/**
 *
 * @author Borui Wang
 */
public class RemoveDuplicates2 {
     static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head = dummy;
        //invariant: head is always pointing to non-repeated elements
        while(head.next != null && head.next.next != null) {
            if(head.next.val == head.next.next.val) {
                int val = head.next.val;
                while(head.next != null && head.next.val == val) {
                    head.next = head.next.next;
                }
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next = new ListNode(1);
        deleteDuplicates(a);
    }
}
