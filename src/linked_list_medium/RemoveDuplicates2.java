/*
Source: http://www.lintcode.com/en/problem/remove-duplicates-from-sorted-list-ii/
Date: 12/23/2016

********************************************************************************
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Have you met this question in a real interview? Yes
Example
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
********************************************************************************

Note:
use dummy node, because if we have  1->1->1->2->3, return 2->3. meaning that we are deleting the first node
 */
package Leetcode_Java.linked_list_medium;

/**
 *
 * @author Borui Wang
 */
public class RemoveDuplicates2 {
     static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
     //我们需要使用虚拟链表，因为在删除后我们不知道开始节点是什么
     //我们用walker 节点指向虚拟节点，注意walker节点总是指向不会被删除的节点
     //每次遍历的时候，我们需要查看walker节点下面的两个节点，如果这两个节点其中一个是空，结束访问返回
     //如果两个节点的值都是一样的话，我们需要记录下这个值，然后移动walker的下一个指针到不是这个值的节点
     //如果walker的下两个节点值不一样的话，我们需要把walker向后移动一位
    static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head = dummy;
        //invariant: head is always pointing to non-repeated elements
        while(head.next != null && head.next.next != null) {
            if(head.next.val == head.next.next.val) {
                int val = head.next.val;
                while(head.next != null && head.next.val == val) {
                    head.next = head.next.next;
                }
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }
    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next = new ListNode(1);
        deleteDuplicates(a);
    }
}
