/*
Source : https://leetcode.com/problems/reorder-list/
Date   : 01/21/2017

********************************************************************************
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
********************************************************************************
 */
package Leetcode_Java.linked_list_medium;

/**
 *
 * @author Borui Wang
 */
public class ReorderList {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode mid = findMid(head);
        ListNode reversedHead = reverse(mid.next);
        mid.next = null;
        merge(head, reversedHead);
    }

    static ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    static void merge(ListNode head, ListNode reversedHead) {
        //size diff between head and revHead is no more than 1
        ListNode tail = new ListNode(-1);
        while(head != null && reversedHead != null) {
            ListNode headNext = head.next;
            ListNode revNext = reversedHead.next;
            tail.next = head;
            tail.next.next = reversedHead;
            tail = tail.next.next;
            head = headNext;
            reversedHead = revNext;
        }
        if(head != null) {
            tail.next = head;
        }
        if(reversedHead != null) {
            tail.next = reversedHead;
        }
    }

    static ListNode createList(int[] nums) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        for (int n : nums) {
            tail.next = new ListNode(n);
            tail = tail.next;
        }
        return dummy.next;
    }

    static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
    public static void main(String [] args) {
        ListNode head = createList(new int[]{1,2,3});
        ListNode test0 = createList(new int[]{});
        ListNode test1 = createList(new int[]{1});
        ListNode test2 = createList(new int[]{1,2});
        ListNode test3 = createList(new int[]{1,2,3});
        ListNode test4 = createList(new int[]{1,2,3,4});
        ListNode test5 = createList(new int[]{1,2,3,4,5});
         
        reorderList(head);
        printList(head);
        //System.out.print(findMid(test5).val);
    }
}
