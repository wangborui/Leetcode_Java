package graph_medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 06/03/2019
 * https://leetcode.com/problems/reconstruct-itinerary/
 * Reference:
 * https://mnmunknown.gitbooks.io/algorithm-notes/content/ou_la_hui_lu_ff0c_hierholzer_suan_fa.html
 * https://www-m9.ma.tum.de/graph-algorithms/hierholzer/index_en.html
 *        1->
 * JFK <---------->SFO
 *  +     <-2
 *  |
 *  |
 *  3---> ATL
 */
public class FindItinerary {
    /**
     * Analysis
     *
     */
    public List<String> findItineraryDFSBackTracking(List<List<String>> tickets) {
        List<String> res =  new ArrayList();
        if (tickets == null || tickets.size() == 0) {
            return res;
        }
        int size = tickets.size() + 1;

        //build graph
        Map<String, List<String>> map = new HashMap();
        for(List<String> ticket: tickets) {
            List<String> destinations = map.getOrDefault(ticket.get(0), new ArrayList());
            destinations.add(ticket.get(1));
            map.put(ticket.get(0), destinations);
        }

        //sort list, because we need to return the itinerary in lexical order
        for(List<String> list: map.values()) {
            Collections.sort(list);
        }
        res.add("JFK");
        helper(map, res, "JFK", size);
        return res;
    }

    public boolean helper(Map<String, List<String>> map, List<String> res, String start, int size) {
        if(res.size() == size) {
            return true;
        }

        if(!map.containsKey(start)){
            return false;
        }

        List<String> destinations = map.get(start);

        //Note: using destinations.size(), this can vary as we traverse through the map, after removing an element
        for(int i = 0; i < destinations.size(); i++) {
            String airport = destinations.get(i);
            res.add(airport);
            destinations.remove(i);
            if(helper(map, res, airport, size)) {
                return true;
            }
            destinations.add(i, airport);
            res.remove(res.size() - 1);
        }
        return false;
    }

    public List<String> findItineraryEulerianPath(String[][] tickets) {
        List<String> res = new ArrayList();
        if(tickets == null || tickets.length == 0 || tickets[0] == null || tickets[0].length == 0) {
            return res;
        }

        Map<String, PriorityQueue<String>> map = new HashMap();

        //init
        for(String[] ticket : tickets) {
            map.computeIfAbsent(ticket[0], v -> new PriorityQueue()).add(ticket[1]);
        }

        dfs(map, res, "JFK");
        return res;
    }
    private void dfs(Map<String, PriorityQueue<String>> map, List<String> res, String s) {
        if(map.containsKey(s)) {
            while(!map.get(s).isEmpty())
                dfs(map, res, map.get(s).poll());
        }
        res.add(0, s);
    }
}
