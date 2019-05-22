// Source : https://leetcode.com/problems/delete-node-in-a-linked-list/
// Date   : 03/10/2017

/********************************************************************************** 
 * 
 * Write a function to delete a node (except the tail) in a singly linked list, given 
 * only access to that node.
 * 
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with 
 * value 3, the linked list should become 1 -> 2 -> 4 after calling your function.
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
package Leetcode_Java.linked_list_easy;

/**
 *
 * @author Borui Wang
 */
public class DeleteNode {

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    //根据题意，末尾节点不能被删除
    //首先判断当前节点是否为末尾节点
    //如果不是，我们把当前节点的值设置为当前节点下一个节点的值
    //再将当前节点的下一节点变为其下下节点
    public void deleteNode(ListNode node) {
        if (node.next != null) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }
}
