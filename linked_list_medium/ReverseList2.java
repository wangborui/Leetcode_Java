/*
Source : https://leetcode.com/problems/reverse-linked-list-ii/
Date   : 01/21/2017

********************************************************************************
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
********************************************************************************
 */
package Leetcode_Java.linked_list_medium;

/**
 *
 * @author Borui Wang
 */
public class ReverseList2 {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    //需要建立虚拟链表，因为我们不知道反转后链表开始节点是什么
    //这里我们需要记录几个重要的节点preM, M，N，posN 如图
    //需要先把N 节点向后移动 n - m个位置，然后再把M和N节点一起向后移动，知道M节点就位
    //反转m 和 n之间的所有节点
//    pos:    1    2    3    4    5    6    7
//            1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7  m = 3, n = 5
//                 |    |         |    |
//                preM  M         N   posN
    static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode preM = null;
        ListNode mNode = dummy;
        ListNode postN = null;
        ListNode nNode = dummy;

        for (int i = 0; i < n - m; i++) {
            nNode = nNode.next;
        }

        for (int i = 0; i < m; i++) {
            preM = mNode;
            mNode = mNode.next;
            nNode = nNode.next;
        }
        postN = nNode.next;
        nNode.next = null;
        reverse(mNode);
        preM.next = nNode;
        mNode.next = postN;
        return dummy.next;
    }

    static void reverse(ListNode head) {
        ListNode prev = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

    }
    static ListNode createList(int [] nums) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        
        for(int num : nums) {
            tail.next = new ListNode(num);
            tail = tail.next;
        }
        
        return dummy.next;
    }
    static void printList(ListNode head) {
        while(head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        ListNode head = createList(new int[]{1,2,3,4,5});
        printList(reverseBetween(head, 2,4));
    }
}
