package matieral.sort.binarySearch;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/longest-increasing-subsequence/
 * Test: https://leetcode.com/problems/russian-doll-envelopes/
 */

public class Special {
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
}
