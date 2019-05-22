/*
Source: https://leetcode.com/problems/reverse-linked-list/
Date: 12/23/2016

********************************************************************************
Reverse a singly linked list.
********************************************************************************

Note: esatblish 2 nodes, prev and cur/
Invariant: after reversal, cur will be null, and return prev as new head
 */
package Leetcode_Java.linked_list_medium;

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
        }//每一行后半部分是下一行前半部分， 33行到36行是核心
        //prev is the new reversed head
        return prev;
    }

    static ListNode reverseListRecursive(ListNode head) {
          if(head == null || head.next == null) {
              return head;
          }
          //假设头后面的部分都被翻转了，并且我们拥有被翻转链表部分的头
          //assume reversed head reversed all nodes after head node, and is the head
          ListNode reversedHead = reverseListRecursive(head.next);
          //把现在的头变成已经被翻转部分的尾部
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
