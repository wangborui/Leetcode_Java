// Source : https://oj.leetcode.com/problems/insertion-sort-list/
// Date   : 03/10/2017

/********************************************************************************** 
* 
* Sort a linked list using insertion sort.
*               
**********************************************************************************/
package Leetcode_Java.linked_list_medium;

/**
 *
 * @author Borui Wang
 */
public class InsertionSortList {

    public class Solution {

        public class ListNode {

            int val;
            ListNode next;

            ListNode(int x) {
                val = x;
            }
        }

        public ListNode insertionSortList(ListNode head) {
            //如果原链表只是空的或者只有一个节点，可以直接返回
            if (head == null || head.next == null) {
                return head;
            }
            //我们不知道原链表在排序之后的开始节点是什么，所以创建虚拟链表
            //在这里虚拟链表表示已经排好序的部分，最开始是空的
            ListNode dummy = new ListNode(-1);
            //prev 指针用来扫描已经排好序的虚拟链表，以找出新元素的插入点
            ListNode prev = dummy;
            
            //遍历原链表里面所有的元素，直到遍历完毕，头节点为空
            while (head != null) {
                prev = dummy;
                //从虚拟链表的头节点开始扫描， 找出虚拟链表中第一个大于等于当前所要插入元素（head）的节点
                //如果虚拟链表中的所有节点都小于当前索要插入的节点（head），就把head放在虚拟链表的最尾部
                while (prev.next != null && prev.next.val < head.val) {
                    prev = prev.next;
                }
                //将当前元素（head）插入到已经排好序的虚拟链表中， 并维持其序列
                ListNode next = head.next;
                head.next = prev.next;
                prev.next = head;
                head = next;
            }
            return dummy.next;
        }
    }
}
