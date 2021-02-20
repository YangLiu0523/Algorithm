package matieral.math;

import java.util.*;

/**
 * Test: https://leetcode.com/problems/count-ways-to-make-array-with-product/
 * Test: https://leetcode.com/problems/count-primes/
 * Test: https://leetcode.com/problems/factor-combinations/
 */

public class Prim {
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

    public static void main(String[] args) {
        Prim test = new Prim();
        System.out.println(test.primeDecompose(16));
    }
}
