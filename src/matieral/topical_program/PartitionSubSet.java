package matieral.topical_program;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/partition-equal-subset-sum/
 * Test: https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 */

public class PartitionSubSet {
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

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;

        Arrays.sort(nums);
        int n = nums.length;
        if (nums[n - 1] > target) {
            return false;
        }

        int i = n - 1;
        while (i >= 0 && nums[i] == target) {
            i--;
            k--;
        }
        return search(new int[k], i, nums, target);
    }

    private boolean search(int[] groups, int idx, int[] nums, int target) {
        if (idx < 0) {
            return true;
        }
        int value = nums[idx];
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + value <= target) {
                groups[i] += value;
                if (search(groups, idx - 1, nums, target)) return true;
                groups[i] -= value;
            }
            if (groups[i] == 0) break;
        }
        return false;
    }
}
