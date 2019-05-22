//Source : https://leetcode.com/problems/expression-add-operators/
//Date   : 02/15/2017

/*******************************************************************************
Given a string that contains only digits 0-9 and a target value, 
* return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []
Credits:
Special thanks to @davidtan1890 for adding this problem and creating all test cases.
 *******************************************************************************
 */
package Leetcode_Java.stack_hard;

import java.util.List;

/**
 *
 * @author Borui Wang
 */
public class AddOperators {
       
    static List<String> res;
    
    static List<String> addOperators(String num, int target) {
        helper(num, target, "", 0, 0);
        return res;
    }
    
    static void helper(String num, int target, String tmp, long currRes, long prevNum){
        // 如果计算结果等于目标值，且所有数都用完了，则是有效结果
        if(currRes == target && num.length() == 0){
            String exp = new String(tmp);
            res.add(exp);
            return;
        }
        // 搜索所有可能的拆分情况
        for(int i = 1; i <= num.length(); i++){
            String currStr = num.substring(0, i);
            // 对于前导为0的数予以排除
            if(currStr.length() > 1 && currStr.charAt(0) == '0'){
                // 这里是return不是continue
                return;
            }
            // 得到当前截出的数
            long currNum = Long.parseLong(currStr);
            // 去掉当前的数，得到下一轮搜索用的字符串
            String next = num.substring(i);
            // 如果不是第一个字母时，可以加运算符，否则只加数字
            if(tmp.length() != 0){
                // 乘法
                helper(next, target, tmp+"*"+currNum, (currRes - prevNum) + prevNum * currNum, prevNum * currNum);
                // 加法
                helper(next, target, tmp+"+"+currNum, currRes + currNum, currNum);
                // 减法
                helper(next, target, tmp+"-"+currNum, currRes - currNum, -currNum); 
            } else {
                // 第一个数
                helper(next, target, currStr, currNum, currNum);
            }

        }
    }
    public static void main(String [] args) {
        
    }
}
