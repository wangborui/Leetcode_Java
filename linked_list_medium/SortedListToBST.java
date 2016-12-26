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
   //Time O(1), saves time from finding mid node 
    private static ListNode current;
    static TreeNode sortedListToBSTWOMID(ListNode head) {
        if(head == null) {
            return null;
        }
        
         current = head;
         int length = 0;
         for(ListNode p = head; p != null; p = p.next) {
             length++;
         }
         return inOrderTraversal(0, length - 1);
    }
    //[3,5,8]
    static TreeNode inOrderTraversal(int start, int end) {
        if(current == null || start > end) {
            return null;
        }
        
        int mid = start + (end - start) / 2;
        TreeNode left = inOrderTraversal(start, mid - 1);
        TreeNode root = new TreeNode(current.val);
        current = current.next;
        TreeNode right = inOrderTraversal(mid + 1, end);
        
        root.left = left;
        root.right = right;
        
        return root;
        
    }
    
    public static void main(String[] args){
        ListNode a = new ListNode(3);
        ListNode b = new ListNode(5);
        ListNode c = new ListNode(8);
        a.next = b;
        b.next = c;
        //TreeNode tree = sortedListToBST(a);
        
       TreeNode tree = sortedListToBSTWOMID(a);
        System.out.println();
    }
}
