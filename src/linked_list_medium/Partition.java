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
Develop 2 separate linked lists smallerTail and greaterEqualTail, where smallerTail < target value, greaterEqualTail >= target value
traverse head when head.val < target value, smallerTail.next = head, else greaterEqualTail.next = head
after finished, connect smallerTail and greaterEqualTail linkedlist
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
        //建立两个虚拟节点， smaller 和 greaterEqual 
        //分别储存原链表里所有小于x的节点和大于等于x的节点
        //遍历原链表，如果碰到小于x的节点，加入smaller链表中，不然加入greaterEqual节点中
        //完成遍历原链表后，将smaller 和 greaterEqual 链表合并，返回。
        ListNode smallerDummy = new ListNode(-1);
        ListNode greaterEqualDummy = new ListNode(-1);
        ListNode smallerTail = smallerDummy;
        ListNode greaterEqualTail = greaterEqualDummy;
        while(head != null) {
            if(head.val < x) {
                smallerTail.next = head;
                smallerTail = smallerTail.next;
            } else {
                greaterEqualTail.next = head;
                greaterEqualTail = greaterEqualTail.next;
            }
            head = head.next;
        }
        
        greaterEqualTail.next = null;
        smallerTail.next = greaterEqualDummy.next;
        return smallerDummy.next;
    }
}
