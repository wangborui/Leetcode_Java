/*
Source: https://leetcode.com/problems/copy-list-with-random-pointer/

********************************************************************************
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
********************************************************************************

Solution: Time O(3n) Space O(n)
given list 1->2->3...
copy next: x' means the new deep copy of x 1->1'->2->2'->3->3'...
copy random: copy original nodes random pointer to deep copy nodes random pointer
separate original node from copy node and return copy node
 */
package Leetcode_Java.linked_list_hard;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class CopyRandomList {

    private class RandomListNode {

        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    };

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return head;
        }
        copyNext(head);
        copyRandom(head);
        return separateCopy(head);
    }

    private void copyNext(RandomListNode head) {
        while (head != null) {
            RandomListNode nextNode = head.next;
            RandomListNode copyNode = new RandomListNode(head.label);
            head.next = copyNode;
            copyNode.next = nextNode;
            head = nextNode;
        }
    }

    private void copyRandom(RandomListNode head) {
        while (head != null) {
            RandomListNode randomNode = head.random;
            RandomListNode copyNode = head.next;
            if (randomNode != null) {
                copyNode.random = randomNode.next;
            } else {
                copyNode.random = randomNode;
            }

            head = head.next.next;
        }
    }

    //separate original list and deep copy list into two different lists
    private RandomListNode separateCopy(RandomListNode head) {
        RandomListNode dummy = new RandomListNode(-1);
        RandomListNode tail = dummy;
        while (head != null) {
            RandomListNode nextNode = head.next.next;
            RandomListNode copyNode = head.next;
            tail.next = copyNode;
            head.next = nextNode;
            head = nextNode;
            tail = tail.next;
        }
        return dummy.next;
    }
    //遍历整个链表，把每个节点存到哈希表里面，并且在哈希表里加入另外一个深度复制的节点
    //再次遍历整个整个链表，深度复制next指针
    //最后遍历整个链表，深度复制random指针
    //放回深度复制链表的头
    private RandomListNode copyRandomListHashMap(RandomListNode head) {
        if (head == null) {
            return head;
        }
        Map<RandomListNode, RandomListNode> map = new HashMap();
        RandomListNode walker = head;
        //make copy of old nodes
        while (walker != null) {
            map.put(walker, new RandomListNode(walker.label));
            walker = walker.next;
        }

        //copy next pointer
        walker = head;
        while (walker != null) {
            RandomListNode copy = map.get(walker);
            copy.next = map.getOrDefault(walker.next, null);
            walker = walker.next;
        }

        //copy random pointer
        walker = head;
        while (walker != null) {
            RandomListNode copy = map.get(walker);
            copy.random = map.getOrDefault(walker.random, null);
            walker = walker.next;
        }
        return map.get(head);
    }
}
