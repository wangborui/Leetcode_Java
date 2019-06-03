package graph_medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 06/02/2019
 * https://leetcode.com/problems/network-delay-time/
 * Solution:
 *
 * Analysis:
 * This question is asking if we start at node K can we reach all nodes in a network? if so, what is the amount of time it will take?
 * This question can be rephrased as the following:
 *   1. If we start at node K, find the shortest path of all other nodes from node K
 *   2. If we cannot reach all nodes from node K, then return -1
 *   3. If we are able to reach all nodes from node K, then we need to find the maximum of the shortest paths of all nodes
 *
 * Algorithm:
 * Dijkstra's Shortest Path Algorithm: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
 * Note:
 * The value we add into the priority queue or heap is distance: K -> current node -> neighbor of current node
 * NOT the distance: current node -> neighbor of current node
 *
 * Implementation:
 * 1. Build the graph:
 *  a. Use HashMap[SourceNode, List[NeighborNode, DistanceFromSourceNodeToNeighborNode]]
 * 2. Edge Relaxation
 *  a. When a shorter distance is found between source node and current node, relax(update) current node distance and add to pq
 * 3. Find max distance from shortest path
 */

public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int N, int K) {
        PriorityQueue<int[]> q = new PriorityQueue<int[]>((time1, time2) -> time1[1] - time2[1]);
        Map<Integer, List<int[]>> graph = new HashMap<>();
        int[] dist = new int[N + 1];
        int res = 0;

        //build graph
        for (int[] time : times) {
            int from = time[0];
            int to = time[1];
            int duration = time[2];

            graph.putIfAbsent(from, new ArrayList<>());
            List<int[]> temp = graph.get(from);
            temp.add(new int[]{to, duration});
        }

        //initialize all nodes as positive infinity distance from source
        for (int i = 0; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[K] = 0;
        q.add(new int[]{K, 0});
        while (!q.isEmpty()) {
            int[] temp = q.poll();

            int fromNode = temp[0];
            int distFromKToNode = temp[1];
            if (!graph.containsKey(fromNode)) {
                continue;
            }

            //examine all neighbors and relax(update) shortest distance
            for (int[] neighborInfo : graph.get(fromNode)) {
                int neighbor = neighborInfo[0];
                int distFromNodeToNeighbor = neighborInfo[1];

                if (dist[neighbor] > distFromKToNode + distFromNodeToNeighbor) {
                    dist[neighbor] = distFromKToNode + distFromNodeToNeighbor;
                    int[] temp2 = new int[]{neighbor, dist[neighbor]};
                    q.add(temp2);
                }

            }
        }

        //find max distance amongst shortest distance of all nodes
        for (int d : dist) {
            if (d != Integer.MAX_VALUE) {
                N--;
                res = Math.max(res, d);
            }
        }
        return N == 0 ? res : -1;
    }

    public static void main(String[] args) {
        PriorityQueue<int[]> q = new PriorityQueue<>((time1, time2) -> time1[2] - time2[2]);
        q.add(new int[]{3, 1});
        q.add(new int[]{2, 3});
    }
}
