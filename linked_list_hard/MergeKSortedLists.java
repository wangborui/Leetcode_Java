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
    //1.)建立虚拟链表，因为k个链表合并后的开始节点是未知的
    //2.)建立一个最小堆，把所有k个链表都放入堆中
    //3.)取出堆顶最小节点，并把这个节点放在虚拟链表中。
    //4.)如果最小节点的下一个节点不为空，把下一个节点放入堆中
    //重复3.）和4.）直到堆为空，返回虚拟链表开头节点
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
