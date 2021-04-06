package matieral.search.backtracking;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/ones-and-zeroes/
 * Test: https://leetcode.com/problems/maximize-score-after-n-operations/
 */
public class PrevStates {
    public int findMaxForm(String[] strs, int zeros, int ones) {
        int n = strs.length;
        int[][] cnt = new int[2][n];
        for (int i = 0; i < n; i++) {
            String s = strs[i];
            for (char c : s.toCharArray()) {
                cnt[c - '0'][i]++;
            }
        }

        Integer[][][] memo = new Integer[n][zeros + 1][ones + 1];
        return search(cnt, memo, 0, 0, 0);
    }

    private int search(int[][] cnt, Integer[][][] memo, int idx, int zeros, int ones) {
        if (idx == memo.length) {
            return 0;
        }
        if (memo[idx][zeros][ones]!= null) {
            return memo[idx][zeros][ones];
        }
        int take = 0;
        if (zeros + cnt[0][idx] < memo[0].length && ones + cnt[1][idx] < memo[0][0].length) {
            take = 1 + search(cnt, memo, idx + 1, zeros + cnt[0][idx], ones + cnt[1][idx]);
        }
        int notTake = search(cnt, memo, idx + 1, zeros, ones);
        memo[idx][zeros][ones] = Math.max(take, notTake);
        return memo[idx][zeros][ones];
    }

    public int maxScore(int[] nums) {
        int n = nums.length;
        int[][] val = new int[n][n];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                val[i][j] = gcd(nums[i], nums[j]);
            }
        }
        Map<Integer, Integer> memo = new HashMap<>();
        return backtrack(val, n / 2, 0, memo);
    }

    private int backtrack(int[][] val, int k, int visited, Map<Integer, Integer> memo) {
        if (k == 0) {
            return 0;
        }
        if (memo.containsKey(visited)) {
            return memo.get(visited);
        }
        int max = 0;
        for (int i = 0; i < val.length; i++) {
            for (int j = i + 1; j < val.length; j++) {
                int curr = (1 << i) | (1 << j);
                if ((visited & curr) == 0) {
                    visited |= curr;

                    int sub = k * val[i][j] + backtrack(val, k - 1, visited, memo);
                    max = Math.max(sub, max);

                    visited &= (~curr);
                }
            }
        }
        memo.put(visited, max);
        return max;
    }

    private int gcd(int a, int b) {
        return b > 0 ? gcd(b, a % b) : a;
    }
}
