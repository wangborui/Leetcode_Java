/*
Source:https://leetcode.com/problems/partition-list/
Date:12/23/2016
********************************************************************************
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.

********************************************************************************

Solution:
Develop 2 separate linked lists left and right, where left < target value, right >= target value
traverse head when head.val < target value, left.next = head, else right.next = head
after finished, connect left and right linkedlist
 */
package Leetcode_Java.linked_list_medium;

/**
 *
 * @author Borui Wang
 */
public class Partition {
    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public ListNode partition(ListNode head, int x) {
        //if head is null, it will not enter while loop
        ListNode leftDummy = new ListNode(-1);
        ListNode rightDummy = new ListNode(-1);
        ListNode left = leftDummy;
        ListNode right = rightDummy;
        while(head != null) {
            if(head.val < x) {
                left.next = head;
                left = left.next;
            } else {
                right.next = head;
                right = right.next;
            }
            head = head.next;
        }
        
        right.next = null;
        left.next = rightDummy.next;
        return leftDummy.next;
    }
}
