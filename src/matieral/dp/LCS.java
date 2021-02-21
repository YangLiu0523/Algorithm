package matieral.dp;

/**
 * Test: https://leetcode.com/problems/longest-common-subsequence/
 */
public class LCS {

    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();
        int[][] dp = new int[n2 + 1][n1 + 1];
        for (int i = 1; i <= n2; i++) {
            for (int j = 1; j <= n1; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (text1.charAt(j - 1) == text2.charAt(i - 1)) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i - 1][j - 1]);
                }
            }
        }
        return dp[n2][n1];
    }
}
