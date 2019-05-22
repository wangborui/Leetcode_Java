/*
Source: http://www.lintcode.com/en/problem/topological-sorting/
Date  : 01/26/2017

********************************************************************************
Given an directed graph, a topological order of the graph nodes is defined as follow:

For each directed edge A -> B in graph, A must before B in the order list.
The first node in the order can be any node in the graph with no nodes direct to it.
Find any topological order for the given graph.

 Notice

You can assume that there is at least one topological order in the graph.

Have you met this question in a real interview? Yes
Clarification
Learn more about representation of graphs

Example
For graph as follow:

picture

The topological order can be:

[0, 1, 2, 3, 4, 5]
[0, 2, 3, 1, 5, 4]
...
********************************************************************************

Solution:
find indegree of each node, then find nodes with 0 indegree, and remove nodes with 0
indegrees and add to results. Before removing the node, subtract the number of indegrees from node's neighbor
repeat the process until no more nodes left in queue

 */
package Leetcode_Java.graph_medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class TopologicalSort {

    class DirectedGraphNode {

        int label;
        ArrayList<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    };

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Integer> indegreeMap = new HashMap();
        Queue<DirectedGraphNode> queue = new LinkedList();
        ArrayList<DirectedGraphNode> res = new ArrayList();
        //find indegree of each node
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                if (!indegreeMap.containsKey(neighbor)) {
                    indegreeMap.put(neighbor, 1);
                } else {
                    indegreeMap.put(neighbor, indegreeMap.get(neighbor) + 1);
                }
            }
        }

        //find nodes with 0 indegree
        for (DirectedGraphNode node : graph) {
            if (!indegreeMap.containsKey(node)) {
                queue.add(node);
            }
        }

        //topological sorting
        while (!queue.isEmpty()) {
            DirectedGraphNode node = queue.poll();
            res.add(node);
            //find 0 indegree neighbors after removal node
            for (DirectedGraphNode neighbor : node.neighbors) {
                int indegree = indegreeMap.get(neighbor) - 1;
                indegreeMap.put(neighbor, indegree);
                if (indegree == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return res;
    }
}
