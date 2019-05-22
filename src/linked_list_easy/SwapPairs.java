// Source : https://oj.leetcode.com/problems/swap-nodes-in-pairs/
// Date   : 03/10/2017

/********************************************************************************** 
* 
* Given a linked list, swap every two adjacent nodes and return its head.
* 
* For example,
* Given 1->2->3->4, you should return the list as 2->1->4->3.
* 
* Your algorithm should use only constant space. You may not modify the values in the list, 
* only nodes itself can be changed.
* 
*               
**********************************************************************************/

package leetcode_Java.linked_list_easy;

import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author Borui Wang
 */
public class SwapPairs {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    //recursive solutions

    static ListNode swapPairsRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //找到第一个节点，取名叫head
        //找到第二个节点，取名叫swappedHead
        //再找到第三个节点，取名叫nextPair
        //交换第一对节点，再递归交换之后成对的节点
        ListNode swappedHead = head.next;
        ListNode nextPair = head.next.next;
        swappedHead.next = head;
        head.next = swapPairsRecursive(nextPair);
        return swappedHead;
    }
    //iterative solutions

    static ListNode swapPairsIterative(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //因为我们不知道所有节点对交换后的结果，我们需要设立一个虚拟节点以便处理edge case
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        //当前节点指向虚拟节点，while 循环确保当前节点后有一对不为空的节点
        //交换当前节点后的一对不为空的节点，并将当前节点指向交换后节点的末尾节点
        while (current.next != null && current.next.next != null) {
            ListNode swappedSecond = current.next;
            ListNode swappedFirst = current.next.next;

            current.next = swappedFirst;
            swappedSecond.next = swappedFirst.next;
            swappedFirst.next = swappedSecond;
            current = swappedSecond;
        }
        return dummy.next;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        ListNode result = swapPairsRecursive(head);
        while (result != null) {
            StdOut.println(result.val);
            result = result.next;
        }

    }
}
