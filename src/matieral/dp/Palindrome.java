package matieral.dp;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/longest-palindromic-substring/
 * Test: https://leetcode.com/problems/longest-palindromic-subsequence/
 * Test: https://leetcode.com/problems/palindrome-partitioning/
 * Test: https://leetcode.com/problems/palindrome-partitioning-ii/ => Like
 * Test: https://leetcode.com/problems/palindrome-partitioning-iii/ => Like
 * Test: https://leetcode.com/problems/palindrome-partitioning-iv/
 */

public class Palindrome {
    public String longestPalindrome(String s) {
        int n = s.length(), longest = 1, start = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 0, j = i + l - 1; j < n; i++, j++) {
                if (s.charAt(i) == s.charAt(j) && (l == 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (l > longest) {
                        longest = l;
                        start = i;
                    }
                }
            }
        }
        return s.substring(start, start + longest);
    }

    public int longestPalindromeSubseq(String s) {
        int longest = 1, n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 0, j = i + l - 1; j < n; i++,j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], 2 + dp[i + 1][j - 1]);
                }
                longest = Math.max(longest, dp[i][j]);
            }
        }

        return longest;
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        search(s, 0, new ArrayList<>(), res);
        return res;
    }

    private void search(String s, int start, List<String> path, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return ;
        }

        for (int end = start; end < s.length(); end++) {
            if (isValid(s, start, end)) {
                path.add(s.substring(start, end + 1));
                search(s, end + 1, path, res);
                path.remove(path.size() - 1);
            }
        }

    }
    private boolean isValid(String s, int l, int r) {
        while(l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public int minCut(String s) {
        int n = s.length();
        boolean[][] valid = new boolean[n][n];
        for (int l = 1; l <= n; l++) {
            for (int i = 0, j = i + l - 1; j < n; i++, j++) {
                valid[i][j] = s.charAt(i) == s.charAt(j) && ((j <= i + 2) || valid[i + 1][j - 1]);
            }
        }

        int[] cut = new int[n];
        for (int i = 0; i < n; i++) {
            if (valid[0][i]) {
                cut[i] = 0;
                continue;
            }
            cut[i] = 1 + cut[i - 1];
            for (int j = 0; j < i; j++) {
                if(valid[j + 1][i]) {
                    cut[i] = Math.min(cut[i], cut[j] + 1);
                }
            }
        }
        return cut[n - 1];

    }

    public int palindromePartition(String s, int k) {
        int n = s.length();
        int[][] changes = new int[n][n];
        for (int l = 2; l <= n; l++) {
            for (int i = 0, j = l - 1 - i; j < n; i++, j++) {
                changes[i][j] = s.charAt(i) == s.charAt(j) ? 0 : 1;
                if (j - i > 2) {
                    changes[i][j] += changes[i + 1][j - 1];
                }
            }
        }

        // dp[i][k] := min cost to make k strings using first i letters
        // dp[0][1] = cost(0, i)
        // dp[i][k] = min{dp[j][k - 1] + cost(j + i, i)}

        // States/Subproblems:
        // 1. length i: relies on
        int[][] dp = new int[n][k + 1];
        for (int[] arr : dp) Arrays.fill(arr, 200);

        for (int i = 0; i < n; i++) {
            dp[i][1] = changes[0][i];
            for (int kk = 2; kk <= k; kk++) {
                for (int j = 0; j < i; j++) {
                    dp[i][kk] = Math.min(dp[i][kk], dp[j][kk - 1] + changes[j + 1][i]);
                }
            }
        }

        return dp[n - 1][k];
    }

    public boolean checkPartitioning(String s) {
        int n = s.length();
        boolean[][] isValid = new boolean[n][n];
        for (int l = 1; l <= n; l++) {
            for (int i= 0, j = i + l - 1; j < n; i++, j++) {
                if (s.charAt(i) == s.charAt(j) && (l <= 2 || isValid[i + 1][j - 1])) {
                    isValid[i][j] = true;
                }
            }
        }

        boolean[][] dp = new boolean[n][3];
        dp[0][0] = true;
        for (int i = 1; i < n; i++) {
            dp[i][0] = isValid[0][i];
            for (int level = 1; level < 3; level++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j][level - 1] && isValid[j + 1][i]) {
                        dp[i][level] = true;
                    }
                }
            }
        }
        return dp[n - 1][2];
    }
}
