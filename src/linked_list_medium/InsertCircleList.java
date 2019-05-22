package linked_list_medium;

/**
 * Given a sorted non-duplicate circular linked list, insert a new node k, maintain order in the linkedlist, and return
 * the minimum node in circular linked list
 *
 * Solution:
 * 1. Iterate the entire circular linked list and use a min node to keep track of the minimum node
 * 2. Iterate the entire circular linked list, keep a wallker node as current node, and three things can happen:
 *    a. walker.val >= walker.next.val
 *       Insert node k, if k greater than walker.val or k is less than walker.next.val
 *    b. walker.val < k && k < walker.next.val
 *       Insert node k
 * 3. Finish iterating the loop and return min
 */
public class InsertCircleList {
    static class Node{
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }
    public static void main(String[] args) {
        //Test Case 1
        Node temp = null;

        InsertCircleList insertCircleList = new InsertCircleList();
        System.out.println(insertCircleList.insert(temp, 3));

        temp = new Node(1);
        temp.next = temp;
        //Test Case 2
        System.out.println(insertCircleList.insert(temp, 3));
        temp = new Node(1);
        temp.next = temp;
        //Test Case 3
        System.out.println(insertCircleList.insert(temp, 0));

        Node node1 = new Node(1);
        Node node5 = new Node(5);
        node1.next = node5;
        node5.next = node1;
        System.out.println(insertCircleList.insert(node5, 3));
    }

    public Node insert(Node root, int k) {
        if (root == null || root.next == null) {
            return root;
        }

        Node min = new Node(k);
        Node kNode = new Node(k);
        Node walker = root;
        do {
            if (min.val > walker.val) {
                min = walker;
            }

            if (walker.val >= walker.next.val) {
                if (walker.val < k || k < walker.next.val) {
                    Node next = walker.next;
                    walker.next = kNode;
                    kNode.next = next;
                }
            } else {
                if (walker.val < k && k < walker.next.val) {
                    Node next = walker.next;
                    walker.next = kNode;
                    kNode.next = next;
                }
            }

            walker = walker.next;
        } while (walker != root);
        return min;
    }
}
