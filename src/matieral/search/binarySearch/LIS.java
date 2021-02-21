package matieral.search.binarySearch;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/longest-increasing-subsequence/
 * Test: https://leetcode.com/problems/russian-doll-envelopes/
 * Test: https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/
 */

public class LIS {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    public int minimumMountainRemovals(int[] nums) {
        int[] l = lengthOfLIS2(nums);
        int[] r = reverse(lengthOfLIS2(reverse(nums)));

        int minLen = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (l[i] > 1 && r[i] > 1) {
                minLen = Math.min(minLen, nums.length  + 1 - r[i] - l[i]);
            }
        }
        return minLen;
    }

    private int[] lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] lenLIS = new int[n];

        int[] dp = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            int loc = Arrays.binarySearch(dp, 0, len, nums[i]);
            if (loc < 0) {
                loc = -(loc + 1);
            }

            dp[loc] = nums[i];
            if (loc == len) {
                len++;
            }
            lenLIS[i] = len;
        }
        return lenLIS;
    }

    private int[] reverse(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            swap(nums, l++, r--);
        }
        return nums;
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
