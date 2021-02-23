package matieral.minimax;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/stone-game/
 * Test: https://leetcode.com/problems/stone-game-ii/
 * Test: https://leetcode.com/problems/stone-game-iii/
 * Test: https://leetcode.com/problems/stone-game-iv/
 */
public class StoneGame {
    public boolean stoneGame(int[] piles) {
        int N = piles.length;

        // dp[i+1][j+1] = the value of the game [piles[i], ..., piles[j]].
        int[][] dp = new int[N+2][N+2];
        for (int size = 1; size <= N; ++size)
            for (int i = 0; i + size <= N; ++i) {
                int j = i + size - 1;
                int parity = (j + i + N) % 2;  // j - i - N; but +x = -x (mod 2)
                if (parity == 1)
                    dp[i+1][j+1] = Math.max(piles[i] + dp[i+2][j+1], piles[j] + dp[i+1][j]);
                else
                    dp[i+1][j+1] = Math.min(-piles[i] + dp[i+2][j+1], -piles[j] + dp[i+1][j]);
            }

        return dp[1][N] > 0;
    }

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] preSum = new int[n];
        preSum[n - 1] = piles[n - 1];
        for (int i = n - 2 ;i >= 0; i--) {
            preSum[i] = piles[i] + preSum[i + 1];
        }
        return dfs2(preSum, 0, 1, new int[n][n]);
    }

    private int dfs2(int[] presum, int p, int m, int[][] memo) {
        if (p + 2 * m >= presum.length) {
            return presum[p];
        }
        if (memo[p][m] > 0) return memo[p][m];

        int res = 0;
        for (int i = 1; i <= 2 * m; i++) {
            int take = presum[p] - presum[p + i];
            res = Math.max(res, take + presum[p + i] - dfs2(presum, p + i, Math.max(i, m), memo));
        }
        memo[p][m] = res;
        return res;
    }

    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] presum = new int[n];
        presum[n - 1] = stoneValue[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            presum[i] = presum[i + 1] + stoneValue[i];
        }

        Integer[] memo = new Integer[n];
        int alice = dfs3(presum, 0, memo);
        int bob = presum[0] - alice;


        if (alice > bob) {
            return "Alice";
        }
        else if (alice < bob) {
            return "Bob";
        }
        else {
            return "Tie";
        }
    }

    private int dfs3(int[] presum, int idx, Integer[] memo) {
        if (idx >= presum.length) {
            return 0;
        }
        if (memo[idx] != null) {
            return memo[idx];
        }

        int val = Integer.MIN_VALUE;
        for (int i = 1; i <= 3; i++) {
            if (idx + i < presum.length) {
                int take = presum[idx] - presum[idx + i];
                val = Math.max(val, take + presum[idx + i] - dfs3(presum, idx + i, memo));
            }
            else {
                int take = presum[idx];
                val = Math.max(val, take + dfs3(presum, idx + i, memo));
            }
        }

        memo[idx] = val;
        return val;
    }

    public boolean winnerSquareGame(int n) {
        Boolean[] dp = new Boolean[n + 1];
        dp[0] = false;
        return game(dp, n);
    }

    private boolean game(Boolean[] dp, int num) {
        if (dp[num] != null) {
            return dp[num];
        }

        for (int i = 1; i * i <= num; i++) {
            if (!game(dp, num - i* i)) {
                dp[num] = true;
                return true;
            }
        }
        dp[num] = false;
        return false;
    }
}
