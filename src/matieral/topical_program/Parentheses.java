package matieral.topical_program;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 * Test: https://leetcode.com/problems/longest-valid-parentheses/ => Like
 */
public class Parentheses {
    public String minRemoveToMakeValid(String s) {
        int left = 0, right = 0;
        Set<Integer> removed = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else if (s.charAt(i) == ')') right++;
            if (right > left) {
                right--;
                removed.add(i);
            }
        }
        left = 0;
        right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) =='(') left++;
            else if (s.charAt(i) == ')') right++;
            if (left > right) {
                left--;
                removed.add(i);
            }
        }
        // System.out.println(removed);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (removed.contains(i)) continue;
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public int longestValidParentheses(String s) {
        int maxLen = 0, n = s.length();
        int[] dp = new int[n];

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }

}
