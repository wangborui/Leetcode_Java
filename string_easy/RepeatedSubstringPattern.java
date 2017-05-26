// Source : https://leetcode.com/problems/repeated-substring-pattern/#/description
// Date   : 05/26/2017

/********************************************************************************** 
* 
* Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
* 
* For example,
* Given [100, 4, 200, 1, 3, 2],
* The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
* 
* Your algorithm should run in O(n) complexity.
* 
*               
**********************************************************************************/
package Leetcode_Java.string_easy;

/**
 *
 * @author Borui Wang
 */
public class RepeatedSubstringPattern {

    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();

        for (int i = n / 2; i >= 1; i--) {
            if (n % i == 0) {
                int times = n / i;
                String sub = s.substring(0, i);
                StringBuilder sb = new StringBuilder();

                for (int j = 0; j < times; j++) {
                    sb.append(sub);
                }

                if (sb.toString().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
}
