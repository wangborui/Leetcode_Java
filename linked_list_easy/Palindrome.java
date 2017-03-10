// Source : https://leetcode.com/problems/palindrome-linked-list/
// Date   : 03/10/2017

/********************************************************************************** 
 * 
 * Given a singly linked list, determine if it is a palindrome.
 * 
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 *               
 **********************************************************************************/

package Leetcode_Java.linked_list_easy;

import edu.princeton.cs.algs4.StdOut;


/**
 *
 * @author Borui Wang
 */
public class Palindrome {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //首先找到链表的中间位置，再反转中间节点之后的所有节点，返回反转链表的开始节点
    //用while循环对比原链表开始节点和反转链表的开始节点是否拥有同样的值,每次对比后两个链表都往后移动一位，重复直到反转链表为空。
    //需要注意的是如果原来链表长度为双数，则反转链表的长度正好为原长度的一半
    //如果原来链表长度为单数，则原来链表的长度 = 反转链表长度 * 2 + 1
    //所以我们在while循环的时候应该检查反转链表当前值是否为空，而不是原链表值是否为空
    static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = reverseList(slow.next);

        while (slow != null) {
            if (slow.val != head.val) {
                return false;
            } else {
                slow = slow.next;
                head = head.next;
            }
        }
        return true;
    }

    static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode end = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return end;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        StdOut.println(isPalindrome(head));

    }
}
