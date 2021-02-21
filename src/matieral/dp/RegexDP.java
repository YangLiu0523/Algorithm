package matieral.dp;

/**
 * Test: https://leetcode.com/problems/regular-expression-matching/
 * Test: https://leetcode.com/problems/wildcard-matching/
 */

public class RegexDP {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            char c = p.charAt(i - 1);
            if (i + 1 <= p.length() && p.charAt(i) == '*') {
                i++;
                if (i - 2 >= 0) {
                    dp[i][0] = dp[i - 2][0];
                }
                for (int j = 1; j <= s.length(); j++) {
                    dp[i][j] = (dp[i][j - 1] || dp[i - 2][j - 1]) && (c == '.' || c == s.charAt(j - 1)) || dp[i - 2][j];
                }
            }
            else {
                for (int j = 1; j <= s.length(); j++) {
                    dp[i][j] = dp[i - 1][j - 1] && (c == '.' || c == s.charAt(j - 1));
                }
            }
        }
        return dp[p.length()][s.length()];
    }

    public boolean isMatch2(String s, String p) {
        int slen = s.length(), plen = p.length();
        boolean[][] dp = new boolean[plen + 1][slen + 1];
        dp[0][0] = true;

        for (int i = 1; i <= plen; i++) {
            char c = p.charAt(i - 1);

            if (c == '*') {
                dp[i][0] = dp[i - 1][0];
                for (int j = 1; j <= slen; j++) {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
            else {
                for (int j = 1; j <= slen; j++) {
                    dp[i][j] = (s.charAt(j - 1) == c || c == '?') && dp[i - 1][j - 1];
                }
            }
        }

        return dp[plen][slen];
    }
}
