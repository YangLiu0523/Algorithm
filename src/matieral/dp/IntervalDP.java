package matieral.dp;

/**
 * Test: https://leetcode.com/problems/minimum-cost-to-merge-stones/
 * Test: https://leetcode.com/problems/burst-balloons/
 */

import java.util.*;
public class IntervalDP {
    public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if ((n - 1) % (K - 1) != 0) {
            return -1;
        }

        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + stones[i];
        }

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 0, j = i + l - 1; j < n; i++, j++) {
                for (int m = i; m < j; m += (K -1)) {
                    int val = dp[m + 1][j] != Integer.MAX_VALUE ? dp[i][m] + dp[m + 1][j]: Integer.MAX_VALUE;
                    dp[i][j] = Math.min(dp[i][j], val);
                }
                if ((l - 1) % (K - 1) == 0) dp[i][j] += (sum[j + 1] - sum[i]);
            }
        }
        return dp[0][n - 1];
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] padding = new int[n + 2];
        for (int i = 0; i < n; i++) {
            padding[i + 1] = nums[i];
        }
        padding[0] = 1;
        padding[n + 1] = 1;

        int[][] dp = new int[n + 2][n + 2];
        for (int l = 1; l <= n; l++) {
            for (int i = 1, j = l + i - 1; j < n + 1; i++, j++) {
                for (int k = i; k <= j; k++) {
                    int val = dp[i][k - 1] + dp[k + 1][j] + padding[i - 1] * padding[k] * padding[j + 1];
                    dp[i][j] = Math.max(dp[i][j], val);
                }
            }
        }
        return dp[1][n];
    }
}
