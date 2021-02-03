package matieral.dp;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/split-array-largest-sum/
 * Test:
 * Test:
 *
 */

public class FromZeroToI {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int[][] cost = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }
        cost[0][0] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k < j; k++) {
                    cost[i][j] = Math.min(cost[i][j], Math.max(cost[i - 1][k], sum[j] - sum[k]));
                }
            }
        }
        return cost[m][n];
    }
}
