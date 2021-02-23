package matieral.dp;

/**
 * Test: https://leetcode.com/problems/unique-paths-ii/
 * Test: https://leetcode.com/problems/combination-sum-iv/ => Like
 */
public class CollectPath {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) return 0;
        int[] dp = new int[n + 1];
        dp[1] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j + 1] = 0;
                }
                else {
                    dp[j + 1] += dp[j];
                }
            }
        }
        return dp[n];
    }

    // Combos(target) = sum (combos(target - nums[i]))
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) { // Order matters!
            for (int j = 0; j < nums.length && nums[j] <= i; j++) {
                dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }
}
