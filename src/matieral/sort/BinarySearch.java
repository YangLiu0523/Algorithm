package matieral.sort;

/**
 * Test: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * Test: https://leetcode.com/problems/k-th-smallest-prime-fraction/
 *
 */

public class BinarySearch {
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

    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums == null || nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return res;
        }

        // floor
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left ) / 2;
            if (nums[mid] >= target) right = mid - 1;
            else left = mid + 1;
        }
        if (nums[right + 1] != target) return res;
        res[0] = right + 1;


        left = 0; right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) /2;
            if (nums[mid] <= target) left = mid + 1;
            else right = mid - 1;
        }
        res[1] = left - 1;

        return res;
    }
}
