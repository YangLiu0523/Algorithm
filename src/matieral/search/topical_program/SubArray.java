package matieral.search.topical_program;

import java.util.*;

/**
 * Test: https://leetcode.com/problems/partition-equal-subset-sum/
 * Test: https://leetcode.com/problems/maximum-subarray/ => Like!
 * Test: https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/
 *
 * Test: https://leetcode.com/problems/subarray-sum-equals-k/ => Love it!
 * Test: https://leetcode.com/problems/continuous-subarray-sum/ => Cute question
 * Test: https://leetcode.com/problems/subarray-sums-divisible-by-k/
 * Test: https://leetcode.com/problems/make-sum-divisible-by-p/
 *
 * Test: https://leetcode.com/problems/split-array-with-equal-sum/
 * Test: https://leetcode.com/problems/split-array-largest-sum/
 *
 * Test: https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
 * Test: https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/ => like
 *
 * Test:https://leetcode.com/problems/maximum-product-of-three-numbers/
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

    public boolean splitArray(int[] nums) {
        int n = nums.length;
        if (n < 7) return false;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = nums[i - 1] + sums[i - 1];
        }

        for (int j = 4; j <= n - 3; j++) {
            Set<Integer> seen = new HashSet<>();
            for (int i = 2; i < j - 1; i++) {
                if (sums[i - 1] == sums[j - 1] - sums[i]) {
                    seen.add(sums[i - 1]);
                }
            }
            for (int k = j + 2; k <= n - 1; k++) {
                if (sums[k - 1] - sums[j] == sums[sums.length - 1] - sums[k] && seen.contains(sums[k - 1] - sums[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }

        int[][] cost = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }
        cost[0][0] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k < j; k++) {
                    cost[i][j] = Math.min(cost[i][j], Math.max(cost[i - 1][k], sum[j] - sum[k]));
                }
            }
        }
        return cost[m][n];
    }

    public int maxSubArrayLen(int[] nums, int k) {
        int longest = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();//val, idx
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                longest = Math.max(longest, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return longest;
    }

    public int minOperations(int[] nums, int x) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        int n = nums.length;
        int maxi = -1;
        int left = 0;
        int current = 0;

        for (int right = 0; right < n; right++) {
            // sum([left ,..., right]) = total - x
            current += nums[right];
            // if larger, move `left` to left
            while (current > total - x && left <= right) {
                current -= nums[left];
                left += 1;
            }
            // check if equal
            if (current == total - x) {
                maxi = Math.max(maxi, right - left + 1);
            }
        }
        return maxi != -1 ? n - maxi : -1;
    }

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[nums.length - 1], nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3]);
    }
}
