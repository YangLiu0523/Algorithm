package matieral.math;

/**
 * Test: https://leetcode.com/problems/count-ways-to-make-array-with-product/
 * Test: https://leetcode.com/problems/prime-arrangements/
 */

import java.util.*;

public class PermutationFormula {
    Map<Integer, Map<Integer, Integer>> memo = new HashMap<>();
    private int C(int k, int n) {
        if (memo.containsKey(k) && memo.get(k).containsKey(n)) {
            return memo.get(k).get(n);
        }
        if (k > n) return 0;
        if (k == 0) return 1;
        int val = (C(k - 1, n - 1) + C(k, n - 1));
        memo.computeIfAbsent(k, v -> new HashMap<>()).put(n, val);
        return val;
    }

    public int H (int k, int n) {
        return C(k, n + k - 1);
    }

    public long A(int k, int n) {
        if (k > n) return 0;
        if (k == 0) return 1;
        if (k == 1) return n;

        long val = (A(k - 1, n - 1) * A(1, n));
        return val;
    }
}
