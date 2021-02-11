package matieral.dp;

/**
 * Test: https://leetcode.com/problems/paint-fence/
 * Test: https://leetcode.com/problems/house-robber/
 *
 */

public class DependsOnMoreThanOnePreviousStatus {
    public int numWays(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        int[] dp = new int[n];
        dp[0] = k;
        dp[1] = k * k;
        for (int i = 2; i < n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) * (k - 1);
        }
        return dp[n - 1];
    }

    public int rob(int[] nums) {
        int prev = 0, curr = 0;
        for (int num : nums) {
            int tmp = curr;
            curr = Math.max(num + prev, curr);
            prev = tmp;
        }
        return curr;
    }
}
