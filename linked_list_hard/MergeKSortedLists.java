/*
Source: https://leetcode.com/problems/merge-k-sorted-lists/
********************************************************************************
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 
********************************************************************************
 */
package Leetcode_Java.linked_list_hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Borui Wang
 */
public class MergeKSortedLists {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    private Comparator<ListNode> comparator = new Comparator<ListNode>() {
        public int compare(ListNode left, ListNode right) {
            //if left is null left is Integer.MAX_VALUE same for right
            return left.val - right.val;
        }
    };

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        PriorityQueue<ListNode> heap = new PriorityQueue(lists.length, comparator);

        for (ListNode node : lists) {
            if (node != null) {
                heap.add(node);
            }
        }

        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {
                heap.add(node.next);
            }
        }

        return dummy.next;
    }
}
