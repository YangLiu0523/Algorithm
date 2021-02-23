package matieral.topical_program;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/unique-paths/
 * Test: https://leetcode.com/problems/unique-paths-ii/ => Like
 * Test: https://leetcode.com/problems/unique-paths-iii/
 */

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] +=dp[j - 1];
            }
        }
        return dp[n -1];
    }

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

    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][] visited;

    public int uniquePathsIII(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        visited = new boolean[m][n];

        int startR = -1, startC = -1;
        int zeros = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    startR = i;
                    startC = j;
                }
                if (grid[i][j] == 0) {
                    zeros++;
                }
            }
        }

        visited[startR][startC] = true;
        return backtrack(grid, startR, startC, zeros);
    }

    private int backtrack(int[][] grid, int i, int j, int zeros) {
        int curr = 0;
        for (int[] dir : dirs) {
            int r = dir[0] + i, c = dir[1] + j;
            if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && !visited[r][c] && grid[r][c] != -1) {
                if (grid[r][c] == 2) {
                    if (zeros == 0) curr++;
                }

                if (grid[r][c] == 0) {
                    visited[r][c] = true;
                    if (zeros - 1 >= 0) curr += backtrack(grid, r, c, zeros - 1);
                    visited[r][c] = false;
                }
            }
        }
        return curr;
    }

}
