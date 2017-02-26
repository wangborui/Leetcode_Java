// Source : https://leetcode.com/problems/evaluate-division/
// Date   : 02/26/2017
/**
 * *************************************************************************************
 *
 * Equations are given in the format A / B = k, where  A and B are variables
 * represented as strings, and k is a real number (floating point number). Given some
 * queries, return the answers. If the answer does not exist, return -1.0.
 *
 * Example:
 * Given  a / b = 2.0, b / c = 3.0. queries are:  a / c = ?,  b / a = ?, a / e = ?,  a
 * / a = ?, x / x = ? . return  [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is:  vector<pair<string, string>> equations, vector<double>& values,
 * vector<pair<string, string>> queries , where equations.size() ==
 * values.size(), and the values are positive. This represents the equations.
 * Return vector<double>.
 *
 * According to the example above: equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0], queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a",
 * "a"], ["x", "x"] ].
 *
 * The input is always valid. You may assume that evaluating the queries will
 * result in no division by zero and there is no contradiction.
 **************************************************************************************
 */
package Leetcode_Java.graph_medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Borui Wang
 */
public class CalcEquation {

    static double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        //global map
        Map<String, Map<String, Double>> graph = new HashMap();
        double[] res = new double[queries.length];
        //build global map
        for (int i = 0; i < equations.length; i++) {
            String[] e = equations[i];
            String from = e[0];
            String to = e[1];
            double val = values[i];
            //adding from neighbors
            insertRow(graph, from, to, val);
            //adding to neighbors
            insertRow(graph, to, from, 1 / val);
        }
        for (int i = 0; i < queries.length; i++) {
            String[] q = queries[i];
            String from = q[0];
            String to = q[1];
            res[i] = dfsHelper(graph, new HashSet(), from, to, 1);
        }
        return res;
    }

    static void insertRow(Map<String, Map<String, Double>> graph, String from, String to, double val) {
        Map<String, Double> neighbors = graph.get(from);
        if (neighbors == null) {
            neighbors = new HashMap();
        }
        neighbors.put(to, val);
        graph.put(from, neighbors);
    }

    static double dfsHelper(Map<String, Map<String, Double>> graph, Set<String> visited, String from, String to, double val) {
        //handles case to or from does not exist
        if (!graph.containsKey(from) || !graph.containsKey(to) || visited.contains(from)) {
            return -1.0;
        }
        //handles case A/A or A has already reached B
        if (from.equals(to)) {
            return val;
        }

        visited.add(from);
        Double temp = 0.0;
        for (String neighbor : graph.get(from).keySet()) {
            temp = dfsHelper(graph, visited, neighbor, to, val * graph.get(from).get(neighbor));
            if (temp != -1.0) {
                break;
            }
        }
        visited.remove(from);
        return temp;
    }

    static void printDouble(double[] d) {
        for (double n : d) {
            System.out.print(n + " | ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[][] equations = {{"a", "b"}, {"b", "c"}};
        double[] values = {2.0, 3.0};
        //String[][] queries = {{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
        String[][] queries = {{"a", "c"}, {"b", "c"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};

        printDouble(calcEquation(equations, values, queries));
    }
}
