package matieral.dp;

import java.util.Arrays;

/**
 * Test: https://leetcode.com/problems/longest-valid-parentheses/
 *
 * Test: https://leetcode.com/problems/palindrome-partitioning-ii/ => Like
 * Test: https://leetcode.com/problems/palindrome-partitioning-iii/ => Like
 */
public class HardTransferStatus {
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

    public int minCut(String s) {
        int n = s.length();
        boolean[][] valid = new boolean[n][n];
        for (int l = 1; l <= n; l++) {
            for (int i = 0, j = i + l - 1; j < n; i++, j++) {
                valid[i][j] = s.charAt(i) == s.charAt(j) && ((j <= i + 2) || valid[i + 1][j - 1]);
            }
        }

        int[] cut = new int[n];
        for (int i = 0; i < n; i++) {
            if (valid[0][i]) {
                cut[i] = 0;
                continue;
            }
            cut[i] = 1 + cut[i - 1];
            for (int j = 0; j < i; j++) {
                if(valid[j + 1][i]) {
                    cut[i] = Math.min(cut[i], cut[j] + 1);
                }
            }
        }
        return cut[n - 1];

    }

    public int palindromePartition(String s, int k) {
        int n = s.length();
        int[][] changes = new int[n][n];
        for (int l = 2; l <= n; l++) {
            for (int i = 0, j = l - 1 - i; j < n; i++, j++) {
                changes[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;
                if (j - i > 2) {
                    changes[i][j] += changes[i + 1][j - 1];
                }
            }
        }

        // dp[i][k] := min cost to make k strings using first i letters
        // dp[0][1] = cost(0, i)
        // dp[i][k] = min{dp[j][k - 1] + cost(j + i, i)}

        // States/Subproblems:
        // 1. length i: relies on
        int[][] dp = new int[n][k + 1];
        for (int[] arr : dp) Arrays.fill(arr, 200);

        for (int i = 0; i < n; i++) {
            dp[i][1] = changes[0][i];
            for (int kk = 2; kk <= k; kk++) {
                for (int j = 0; j < i; j++) {
                    dp[i][kk] = Math.min(dp[i][kk], dp[j][kk - 1] + changes[j + 1][i]);
                }
            }
        }

        return dp[n - 1][k];
    }

}
