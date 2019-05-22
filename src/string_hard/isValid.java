// Source : https://oj.leetcode.com/problems/valid-number/
// Date   : 03/21/2017

/********************************************************************************** 
* 
* Validate if a given string is numeric.
* 
* Some examples:
* "0" => true
* "   0.1  " => true
* "abc" => false
* "1 a" => false
* "2e10" => true
* 
* Note: It is intended for the problem statement to be ambiguous. 
*       You should gather all requirements up front before implementing one.
* 
*               
**********************************************************************************/

package Leetcode_Java.string_hard;

/**
 *
 * @author Borui Wang
 */
public class isValid {
    public boolean isNumber(String s) {
        s = s.trim();
        //we have not seen a number yet
        boolean seenNum = false;
        //we have not seen e yet
        boolean seenE = false;
        //we have not see "." yet
        boolean seenDot = false;
        //we have no e, therefore, we do not need number after e
        boolean numAfterE = true;
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                if(!seenNum) {
                    seenNum = true;
                }
                if(!numAfterE) {
                    numAfterE = true;
                }
            } else if(c == '+' || c == '-') {
                if(i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            } else if(c == 'e') {
                if(seenE || !seenNum || i == 0) {
                    return false;
                }
                seenE = true;
                numAfterE = false;
            } else if(c == '.') {
                if(seenE || seenDot) {
                    return false;
                }
                seenDot = true;
            } else {
                return false;
            }
        }
        //we have seen a number and any number after e has been fulfilled
        return numAfterE && seenNum;
    }
}
