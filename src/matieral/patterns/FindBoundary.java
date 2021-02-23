package matieral.patterns;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Test: https://leetcode.com/problems/sum-of-subarray-minimums/ => Like
 * Test: https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/
 * Test: https://leetcode.com/problems/sum-of-subsequence-widths/ => Like!
 */

public class FindBoundary {
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1000000007, n = arr.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            prev[i] = stack.isEmpty()? -1 : stack.peek();
            stack.push(i);
        }

        stack = new ArrayDeque<>();
        int[] next = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            next[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            long add = (long)(i - prev[i]) * (next[i] - i) % MOD * arr[i] % MOD;
            ans = (ans + add) % MOD;
        }
        return (int)ans;
    }

    int MOD = 1000000007;
    public int uniqueLetterString(String S) {
        int[][] idx = new int[26][2];
        for (int i = 0; i < 26; i++) {
            idx[i][0] = idx[i][1] = -1;
        }

        long ret = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            ret = (ret + (i - idx[c - 'A'][1]) * (idx[c - 'A'][1] - idx[c - 'A'][0]) % MOD) % MOD;
            idx[c- 'A'][0] = idx[c - 'A'][1];
            idx[c - 'A'][1] = i;
        }

        int n = S.length();
        for (int i = 0; i < 26; i++) {
            ret = (ret + (idx[i][1] - idx[i][0]) * (n - idx[i][1]) % MOD) % MOD;
        }
        return (int)ret;
    }

    public int sumSubseqWidths(int[] A) {
        int N = A.length;
        Arrays.sort(A);

        long[] pow2 = new long[N];
        pow2[0] = 1;
        for (int i = 1; i < N; ++i)
            pow2[i] = (pow2[i-1] * 2) % MOD;

        long ret = 0;
        for (int i = 0; i < N; i++) {
            ret = (ret + (A[i] * (-pow2[N - i - 1] + pow2[i]) % MOD) % MOD) % MOD;
        }
        return (int)ret;
    }
}
