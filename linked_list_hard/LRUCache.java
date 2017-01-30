/*
Source: https://leetcode.com/problems/lru-cache/
/********************************************************************************** 
* 
* Design and implement a data structure for Least Recently Used (LRU) cache. 
* It should support the following operations: get and set.
* 
*    get(key) - Get the value (will always be positive) of the key if the key exists 
*               in the cache, otherwise return -1.
*
*    set(key, value) - Set or insert the value if the key is not already present. 
*                      When the cache reached its capacity, it should invalidate 
*                      the least recently used item before inserting a new item.
*               
**********************************************************************************/

package Leetcode_Java.linked_list_hard;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class LRUCache {

    Map<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;

    private class Node {

        int key;
        int val;
        Node next;
        Node prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }
// The idea here is quite simple:
//    1) A Map to index the key.  O(1) key search time-complexity.
//    2) A List to sort the cache data by accessed time. O(1) delete and insert time
// 
//  Considering there are too many insert/delete opreations for the List, 
//  The doubly linked list is the good data structure to performance it.
    public LRUCache(int capacity) {
        map = new HashMap();
        //head.next is Least Recently used node
        head = new Node(-1, -1);
        //tail.prev is the Most Recently Usred node
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }
    //update LRU node
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        //first delete the node
        Node node = map.get(key);
        delete_node(node);
        //move the node to tail, node becomes LRU element
        move_to_tail(node);
        return map.get(key).val;
    }
    //move node to the end of the list, making it MRU node
    private void move_to_tail(Node current) {
        current.prev = tail.prev;
        tail.prev = current;
        current.prev.next = current;
        current.next = tail;
    }
    //delete node from doubly linked list
    private void delete_node(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void set(int key, int value) {
        //insert the value when it does not exist
        if (get(key) == -1) {
            //invalidate LRU node, when cache capacity is reached
            if (map.size() == capacity) {
                map.remove(head.next.key);
                delete_node(head.next);
            }
            Node insert = new Node(key, value);
            map.put(key, insert);
            move_to_tail(insert);
        } //set the value when it already exists
        else {
            Node node = map.get(key);
            node.val = value;
            //does not move node to tail after setting value
        }

    }
}
