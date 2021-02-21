package matieral.math;

import java.util.*;

/**
 * Test: https://leetcode.com/problems/count-ways-to-make-array-with-product/
 * Test: https://leetcode.com/problems/count-primes/
 * Test: https://leetcode.com/problems/factor-combinations/
 */

public class Prime {
    public int countPrimes(int n) {
        boolean[] marked = new boolean[n];
        for(int i = 2; i * i < n; i++) {
            if (!marked[i]) {
                for (int j = i* i; j < n; j += i) {
                    marked[j] = true;
                }
            }
        }

        int cnt = 0;
        for (int i = 2; i < n; i++) { //从2开始算
            if (!marked[i]) cnt++;
        }
        return cnt;
    }

    public List<Integer> primeDecompose(int num) {
        List<Integer> primeFactors = new ArrayList<Integer>();
        int factor = 2;

        while (factor * factor <= num) {
            if (num % factor == 0) {
                primeFactors.add(factor);
                num = num / factor;
            } else {
                factor++;
            }
        }

        primeFactors.add(num);
        return primeFactors;
    }

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(n, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int n, List<Integer> tmp, List<List<Integer>> res) {
        if (n == 1) {
            if(tmp.isEmpty()) return;
            res.add(new ArrayList<>(tmp));
            return;
        }
        else if (!tmp.isEmpty()) {
            tmp.add(n);
            res.add(new ArrayList<>(tmp));
            tmp.remove(tmp.size() - 1);
        }

        int st = tmp.isEmpty() ? 2 : tmp.get(tmp.size() - 1);
        for (int i = st; i * i <= n; i++) {
            if (n % i == 0) {
                tmp.add(i);
                backtrack(n / i, tmp, res);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    int MOD = 1000000007;
    public int[] waysToFillArray(int[][] queries) {
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int size = queries[i][0], prod = queries[i][1];
            Map<Integer, Integer> primeFacs = decomposit(prod);
            // System.out.println(primeFacs);
            long ways = 1;
            for (Map.Entry<Integer, Integer> e : primeFacs.entrySet()) {
                ways = (ways * H(e.getValue(), size)) % MOD;
            }
            ans[i] =(int)ways;
        }
        return ans;
    }

    private Map<Integer, Integer> decomposit(int n) {
        if (n == 1) {
            return  new HashMap<>();
        }

        Map<Integer, Integer> ans = new HashMap<>();
        int fac = 2;
        while (fac * fac <= n) {
            if (n % fac == 0) {
                ans.put(fac, ans.getOrDefault(fac, 0) + 1);
                n /= fac;
            }
            else {
                fac++;
            }
        }
        ans.put(n, ans.getOrDefault(n, 0) + 1);
        return ans;
    }

    private long H(int k, int n) {
        return C(k, k + n - 1);
    }

    Map<Integer, Map<Integer, Long>> memo = new HashMap<>();
    private long C(int k, int n) {
        if (memo.containsKey(k) && memo.get(k).containsKey(n)) {
            return memo.get(k).get(n);
        }
        if (k > n) return 0;
        if (k == 0) return 1;
        long val = (C(k - 1, n - 1) + C(k, n - 1)) % MOD;
        memo.computeIfAbsent(k, v -> new HashMap<>()).put(n, val);
        return val;
    }

    public static void main(String[] args) {
        Prime test = new Prime();
        System.out.println(test.primeDecompose(16));
    }
}
