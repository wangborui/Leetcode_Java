package linked_list_medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * June 11, 2019
 * https://www.geeksforgeeks.org/find-top-k-or-most-frequent-numbers-in-a-stream/
 *
 * Given a stream of numbers, find topK most frequent numbers at any point of time. You need to expose two APIs -
 *
 * void addNewNumber(Integer number),
 * List<Integer> getkMostFrequent(int k).
 */
public class FindTopK {
    public class NumFrequency {
        int num;
        int times;
        public NumFrequency(int num, int times) {
            this.num = num;
            this.times = times;
        }
    }

    private Map<Integer, NumFrequency> map;
    private List<NumFrequency> topKList;

    public FindTopK() {
        map = new HashMap<>();
        topKList = new ArrayList<>();
    }
}
