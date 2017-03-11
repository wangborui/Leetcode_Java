/*
Source: https://leetcode.com/problems/remove-duplicates-from-sorted-list/
Date: 12/23/2016

********************************************************************************
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
********************************************************************************

Note: 
No need to use dummy nodes because we are not possibly deleting the first node of the list
Make sure walker is not null. if walker is null, then walker.val and walker.next will throw exception
 */
package Leetcode_Java.linked_list_medium;


/**
 *
 * @author Borui Wang
 */
public class RemoveDuplicates {

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
 
    //this solution only keeps one pointer, if that is equal to its next, 
    //我们不需要使用虚拟节点，因为在取掉重复节点后，我们的开头节点任然是原链表的开始节点（题意）
    //使用walker节点来遍历整个链表，如果walker的后面节点和walker的值是一样的，那么我们把walker的下个节点指向下下个节点
    //如果walker节点的下个节点值和walker值不同，walker则向后移动一个节点
    public ListNode deleteDuplicatesOnePointer(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode walker = head;

        while (walker.next != null) {
            if (walker.val == walker.next.val) {
                walker.next = walker.next.next;
            } else {
                walker = walker.next;
            }
        }
        return head;
    }
}
