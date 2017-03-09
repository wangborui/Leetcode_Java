// Source : https://leetcode.com/problems/add-two-numbers-ii/?tab=Description
// Date   : 03/09/2017
/**
 * ********************************************************************************
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 *********************************************************************************
 */
package Leetcode_Java.linked_list_medium;

import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class AddTwoNumbers2 {

    /**
     * Definition for singly-linked list. public class ListNode { int val;
     * ListNode next; ListNode(int x) { val = x; } }
     */
    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> num1 = new Stack();
        Stack<Integer> num2 = new Stack();
        ListNode dummy = new ListNode(-1);
        int sum = 0;

        while (l1 != null) {
            num1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            num2.push(l2.val);
            l2 = l2.next;
        }

        while (!num1.isEmpty() || !num2.isEmpty()) {
            sum /= 10;
            if (!num1.isEmpty()) {
                sum += num1.pop();
            }

            if (!num2.isEmpty()) {
                sum += num2.pop();
            }

            ListNode temp = new ListNode(sum % 10);
            temp.next = dummy.next;
            dummy.next = temp;
        }

        if (sum >= 10) {
            ListNode temp = new ListNode(1);
            temp.next = dummy.next;
            dummy.next = temp;
        }

        return dummy.next;
    }

}
