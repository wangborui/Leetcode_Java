// Source : https://leetcode.com/problems/lfu-cache/?tab=Description
// Date   : 03/09/2017
/**
 * ********************************************************************************
 * Design and implement a data structure for Least Frequently Used (LFU) cache.
 * It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity,
 * it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem,
 * when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LFUCache cache = new LFUCache( 2 /* capacity);
 *
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.get(3);       // returns 3.
 * cache.put(4, 4);    // evicts key 1.
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 *********************************************************************************
 */
package Leetcode_Java.linked_list_hard;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class LFUCache {

    private class Node {

        private final int count;
        private LinkedHashSet<Integer> keys;
        private Node prev;
        private Node next;

        public Node(int c) {
            this.count = c;
            keys = new LinkedHashSet();
            prev = null;
            next = null;
        }
    }
    private final int CAP;
    private Map<Integer, Integer> valueMap;
    private Map<Integer, Node> nodesMap;
    private Node head;
    private Node tail;

    public LFUCache(int capacity) {
        this.CAP = capacity;
        this.valueMap = new HashMap();
        this.nodesMap = new HashMap();
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (valueMap.containsKey(key)) {
            int val = valueMap.get(key);
            increaseCount(key);
            return val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (CAP <= 0) {
            return;
        }
        if (valueMap.size() < CAP) {
            if (valueMap.containsKey(key)) {
                valueMap.put(key, value);
                increaseCount(key);
            } else {
                //need to insert a new node 
                insertNewKeyValue(key, value);
            }
        } else if (valueMap.containsKey(key)) {
            valueMap.put(key, value);
            increaseCount(key);
        } else {
            //remove least recent key
            int leastRecentKey = -1;
            for (int k : head.next.keys) {
                leastRecentKey = k;
                break;
            }
            deleteKeyInLinkedList(leastRecentKey, nodesMap.get(leastRecentKey));
            //delete least recent key from hashmap
            valueMap.remove(leastRecentKey);
            nodesMap.remove(leastRecentKey);
            //insert new key
            insertNewKeyValue(key, value);
        }
    }

    private void increaseCount(int key) {
        Node cur = nodesMap.get(key);
        int freq = cur.count;
        //need to create a new node 
        if (cur.next.count != freq + 1) {
            Node temp = new Node(freq + 1);
            temp.keys.add(key);

            //insert a new node after current node
            insertNode(cur, temp);
            //update node map
            nodesMap.put(key, temp);
        } else {
            cur.next.keys.add(key);
            //update node map
            nodesMap.put(key, cur.next);
        }
        //delete key from current node, if node.keys becomes empty, delete node
        deleteKeyInLinkedList(key, cur);
    }

    private void deleteKeyInLinkedList(int key, Node n) {
        n.keys.remove(key);
        if (n.keys.isEmpty()) {
            deleteNode(n);
        }
    }

    private void deleteNode(Node n) {
        Node prev = n.prev;
        Node next = n.next;

        prev.next = next;
        next.prev = prev;
        n.next = null;
        n.prev = null;
    }

    private void insertNode(Node prevNode, Node curNode) {
        curNode.prev = prevNode;
        curNode.next = prevNode.next;
        prevNode.next = curNode;
        curNode.next.prev = curNode;
    }

    private void insertNewKeyValue(int key, int value) {
        int leastFreq = head.next.count;
        valueMap.put(key, value);
        if (leastFreq == 1) {
            head.next.keys.add(key);
            nodesMap.put(key, head.next);
        } else {
            //need to insert a new node
            Node temp = new Node(1);
            temp.keys.add(key);
            nodesMap.put(key, temp);
            insertNode(head, temp);
        }
    }

    private void printLinkedList() {
        Node walker = head.next;
        if (walker.count == -1) {
            System.out.println("null");
        }
        while (walker != null && walker.count != -1) {
            System.out.println("count: " + walker.count + " [" + walker.keys + "] ");
            walker = walker.next;
        }

    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2 /* capacity */);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3.
        cache.put(4, 4);    // evicts key 1.
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));        // returns 3
        System.out.println(cache.get(4));        // returns 4

//        LFUCache cache = new LFUCache(3 /* capacity */);
//        cache.put(2,2);
//        cache.put(1, 1);
//        System.out.println(cache.get(2));   //return 2
//        System.out.println(cache.get(1));   //return 1
//        System.out.println(cache.get(2));   //return 2
//        cache.put(3, 3);                    
//        cache.put(4, 4);                    //evicts 3
//        System.out.println(cache.get(3));   //return -1 (not found)
//        System.out.println(cache.get(2));   //return 2
//        System.out.println(cache.get(1));   //return 1
//        System.out.println(cache.get(4));   //return 4
//        LFUCache cache = new LFUCache(2 /* capacity */);
//        System.out.println(cache.get(1));  //return -1 not found 
//       // cache.printLinkedList(); 
//        cache.put(2,6);                    
//        //cache.printLinkedList();
//        System.out.println(cache.get(1));  //return -1 not found
//        //cache.printLinkedList();
//        cache.put(1,5);                    
//        //cache.printLinkedList();
//        cache.put(1,2);                    
//        //cache.printLinkedList();
//        System.out.println(cache.get(1));  //return 2
//        //cache.printLinkedList(); 
//        System.out.println(cache.get(2));  //return 6
//        //cache.printLinkedList(); 
    }
}

/**
 * Your LFUCache object will be instantiated and called as such: LFUCache obj =
 * new LFUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */
