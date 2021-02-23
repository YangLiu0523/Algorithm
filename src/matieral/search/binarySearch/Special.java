package matieral.search.binarySearch;

/**
 * Test: https://leetcode.com/problems/k-th-smallest-prime-fraction/ => Double
 */

public class Special {

    public int[] kthSmallestPrimeFraction(int[] primes, int k) {
        double lo = 0, hi = 1;
        int[] ans = new int[]{0, 1};

        while (hi - lo > 1e-9) {
            double mid = lo + (hi - lo) /2.0;
            int[] res = under(mid, primes);
            if (res[0] < k) {
                lo = mid;
            }
            else {
                ans[0] = res[1];
                ans[1] = res[2];
                hi = mid;
            }
        }
        return ans;
    }

    public int[] under(double target, int[] primes) {
        int numer = 0, denom = 1, cnt = 0;
        int i = -1;
        for (int j = 1; j < primes.length; j++) {
            while (primes[i + 1] < primes[j] * target) i++;

            cnt += (i + 1);
            if (i >= 0 && numer * primes[j] < primes[i] * denom) {
                numer = primes[i];
                denom = primes[j];
            }
        }
        return new int[]{cnt, numer, denom};
    }
}
