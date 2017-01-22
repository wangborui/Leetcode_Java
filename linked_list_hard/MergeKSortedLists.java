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
     static ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) {
            return null;
        }
        
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        int n = lists.length;
        
        PriorityQueue<ListNode> pq = new PriorityQueue(n, new Comparator<ListNode>() {
           @Override
           public int compare(ListNode a, ListNode b) {
               return a.val - b.val;
           } 
        });
        for(ListNode list : lists) {
            if(list != null) {
                pq.add(list);
            }
        }
        
        while(!pq.isEmpty()) {
            ListNode smallest = pq.poll();
            tail.next = smallest;
            tail = tail.next;
            if(smallest.next != null) {
                pq.add(smallest.next);
            }
        }
        return dummy.next;
    }
}
