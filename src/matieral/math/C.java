package matieral.math;

/**
 * Test: https://leetcode.com/problems/count-ways-to-make-array-with-product/
 */

import java.util.*;

public class C {
    Map<String, Integer> memo = new HashMap<>();

    private int C (int k, int n) {
        String key = k + "-" + n;
        if (memo.containsKey(key)) return memo.get(key);
        if (k > n) return 0;
        if (k == 0) return 1;

        int val = C(k - 1, n - 1)  + C(k, n - 1) ;
        memo.put(key, val);
        return val;
    }
}
