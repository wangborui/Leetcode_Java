package depth_first_search;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/remove-invalid-parentheses/
 * https://leetcode.com/problems/remove-invalid-parentheses/discuss/75027/Easy-Short-Concise-and-Fast-Java-DFS-3-ms-solution
 */
public class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList();
        helper(s, res, 0, 0, '(', ')');
        return res;
    }

    public void helper(String s, List<String> res, int iStart, int jStart, char open, char close) {
        int numOpen = 0;
        int numClose = 0;

        for(int i = iStart; i < s.length(); i++) {
            if(s.charAt(i) == open) {
                numOpen++;
            }
            if(s.charAt(i) == close) {
                numClose++;
            }

            //remove the first parenthesis which caused invalidity
            if(numClose > numOpen) {
                for(int j = jStart; j <= i; j++) {
                    if(s.charAt(j) == close && (j == jStart || s.charAt(j - 1) != close)) {
                        helper(s.substring(0, j) + s.substring(j + 1, s.length()), res, i, j, open, close);
                    }
                }
                return;
            }
        }

        //all parenthesis are valid, reverse and check for the other direction, or reverse and add to results
        StringBuilder sb = new StringBuilder(s);
        s = sb.reverse().toString();
        if(open == '(') {
            helper(s, res, 0, 0, ')', '(');
        } else {
            res.add(s);
        }
    }
}
