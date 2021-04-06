package matieral.dp;

/**
 * Test: https://leetcode.com/problems/ones-and-zeroes/
 */

public class knapsack2D {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s : strs) {
            int[] cnt = count(s);
            for (int zeros = m; zeros >= cnt[0]; zeros--) {
                for (int ones = n; ones >= cnt[1]; ones--) {
                    dp[zeros][ones] = Math.max(1 + dp[zeros - cnt[0]][ones - cnt[1]], dp[zeros][ones]);
                }
            }
        }
        return dp[m][n];
    }

    public int[] count(String s) {
        int[] c = new int[2];
        for (char cc : s.toCharArray()) {
            c[cc - '0']++;
        }
        return c;
    }
}
