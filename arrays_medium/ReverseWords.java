/*
Source : http://www.lintcode.com/en/problem/reverse-words-in-a-string/#
Date   : 01/18/2017

*********************************************************************************
Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Have you met this question in a real interview? Yes
Clarification
What constitutes a word?
A sequence of non-space characters constitutes a word.
Could the input string contain leading or trailing spaces?
Yes. However, your reversed string should not contain leading or trailing spaces.
How about multiple spaces between two words?
Reduce them to a single space in the reversed string.
*********************************************************************************
 */
package Leetcode_Java.arrays_medium;

/**
 *
 * @author Borui Wang
 */
public class ReverseWords {
        /**
     * @param s : A string
     * @return : A string
     */
    
//Pitfalls: 
//      1.)if string = ""[][][](3 spaces)"", the string.split("" ""); will result in array size of 0, 
//      2.)if we have 2 spaces before the char like so s = ""[][](2 spaces)s"", then string.split("" "") will result in array size of 3, counting the 2 spaces 
//      3.)if we have spaces between chars like so s = ""a(3spaces)b"", then string. split("" "") will result in array size of {""a"","""","""",""b""}
//We only want to have result string to have no leading or trailing spaces and only one space between each word, so make sure to to check for extra space


     static String reverseWords(String s) {
       /**
        * Note: if s = "     ";
        * s.split(" ") will result in string array of 5 "" elements
        * the same as s [] = {"","","","","",};
        */
        String[] array = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = array.length - 1; i >= 0; --i) {
            if (!array[i].equals("")) {
                sb.append(array[i]).append(" ");
            }
        }

        //remove the last " ", and also check if s = "    "
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }
 
    public static void main(String [] args) {
//        System.out.println(reverseWords( "the sky is blue"));
//        System.out.println(reverseWords("     the sky is blue     "));
        System.out.println(reverseWords("a   b"));
    }
}
