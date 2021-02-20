package matieral.search.topical_program;

import java.util.*;

/**
 * Test: https://leetcode.com/problems/partition-equal-subset-sum/
 * Test: https://leetcode.com/problems/maximum-subarray/
 * Test: https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/
 *
 * Test: https://leetcode.com/problems/subarray-sum-equals-k/ => Love it!
 * Test: https://leetcode.com/problems/continuous-subarray-sum/ => Cute question
 * Test: https://leetcode.com/problems/subarray-sums-divisible-by-k/
 * Test: https://leetcode.com/problems/make-sum-divisible-by-p/
 */

public class SubArray {
    public boolean canPartition(int[] nums) {
        int total = Arrays.stream(nums).sum();
        if (total % 2 != 0) {
            return false;
        }
        int target = total / 2;

        Arrays.sort(nums);
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for(int j = target; j >= num; j--) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }

    public int maxSubArray(int[] nums) {
        int maxSum = nums[0], sum = nums[0]; // Error prone
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public int maxAbsoluteSum(int[] nums) {
        int small = nums[0], big = nums[0], maxSum = Math.abs(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            small = Math.min(nums[i], nums[i] + small);
            big = Math.max(nums[i], nums[i] + big);
            maxSum = Math.max(maxSum, Math.max(Math.abs(small), Math.abs(big)));
        }
        return maxSum;
    }


    public int subarraySumNSquare(int[] nums, int k) {
        int cnt = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum == k) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

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

    public boolean checkSubarraySumNSquare(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < n; j++) {
                sum += nums[j];
                if (k == 0) {
                    if (sum == 0) return true;
                }
                else if (sum % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

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
