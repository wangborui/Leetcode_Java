// Source : https://oj.leetcode.com/problems/intersection-of-two-linked-lists/
// Date   : 03/10/2017
/**
 * ********************************************************************************
 *
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * For example, the following two linked lists:
 *
 *
 *    A:          a1 → a2
 *                       ↘
 *                         c1 → c2 → c3
 *                       ↗
 *    B:     b1 → b2 → b3
 *
 * begin to intersect at node c1.
 *
 * Notes:
 *
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 *
 *********************************************************************************
 */
package Leetcode_Java.linked_list_easy;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Borui Wang
 */
public class IntersectionNode {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    //首先找出A链表和B链表的长度
    //如果两个链表长度不一样，就先把长的链表往后移动 |lenA - lenB| 步，使得两个链表长度一样
    //用while循环让两个链表各自向后走，知道他们的值相等
    //在循环过程中，如果两个链表的值相等了，我们会有两种可能性
    //1.)他们没有交点，两个都走到了最后null的值
    //2.)他们有交点，两个链表碰到了一起
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getLength(headA);
        int lenB = getLength(headB);

        //get 2 lists to start at the same amount of nodes away from the end
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }

        while (lenB > lenA) {
            headB = headB.next;
            lenB--;
        }

        //either headA = headB = null
        //or headA = headB = some node
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }

    private int getLength(ListNode node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }
}
