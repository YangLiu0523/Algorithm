package matieral.dp;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/maximal-square/
 * Test: https://leetcode.com/problems/maximal-rectangle/
 * Test: https://leetcode.com/problems/largest-plus-sign/
 *
 */

public class MaxArea {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int maxEdge = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c = matrix[i - 1][j - 1];
                if (c == '1') {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                    maxEdge = Math.max(maxEdge, dp[i][j]);
                }
            }
        }
        return maxEdge * maxEdge;
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1 + (j - 1 >= 0 ? dp[i][j - 1] : 0);
                }
            }
        }
        int maxArea = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1 + ((j - 1) >= 0 ? dp[i][j - 1] : 0);
                    int curr = dp[i][j];
                    for (int k = i; k >= 0; k--) {
                        curr = Math.min(curr, dp[k][j]);
                        int area = curr * (i - k + 1);
                        maxArea = Math.max(maxArea, area);
                    }
                }
            }
        }

        return maxArea;
    }

    public int orderOfLargestPlusSign(int N, int[][] mines) {
        Set<Integer> zeros = new HashSet<>();
        for (int[] mine : mines) {
            zeros.add(mine[0] * N + mine[1]);
        }

        int ans = 0, cnt = 0;
        int[][] minContinueOne = new int[N][N];
        for (int r = 0; r < N; r++) {
            cnt = 0;
            for (int c = 0; c < N; c++) {
                if (zeros.contains(r * N + c)) cnt = 0;
                else cnt++;
                minContinueOne[r][c] = cnt;
            }

            cnt = 0;
            for (int c = N - 1; c >= 0; c--) {
                if (zeros.contains(r * N + c)) cnt = 0;
                else cnt++;
                minContinueOne[r][c] = Math.min(minContinueOne[r][c], cnt);
            }
        }

        for (int c = 0; c < N; c++) {
            cnt = 0;
            for (int r = 0; r < N; r++) {
                if (zeros.contains(r * N + c)) cnt = 0;
                else cnt++;
                minContinueOne[r][c] = Math.min(minContinueOne[r][c], cnt);
            }

            cnt = 0;
            for (int r = N - 1; r >= 0; r--) {
                if (zeros.contains(r * N + c)) cnt = 0;
                else cnt++;
                minContinueOne[r][c] = Math.min(minContinueOne[r][c], cnt);
                ans = Math.max(ans, minContinueOne[r][c]);
            }
        }
        return ans;
    }
}
