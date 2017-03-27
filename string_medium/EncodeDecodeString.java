//Source : https://leetcode.com/problems/encode-and-decode-strings/#/description
//Date   : 03/27/2017
/**
 * *****************************************************************************
 * Design an algorithm to encode a list of strings to a string. 
 * The encoded string is then sent over the network and is decoded back to the original list of strings.
 *
 * Machine 1 (sender) has the function:
 *
 * string encode(vector<string> strs) { // ... your code return encoded_string;
 * } Machine 2 (receiver) has the function: vector<string> decode(string s) {
 * //... your code return strs; } So Machine 1 does:
 *
 * string encoded_string = encode(strs); and Machine 2 does:
 *
 * vector<string> strs2 = decode(encoded_string); strs2 in Machine 2 should be
 * the same as strs in Machine 1.
 *
 * Implement the encode and decode methods.
 *
 * Note: The string may contain any possible characters out of 256 valid ascii
 * characters. Your algorithm should be generalized enough to work on any
 * possible characters. Do not use class member/global/static variables to store
 * states. Your encode and decode algorithms should be stateless. Do not rely on
 * any library method such as eval or serialize methods. You should implement
 * your own encode/decode algorithm.
 ******************************************************************************
 */
package Leetcode_Java.string_medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class EncodeDecodeString {
    // Encodes a list of strings to a single string.

    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            //encoded length could have multiple digits
            //if length = 13, we need to use # to separate string length
            sb.append(str.length()).append("#").append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    //"63/Rc","h","BmI3FS~J9#vmk","7uBZ?7*/","24h+X","O "
    public List<String> decode(String s) {
        List<String> res = new ArrayList();
        int i = 0;
        int n = s.length();

        while (i < n) {
            int delimiter = s.indexOf("#", i);
            int len = Integer.valueOf(s.substring(i, delimiter));
            if (len == 0) {
                res.add("");
            } else {
                String temp = s.substring(delimiter + 1, delimiter + 1 + len);
                res.add(temp);
            }
            i = delimiter + 1 + len;
        }
        return res;
    }

    public static void main(String[] args) {
        // Your Codec object will be instantiated and called as such:
        EncodeDecodeString codec = new EncodeDecodeString();
        String[] in = {"63/Rc", "h", "BmI3FS~J9#vmk", "7uBZ?7*/", "24h+X", "O "};
        List<String> input = new ArrayList(Arrays.asList(in));
        //input.add("");
        System.out.println(codec.decode(codec.encode(input)));
        System.out.println("01".subSequence(2, 2));
    }
}
