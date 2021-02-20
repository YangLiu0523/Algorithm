package matieral.patterns;

import java.util.HashMap;
import java.util.Map;

/**
 * Test: https://leetcode.com/problems/subarray-sum-equals-k/ => Love it!
 * Test: https://leetcode.com/problems/continuous-subarray-sum/ => Cute question
 * Test: https://leetcode.com/problems/subarray-sums-divisible-by-k/
 * Test: https://leetcode.com/problems/make-sum-divisible-by-p/
 */

public class AccumulatedSum {
    public int subarraySum(int[] nums, int k) {
        int cnt = 0, n = nums.length, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // Error prone!
        for (int num : nums) {
            sum += num;
            cnt += map.getOrDefault(sum - k, 0); // sum - (sum - k) = k
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);// Error prone!

        int n = nums.length, sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (k != 0) {
                sum = sum % k;
            }
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            }
            else {
                map.put(sum, i);
            }

        }
        return false;
    }

    public int subarraysDivByK(int[] A, int K) {
        int sum = 0, cnt = 0;
        Map<Integer, Integer> preValue = new HashMap<>();
        preValue.put(0, 1);
        for (int a : A) {
            sum = (a + sum) % K; // Error prone
            if (sum < 0) sum += K;
            cnt += preValue.getOrDefault(sum, 0);
            preValue.put(sum, preValue.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }

    public int minSubarray(int[] A, int p) {
        int remainder = 0;
        for (int a : A) {
            remainder = (remainder + a) % p;
        }
        if (remainder == 0) return 0;

        int removed = A.length, sum = 0;
        Map<Integer, Integer> map = new HashMap<>(); // <value, index>
        map.put(0, -1);
        for (int i = 0; i < A.length; i++) {
            sum = (A[i] + sum) % p;
            if (sum < 0) sum += p;

            int required = (sum + p - remainder) % p;
            if (map.containsKey(required)) {
                removed = Math.min(removed, i - map.get(required));
            }
            map.put(sum, i);
        }

        return removed < A.length ? removed : -1;
    }
}
