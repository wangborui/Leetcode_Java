/*
Source: https://leetcode.com/problems/reverse-linked-list/
Date: 12/23/2016

********************************************************************************
Reverse a singly linked list.
********************************************************************************

Note: esatblish 2 nodes, prev and cur/
 */
package Leetcode_Java.linked_list_easy;

/**
 *
 * @author Borui Wang
 */
public class ReverseList {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static ListNode reverseListIterative(ListNode head) {
        ListNode prev = null;
        while(head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        //prev is the new reversed head
        return prev;
    }

    static ListNode reverseListRecursive(ListNode head) {
          if(head == null || head.next == null) {
              return head;
          }
          //assume reversed head reversed all nodes after head node, and is the head
          ListNode reversedHead = reverseListRecursive(head.next);
          head.next.next = head;
          head.next = null;
          return reversedHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        ListNode result = reverseListIterative(head);
    }
}
