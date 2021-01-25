package matieral.math;

/**
 * Test: https://leetcode.com/problems/count-ways-to-make-array-with-product/
 * Test: https://leetcode.com/problems/prime-arrangements/
 * Test: https://leetcode.com/problems/count-ways-to-make-array-with-product/
 */

import java.util.*;

public class PermutationFormula {
    Map<String, Integer> memo = new HashMap<>();

    public int C (int k, int n) {
        String key = k + "-" + n;
        if (memo.containsKey(key)) return memo.get(key);
        if (k > n) return 0;
        if (k == 0) return 1;

        int val = C(k - 1, n - 1)  + C(k, n - 1) ;
        memo.put(key, val);
        return val;
    }

    public int H (int k, int n) {
        return C(k, n + k - 1);
    }

    public long A(int k, int n) {
        if (k > n) return 0;
        if (k == 1) return n;
        if (k == 0) return 1;

        long val = (A(k - 1, n - 1) * A(1, n));
        return val;
    }
}
