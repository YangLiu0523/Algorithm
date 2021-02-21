package matieral.dp;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/longest-increasing-subsequence/
 * Test: https://leetcode.com/problems/increasing-triplet-subsequence/ => Like
 * Test: https://leetcode.com/problems/longest-continuous-increasing-subsequence/
 * Test: https://leetcode.com/problems/best-team-with-no-conflicts/
 * Test: https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/
 */

public class LIS {

    public int lengthOfLIS1(int[] nums) {
        int n = nums.length,  max = 1;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }

    public boolean increasingTriplet(int[] nums) {
        int first_num = Integer.MAX_VALUE;
        int second_num = Integer.MAX_VALUE;
        for (int n: nums) {
            if (n <= first_num) {
                first_num = n;
            } else if (n <= second_num) {
                second_num = n;
            } else {
                return true;
            }
        }
        return false;
    }

    public int findLengthOfLCIS(int[] nums) {
        int start = 0, max = 0;
        for (int end = 0; end < nums.length; end++) {
            if (end != 0 && nums[end] <= nums[end - 1]) {
                max = Math.max(max, end - start);
                start = end;
            }
        }
        max = Math.max(max, nums.length - start);
        return max;
    }

    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        Player[] players = new Player[n];
        for (int i = 0; i < n; i++) {
            players[i] = new Player(scores[i], ages[i]);
        }
        Arrays.sort(players, (p1, p2) -> p1.age != p2.age ? Integer.compare(p1.age, p2.age): Integer.compare(p1.score, p2.score));

        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = players[i].score;
        }

        int bestScore = players[0].score;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (players[i].score >= players[j].score) {
                    dp[i] = Math.max(dp[i], dp[j] + players[i].score);
                }
            }
            bestScore = Math.max(bestScore, dp[i]);
        }

        return bestScore;
    }

    class Player{
        int score;
        int age;
        public Player(int score, int age) {
            this.score = score;
            this.age = age;
        }
    }

    public int minimumMountainRemovals(int[] nums) {
        int[] l = lengthOfLIS(nums);
        int[] r = reverse(lengthOfLIS(reverse(nums)));

        int minLen = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (l[i] > 1 && r[i] > 1) {
                minLen = Math.min(minLen, nums.length  + 1 - r[i] - l[i]);
            }
        }
        return minLen;
    }

    private int[] lengthOfLIS(int[] nums) {
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
