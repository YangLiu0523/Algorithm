package matieral.string;

/**
 * Test: https://leetcode.com/problems/repeated-substring-pattern/
 */
public class RepeatSubStrPattern {
    public boolean repeatedSubstringPattern(String s) {
        return (s + s).substring(1, 2 * s.length() - 1).contains(s);
    }
}
