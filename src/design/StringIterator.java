// Source : https://leetcode.com/problems/design-compressed-string-iterator/description/
// Date   : 08/19/2017
/**
 * ********************************************************************************
 * Design and implement a data structure for a compressed string iterator.
 * It should support the following operations: next and hasNext.
 *
 * The given compressed string will be in the form of each letter followed by a positive
 * integer representing the number of this letter existing in the original uncompressed string.
 *
 * next() - if the original string still has uncompressed characters,
 * return the next letter; Otherwise return a white space.
 * hasNext() - Judge whether there is any letter needs to be uncompressed.
 *
 * Note:
 * Please remember to RESET your class variables declared in StringIterator,
 * as static/class variables are persisted across multiple test cases. Please see here for more details.
 *
 * Example:
 *
 * StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
 *
 * iterator.next(); // return 'L'
 * iterator.next(); // return 'e'
 * iterator.next(); // return 'e'
 * iterator.next(); // return 't'
 * iterator.next(); // return 'C'
 * iterator.next(); // return 'o'
 * iterator.next(); // return 'd'
 * iterator.hasNext(); // return true
 * iterator.next(); // return 'e'
 * iterator.hasNext(); // return false
 * iterator.next(); // return ' '
 *********************************************************************************
 */
package Leetcode_Java.design;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Borui Wang
 */
public class StringIterator {

    private Queue<int[]> q;

    public StringIterator(String compressedString) {
        q = new LinkedList();
        int n = compressedString.length();
        char[] c = compressedString.toCharArray();
        char cur = ' ';
        int count = 0;
        int i = 0;

        while (i < n) {
            cur = c[i++];
            int j = i;
            while (j < n && Character.isDigit(c[j])) {
                count = count * 10 + (c[j++] - '0');
            }
            q.add(new int[]{cur - 'A', count});
            i = j;
            count = 0;
        }

    }

    public char next() {
        if (q.isEmpty()) {
            return ' ';
        } else {
            int[] temp = q.peek();
            if (--temp[1] == 0) {
                q.poll();
            }
            return (char) ('A' + temp[0]);
        }
    }

    public boolean hasNext() {
        return !q.isEmpty();
    }

    public static void main(String[] args) {
        StringIterator obj = new StringIterator("L1e2t1C1o1d1e1");

    }
}
