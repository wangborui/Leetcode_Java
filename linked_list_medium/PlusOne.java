// Source : https://leetcode.com/problems/plus-one-linked-list/?tab=Description
// Date   : 03/09/2017
/**
 * ********************************************************************************
 * Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
 *
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example:
 * Input:
 * 1->2->3
 *
 * Output:
 * 1->2->4
 *********************************************************************************
 */
package Leetcode_Java.linked_list_medium;

/**
 *
 * @author Borui Wang
 */
public class PlusOne {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static ListNode plusOne(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode reversedHead = reverse(head);
        ListNode walker = reversedHead;
        int carry = 1;

        while (walker != null) {
            int val = walker.val;
            if (val == 9) {
                walker.val = 0;
            } else {
                walker.val++;
                carry = 0;
                break;
            }
            walker = walker.next;
        }
        if (carry == 1) {
            ListNode temp = new ListNode(1);
            temp.next = reversedHead;
            return temp;
        } else {
            return reverse(reversedHead);
        }

    }

    static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    static ListNode createList(int[] a) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        for (int n : a) {
            ListNode temp = new ListNode(n);
            tail.next = temp;
            tail = tail.next;
        }
        return dummy.next;
    }

    static void printList(ListNode root) {
        while (root != null) {
            System.out.print(root.val + " ");
            root = root.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head = createList(new int[]{1, 2, 3});
        printList(plusOne(head));
    }
}
