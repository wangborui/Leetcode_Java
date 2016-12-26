/*
Source: https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
********************************************************************************
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 
********************************************************************************

Solution 1: find middle of the list
total time O(nlogn) exceeds OJ time limit
find middle method takes O(n) time, construction of treeNode takes O(1)

                                [--------------------------]                    = O(n) ]
                                     /                 \                               |
                      [------------]O(n/2)    +     [-------------]O(n/2)       = O(n) | log n levels
                                             ...                                       |  
                      []O(1) [] O(1) ..............        []O(1) []O(1)        = O(n) ]
 */
package Leetcode_Java.linked_list_medium;

/**
 *
 * @author Borui Wang
 */
public class SortedListToBST {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
 //total time O(nlogn) 
    static TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        //find middle node first time O(n)
        ListNode preMid = null;
        ListNode slow = head;
        ListNode fast = head;
        //slows moves one step, fast moves 2 steps, after loop slow is the middle of the list
        while (fast != null && fast.next != null) {
            preMid = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //list size is 1
        if (preMid == null) {
            //root.left is must be null
            head = null;
        } else {
            preMid.next = null;
        }

        TreeNode root = new TreeNode(slow.val);
        root.right = sortedListToBST(slow.next);
        root.left = sortedListToBST(head);
        return root;
    }
    
    public static void main(String[] args){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        a.next = b;
        sortedListToBST(a);
    }
}
