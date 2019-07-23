package heap_hard;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 07/23/2019
 * https://leetcode.com/problems/reorganize-string/
 */
public class ReorganizeString {
    class Solution {
        class Pair {
            public Character c;
            public Integer i;
            public Pair(char c, int i) {
                this.c = c;
                this.i = i;
            }

            public Character getKey() {
                return c;
            }

            public Integer getValue() {
                return i;
            }

            public String toString() {
                return "character: " + c + " integer: " + i;
            }
        }
        public String reorganizeString(String S) {
            Map<Character, Integer> map = new HashMap();
            char[] c = S.toCharArray();
            for(int i = 0; i < S.length(); i++) {
                int count = map.getOrDefault(c[i], 0) + 1;
                map.put(c[i], count);
                if(count > (S.length() + 1) / 2) {
                    return "";
                }
            }

            PriorityQueue<Pair> pq = new PriorityQueue<>((Pair a, Pair b) -> b.getValue() - a.getValue());

            for(Map.Entry<Character, Integer> entry : map.entrySet()) {
                pq.add(new Pair(entry.getKey(), entry.getValue()));
            }
            StringBuilder sb = new StringBuilder();
            while(!pq.isEmpty()) {
                Pair first = pq.poll();
                if(sb.toString().isEmpty() || sb.charAt(sb.length() - 1) != first.c) {
                    sb.append("" + first.c);
                    if(first.i - 1 > 0) {
                        pq.add(new Pair(first.c, first.i - 1));
                    }
                } else {
                    Pair second = pq.poll();
                    sb.append("" + second.c);
                    if(second.i - 1 > 0) {
                        pq.add(new Pair(second.c, second.i - 1));
                    }
                    pq.add(first);
                }
            }
            return sb.toString();
        }
    }
}
