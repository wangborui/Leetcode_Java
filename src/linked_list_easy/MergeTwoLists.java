/*
Source: https://leetcode.com/problems/merge-two-sorted-lists/
Date: 12/24/2016
********************************************************************************
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
********************************************************************************
 */
package Leetcode_Java.linked_list_easy;

/**
 *
 * @author Borui Wang
 */
public class MergeTwoLists {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    //recursive solution
    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoListsRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecursive(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoListsIterative(ListNode l1, ListNode l2) {
        //use dummy node because we do not know the start of the list
        //建立虚拟节点和虚拟结尾，因为我们不知道合并后的链表开头是什么
        //用while loop来遍历第一个链表和第二个链表，l1 和 l2，并对比当前两个链表开头节点大小
        //将虚拟结尾指向两个链表当中较小的节点，后移虚拟结尾，后移较小节点链表
        //当l1 和 l2 有一个为空的时候，将另一个合并到虚拟结尾后面
        //返回虚拟节点的下一个节点
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if (l1 == null) {
            tail.next = l2;
        }
        if (l2 == null) {
            tail.next = l1;
        }
        return dummy.next;
    }
}
