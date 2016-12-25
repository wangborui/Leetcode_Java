/*
Source: https://leetcode.com/problems/merge-two-sorted-lists/
Date: 12/24/2016
********************************************************************************
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
********************************************************************************
 */
package Leetcode_Java.linked_list_easy;

/**
 *
 * @author Borui Wang
 */
public class MergeTwoLists {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoListsRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecursive(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoListsIterative(ListNode l1, ListNode l2) {
        //use dummy node because we do not know the start of the list
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if (l1 == null) {
            tail.next = l2;
        }
        if (l2 == null) {
            tail.next = l1;
        }
        return dummy.next;
    }
}
