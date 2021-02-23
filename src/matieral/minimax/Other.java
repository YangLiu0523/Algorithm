package matieral.minimax;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/escape-the-ghosts/
 * Test: https://leetcode.com/problems/guess-the-word/
 * Test: https://leetcode.com/problems/guess-number-higher-or-lower-ii/
 */

public class Other {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int dist = Math.abs(target[0]) + Math.abs(target[1]);
        for (int[] g : ghosts) {
            int d = Math.abs(g[0] - target[0]) + Math.abs(g[1] - target[1]);
            if (d <= dist) return false;
        }
        return true;
    }

    public void findSecretWord(String[] wordlist, Master master) {
        for (int t = 0; t < 10; t++) {
            int[][] count = new int[6][26];
            for (String word : wordlist) {
                for (int i = 0; i < 6; i++) {
                    count[i][word.charAt(i) - 'a']++;
                }
            }
            String guess = wordlist[0];
            int best = 0;
            for (String word : wordlist) {
                int score = 0;
                for (int i = 0; i < 6; i++) {
                    score += count[i][word.charAt(i) - 'a'];
                }
                if (score > best) {
                    best = score;
                    guess = word;
                }
            }

            int x = master.guess(guess);
            List<String> list2 = new ArrayList<>();
            for (String word : wordlist) {
                if (match(word, guess) == x) { // Important
                    list2.add(word);
                }
            }
            wordlist = list2.toArray(new String[0]);
        }
    }
    public int match(String a, String b) {
        int matches = 0;
        for (int i = 0; i < a.length(); ++i)
            if (a.charAt(i) == b.charAt(i))
                matches ++;
        return matches;
    }

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];

        for (int l = 2; l <= n; l++) {
            for (int i = 1, j = i + l - 1; j <= n; i++, j++) {
                if (l == 2) {
                    dp[i][j] = i;
                }
                else {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i + 1; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], k + Math.max(dp[i][k - 1], dp[k + 1][j]));
                    }
                }
            }
        }

        return dp[1][n];
    }
}
class Master {
    public int guess(String word) {return -1;}
}