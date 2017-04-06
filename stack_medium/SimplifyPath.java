// Source : https://oj.leetcode.com/problems/simplify-path/
// Date   : 04/06/2017
/**
 * ********************************************************************************
 *
 * Given an absolute path for a file (Unix-style), simplify it.
 *
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 *
 *
 * Corner Cases:
 *
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 *
 *
 *********************************************************************************
 */
package Leetcode_Java.stack_medium;

import java.util.Stack;

/**
 *
 * @author Borui Wang
 */
public class SimplifyPath {

//引用： http://bangbingsyb.blogspot.com/2014/11/leetcode-simplify-path.html
//Unix的path规则可以在这里了解：
//http://en.wikipedia.org/wiki/Path_(computing)
//
//归下类的话，有四种字符串：
//1. "/"：为目录分隔符，用来分隔两个目录。
//2. "."：当前目录
//3. ".."：上层目录
//4. 其他字符串：目录名
//
//简化的核心是要找出所有的目录，并且如果遇到".."，需要删除上一个目录。
//把字符串先由"/"分开来，分成很多token
//如果一个token是"..",".","" 这三种情况的话，我们就不入栈
//不然就把当前token入栈
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack();

        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if (dir.equals("..") || dir.equals(".") || dir.equals("")) {
                continue;
            } else {
                stack.push(dir);
            }

        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, "/" + stack.pop());
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }

    public static void main(String[] args) {
        String s = "/home/foo";
        for (String st : s.split("/")) {
            System.out.println(st);
        }
    }
}
