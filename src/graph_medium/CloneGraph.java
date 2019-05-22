/*
Source: https://leetcode.com/problems/clone-graph/
********************************************************************************
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
********************************************************************************

Solution: Time O(n) Space O(n)
Use hashmap to store pair oldNode, clonedNode
clone all the old nodes and store them in hashmap with clonedNode with BFS
clone all edges with BFS
 */
package Leetcode_Java.graph_medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Borui Wang
 */
public class CloneGraph {

// Definition for undirected graph.
  class UndirectedGraphNode {
     int label;
      List<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 };

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) {
            return null;
        }
        //remembers the path to traverse the graph with BFS
        List<UndirectedGraphNode> nodes = new ArrayList();
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap();
        nodes.add(node);
        map.put(node, new UndirectedGraphNode(node.label));
        int size = 0;
        
        //clone nodes
        while(size < nodes.size()) {
            UndirectedGraphNode temp = nodes.get(size++);
            for(UndirectedGraphNode neighbor: temp.neighbors) {
                //if node is unvisited
                if(!map.containsKey(neighbor)) {
                        nodes.add(neighbor);
                        map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                    }
            }
             
        }
        
        //clone edges with remembered BFS path from unique nodes  
        for(UndirectedGraphNode visitedNode : nodes) {
            UndirectedGraphNode cloneNode = map.get(visitedNode);
            for(UndirectedGraphNode neighbor : visitedNode.neighbors) {
                cloneNode.neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);                                                                                                                                                                                       
    }
}
