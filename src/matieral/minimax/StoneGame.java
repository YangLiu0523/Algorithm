package matieral.minimax;

/**
 * Test: https://leetcode.com/problems/stone-game-iv/
 */
public class StoneGame {
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
