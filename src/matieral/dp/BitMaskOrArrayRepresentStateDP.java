package matieral.dp;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 * Test: https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/
 * Test: https://leetcode.com/problems/can-i-win/
 * Test: https://leetcode.com/problems/stickers-to-spell-word/
 * Test: https://leetcode.com/problems/parallel-courses/
 */

public class BitMaskOrArrayRepresentStateDP {

    // https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0, maxNum = 0;
        for (int num : nums) {
            sum += num;
            maxNum = Math.max(maxNum, num);
        }
        if (sum % k != 0 || maxNum > sum / k) {
            return false;
        }
        return canPartitionKSubsetsFrom(nums, k, new boolean[nums.length], sum / k, 0, 0);
    }

    private boolean canPartitionKSubsetsFrom(int[] nums, int k, boolean[] visited, int targetSubsetSum, int curSubsetSum, int nextIndexToCheck) {
        if (k == 0) {
            return true;
        }

        if (curSubsetSum == targetSubsetSum) {
            return canPartitionKSubsetsFrom(nums, k - 1, visited, targetSubsetSum, 0, 0);
        }

        for (int i = nextIndexToCheck; i < nums.length; i++) {
            if (!visited[i] && curSubsetSum + nums[i] <= targetSubsetSum) {
                visited[i] = true;
                if (canPartitionKSubsetsFrom(nums, k, visited, targetSubsetSum, curSubsetSum + nums[i], i + 1)) {
                    return true;
                }
                visited[i] = false;
            }
        }

        return false;
    }

    // https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/
    int min = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        backtrack(jobs, 0, new int[k]);
        return min;
    }

    private void backtrack(int[] jobs, int idx, int[] workers) {
        int currMax = Arrays.stream(workers).max().getAsInt();
        if (currMax > min) return;
        if (idx == jobs.length) {
            min = Math.min(currMax, min);
            return ;
        }

        for (int i = 0; i < workers.length; i++) {
            if (i - 1 >= 0 && workers[i] == workers[i - 1]) continue;

            workers[i] += jobs[idx];
            backtrack(jobs, idx + 1, workers);
            workers[i] -= jobs[idx];
        }
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        Map<Integer, Boolean> memo = new HashMap<>();
        return solve(maxChoosableInteger, desiredTotal, 0, 0, memo);
    }

    private boolean solve(int maxChoosableInteger, int desiredTotal, int state, int sum, Map<Integer, Boolean> memo) {
        if (memo.containsKey(state)) {
            return memo.get(state);
        }

        for (int num = 1; num <= maxChoosableInteger; num++) {
            int bitRep = 1 << num;
            if ((state & bitRep) == 0) { // not visited
                if (sum + num >= desiredTotal) {
                    memo.put(state, true);
                    return true;
                }

                if (solve(maxChoosableInteger, desiredTotal, state ^ bitRep, sum + num, memo)) {
                    memo.put(state, false);
                    return false;
                }
            }
        }
        memo.put(state, true);
        return true;
    }

    public int minStickers(String[] stickers, String target) {
        int n = target.length();
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int state = 0; state < dp.length; state++) {
            if (dp[state] == Integer.MAX_VALUE) {
                continue;
            }
            for (String sticker : stickers) {
                int now = state;
                for (char letter : sticker.toCharArray()) {
                    for (int i = 0; i < n; i++) {
                        if (((now >> i) & 1) == 1) {
                            continue;
                        }
                        if (target.charAt(i) == letter) {
                            now |= (1 << i);
                            break;
                        }
                    }
                }

                dp[now] = Math.min(dp[now], dp[state] + 1);
            }
        }
        // System.out.println(Arrays.toString(dp));
        return dp[dp.length - 1] == Integer.MAX_VALUE ? -1 : dp[dp.length - 1];
    }

}
