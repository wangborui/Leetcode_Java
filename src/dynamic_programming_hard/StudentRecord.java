// Source : https://leetcode.com/problems/student-attendance-record-i/#/description
// Date   : 05/16/2017
/**
 * *************************************************************************************
 * You are given a string representing an attendance record for a student. The record only contains the following three characters:
 *
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 * A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 *
 * You need to return whether the student could be rewarded according to his attendance record.
 *
 * Example 1:
 * Input: "PPALLP"
 * Output: True
 * Example 2:
 * Input: "PPALLL"
 * Output: False
 **************************************************************************************
 */
package Leetcode_Java.dynamic_programming_hard;

/**
 *
 * @author Borui Wang
 */
public class StudentRecord {

    public boolean checkRecord(String s) {
        if (s == null || s.length() < 2) {
            return true;
        }

        int absent = 0;
        int late = 0;

        for (char c : s.toCharArray()) {
            if (c == 'L') {
                late++;
            } else {
                late = 0;
                if (c == 'A') {
                    absent++;
                }
            }
            if (absent > 1 || late > 2) {
                return false;
            }
        }
        return true;
    }

    public boolean checkRecord2(String s) {
        return !s.matches(".*LLL.*|.*A.*A.*");
    }
}
