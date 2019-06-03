package graph_medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 06/03/2019
 * https://leetcode.com/problems/course-schedule/
 *
 * Analysis:
 * Based on the description of the problem, we can simplify the problem as a directed edged graph.
 * We have the source node as the prerequisite for a course, and target node and the course we need to take
 * For example:
 *      Input: 2, [[1,0]] means the following graph, class 1 is the prereq of class 0
 *      +-----+          +-----+
 *      |  1  |--------->|  0  |
 *      +-----+          +-----+
 *
 * The issue is essentially turned into a graph problem. with node 1 indegree 0, node 0 indegree 1
 *
 * Solutions:
 * BFS:
 *      1. Build a graph
 *      2. Use priority queue to keep track of all nodes with indegree 0
 *      3. Pop top element off of priority queue and traverse the graph
 *      4. If there are non-zero indegree nodes left, then we cannot finish all courses
 *
 * DFS:
 *      1. Build a graph
 *      2. Visit all nodes to find if there are circles in the graph
 *          //0 = unvisited, 1 = visiting, 2 = visited
 *      3. If there are circles in the graph, then we cannot finish all course
 */
public class CanFinish {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses == 0 || prerequisites == null) {
            return true;
        }
        //0 = unvisited, 1 = visiting, 2 = visited
        int[] visited = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap();

        //build graph
        for(int[] p : prerequisites) {
            int course = p[0];
            int pre = p[1];
            map.putIfAbsent(pre, new ArrayList());
            List<Integer> temp =  map.get(pre);
            temp.add(course);
            map.put(pre, temp);
        }

        //find circle
        for(int i = 0; i < numCourses; i++) {
            if(dfsFindCircle(map, visited, i)) {
                return false;
            }
        }
        return true;
    }

    public boolean dfsFindCircle(Map<Integer, List<Integer>> map, int[] visited, int course) {
        if(visited[course] == 2) {
            return false;
        }

        if(visited[course] == 1) {
            return true;
        }

        boolean res = false;
        visited[course] = 1;
        if(map.containsKey(course)) {
            for(int next : map.get(course)) {
                res |= dfsFindCircle(map, visited, next);
            }
        }

        visited[course] = 2;
        return res;
    }

    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap();
        Queue<Integer> q = new LinkedList();

        //build graph
        for(int[] p : prerequisites) {
            int course = p[0];
            int pre = p[1];
            inDegree[course]++;
            map.putIfAbsent(pre, new ArrayList());
            map.get(pre).add(course);
        }

        //find zero in degrees
        for(int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0) {
                q.add(i);
            }
        }
        int count = 0;

        //traverse and find cycle
        while(!q.isEmpty()) {
            int temp = q.poll();
            count++;
            if(map.containsKey(temp)) {
                for(int next : map.get(temp)) {
                    if(--inDegree[next] == 0) {
                        q.add(next);
                    }
                }
            }
        }

        //return result
        System.out.println(count);
        return count == numCourses;
    }
}
