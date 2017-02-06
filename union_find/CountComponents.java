//Source : https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
//Date   : 02/05/2017
/**
 * *****************************************************************************
 * Total Accepted: 20754
 * Total Submissions: 44151
 * Difficulty: Medium
 * Contributors: Admin
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to find the number of connected components in an undirected graph.
 *
 * Example 1:
 * 0          3
 * |          |
 * 1 --- 2    4
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
 *
 * Example 2:
 * 0           4
 * |           |
 * 1 --- 2 --- 3
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
 *
 * Note:
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 *
 *
 *******************************************************************************
 */
package Leetcode_Java.union_find;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Borui Wang
 */
public class CountComponents {
    //BFS

    public int countComponents(int n, int[][] edges) {
        if (n <= 0) {
            return 0;
        }
        //each nodes and its neighbors
        Map<Integer, List<Integer>> graph = new HashMap();
        Set<Integer> visited = new HashSet();
        Queue<Integer> q = new LinkedList();
        int connectedC = 0;
        //add nodes
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList());
        }

        //add edges
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        //BFS traversal
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                connectedC++;
                q.add(i);
                visited.add(i);
                while (!q.isEmpty()) {
                    int node = q.poll();
                    for (int neighbor : graph.get(node)) {
                        if (!visited.contains(neighbor)) {
                            q.add(neighbor);
                            visited.add(neighbor);
                        }
                    }
                }
            }
        }
        return connectedC;
    }
    /**
     * 
     * @param n
     * @param edges
     * @return int
     */
//******************************************************************************
//                               Regular Union Find
//      when encounters an edge, union two sides of the edges
//      when union two nodes, find their ancestors O(n) time complexity, if ancestors are not the same,
//      union both ancestors, otherwise do nothing.
//      Time O(n^2) Space O(n)
//******************************************************************************
      
     
    
    public int countComponentsUnionFind(int n, int[][] edges) {
        int[] roots = new int[n];

        //initalize each node to be its own root
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }

        //union each nodes with given edges
        for (int[] edge : edges) {
            int root1 = find(roots, edge[0]);
            int root2 = find(roots, edge[1]);

            //union
            if (root1 != root2) {
                roots[root1] = root2;
                n--;
            }
        }
        return n;
    }

    private int find(int[] roots, int node) {
        while (node != roots[node]) {
            node = roots[node];
        }
        return node;
    }
    /**
     * 
     * @param n
     * @param edges
     * @return int
     */
//******************************************************************************
//                          Path Compression Union Find
//      when encounters an edge, union two sides of the edges
//      when union two nodes, find their ancestors O(1) time complexity, if ancestors are not the same,
//      union both ancestors, otherwise do nothing.
//        Path compression happens at the find operation, at one point find operation
//        may need to do O(n) operations to compression path, but find will have amortized O(1) time
//        
//        for example,
//        
//            1->2->3->4->5         find(1) returns 5 and compresses path
//               
//               2 
//               |
//               V 
//         1 - > 5 < - 3
//               ^
//               |
//               4
//      Time O(n) Space O(n)
//******************************************************************************
    public int countComponentsPathCompressionUnionFind(int n, int[][] edges) {
        int [] ancestor = new int[n];

        //initalize each node to be its own root
        for(int i = 0; i < n; i++) {
            ancestor[i] = i;
        }

        //union each nodes with given edges
        for(int [] edge : edges) {
            int root1 = compressed_find(ancestor, edge[0]);
            int root2 = compressed_find(ancestor, edge[1]);

            //union
            if(root1 != root2) {
                ancestor[root1] = root2;
                n--;
            }
        }
        return n;
    }
    private int compressed_find(int [] ancestors, int node) {
        //find the ancestor of the node
        int ancestor = node;
        while(ancestor != ancestors[ancestor]) {
            ancestor = ancestors[ancestor];
        }
        int parent = ancestors[node];
        //compressing each node path
        while(parent != ancestor) {
            ancestors[node] = ancestor;
            node = parent;
            parent = ancestors[parent];

        }
        return ancestor;
    }
}
