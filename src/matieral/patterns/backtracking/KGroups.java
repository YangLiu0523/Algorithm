package matieral.patterns.backtracking;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/
 * Test: https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 * Test: https://leetcode.com/problems/matchsticks-to-square/
 */

public class KGroups {
    int res = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        int[] workers = new int[k];
        backtrackMinTime(jobs, jobs.length - 1, workers);
        return res;
    }

    private void backtrackMinTime(int[] jobs, int idx, int[] workers) {
        int currMax = Arrays.stream(workers).max().getAsInt();
        if (idx == -1) {
            res = Math.min(res, currMax);
            return;
        }
        if (currMax > res) return;

        for (int i = 0; i < workers.length; i++) {
            if (i > 0 && workers[i] == workers[i - 1]) {
                continue;
            }

            workers[i] += jobs[idx];
            backtrackMinTime(jobs, idx - 1, workers);
            workers[i] -= jobs[idx];
        }
    }

    public boolean search(int[] groups, int row, int[] nums, int target) {
        if (row < 0) return true;
        int v = nums[row--];
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + v <= target) {
                groups[i] += v;
                if (search(groups, row, nums, target)) return true;
                groups[i] -= v;
            }
            if (groups[i] == 0) break;
        }
        return false;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;
        int target = sum / k;

        Arrays.sort(nums);
        int row = nums.length - 1;
        if (nums[row] > target) return false;
        while (row >= 0 && nums[row] == target) {
            row--;
            k--;
        }
        return search(new int[k], row, nums, target);
    }

    public boolean makesquare(int[] nums) {
        if (nums== null || nums.length < 4) return false;
        int sum = Arrays.stream(nums).sum();
        if (sum % 4 != 0) {
            return false;
        }
        int target = sum / 4;
        int[] edges = new int[4];
        return backtrackMakeSquare(nums, 0, edges, target) ;
    }

    private boolean backtrackMakeSquare(int[] nums, int idx, int[] edges, int target) {
        if (idx == nums.length) {
            return true;
        }

        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            if (!seen.contains(edges[i]) && edges[i] + nums[idx] <= target) {
                seen.add(edges[i]);
                edges[i] += nums[idx];
                if(backtrackMakeSquare(nums, idx + 1, edges, target)) return true;
                edges[i] -= nums[idx];
            }
        }
        return false;
    }
}
