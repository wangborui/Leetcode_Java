/*
Source: https://leetcode.com/problems/sort-list/
Date: 12/23/2016
********************************************************************************
Sort a linked list in O(n log n) time using constant space complexity.
********************************************************************************

Solution:
Merge sort, uses constant space because linkedlist is only a pointer, but array uses linear space
Quick sort, always uses constant space and sorts array in place
 */
package Leetcode_Java.linked_list_medium;

/**
 *
 * @author Borui Wang
 */
public class SortList {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
//{1,4,5,2,7,0,2}

    static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMid(head);
        //sort list from mid.next to the end
        ListNode right = sortList(mid.next);
        //cut off mid and start sort left from head to mid
        mid.next = null;
        ListNode left = sortList(head);
        return merge(left, right);
    }

    static ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static ListNode merge(ListNode left, ListNode right) {
        //use dummy because we do not know the start of the list, either right first or left first
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        //difference between right and left half size is at most one
        while (left != null && right != null) {
            if (left.val < right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        if(left != null) {
            tail.next = left;
        } 
        if(right != null) {
            tail.next = right;
        }
        return dummy.next;

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

    public static void main(String[] args) {
        ListNode root = createList(new int[]{1, 4, 5, 2, 7, 0, 2});
        sortList(root);
        printList(root);
    }
}
