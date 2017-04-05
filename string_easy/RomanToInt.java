// Source : https://oj.leetcode.com/problems/roman-to-integer/
// Date   : 04/05/2017
/**
 * ********************************************************************************
 *
 * Given a roman numeral, convert it to an integer.
 *
 * Input is guaranteed to be within the range from 1 to 3999.
 *
 *********************************************************************************
 */
package Leetcode_Java.string_easy;

/**
 *
 * @author Borui Wang
 */
public class RomanToInt {
//首先把末尾字母转换成数字
//使用两个变量记录当前值和之前值
//如果当前值小于之前值，就把的当前值从结果中减去
//不然把当前值加入到结果中
    static int romanToInt(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int result = 0;
        int last = 0;
        for (int i = n - 1; i >= 0; i--) {
            int val = getVal(s.charAt(i));
            if (val < last) {
                result -= val;
            } else {
                result += val;
            }
            last = val;
        }
        return result;
    }

    static int getVal(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }

    }

    public static void main(String[] args) {
        System.out.println(romanToInt("LXVII"));
    }
}
