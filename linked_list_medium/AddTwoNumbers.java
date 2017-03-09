// Source : https://oj.leetcode.com/problems/add-two-numbers/
// Date   : 03/09/2017

/********************************************************************************** 
* 
* You are given two linked lists representing two non-negative numbers. 
* The digits are stored in reverse order and each of their nodes contain a single digit. 
* Add the two numbers and return it as a linked list.
* 
* Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
* Output: 7 -> 0 -> 8
*               
**********************************************************************************/

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
package Leetcode_Java.linked_list_medium;

/**
 *
 * @author Borui Wang
 */
public class AddTwoNumbers {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;
        ListNode walker1 = l1;
        ListNode walker2 = l2;
        int sum = 0;
        
        while(walker1 != null || walker2 != null) {
            sum /= 10;
            
            if(walker1 != null) {
                sum += walker1.val;
                walker1 = walker1.next;
            }
            
            if(walker2 != null) {
                sum += walker2.val;
                walker2 = walker2.next;
            }
            
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
        }
        if(sum >= 10) {
            tail.next = new ListNode(1);
        }
        return dummy.next;
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
        ListNode a = createList(new int[]{2,4,3});
        ListNode b = createList(new int[]{5,6,4});
        printList(addTwoNumbers(a,b));
    }
}
