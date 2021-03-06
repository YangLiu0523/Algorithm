package matieral.patterns;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/maximize-distance-to-closest-person/
 * Test: https://leetcode.com/problems/shortest-distance-to-a-character/
 *
 * Test: https://leetcode.com/problems/sum-of-subarray-minimums/
 * Test: https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/
 * Test: https://leetcode.com/problems/sum-of-subsequence-widths/
 *
 * Test: https://leetcode.com/problems/longest-valid-parentheses/ => Like
 * Test: https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/
 *
 */
public class SearchTwoDirs {
    public int[] shortestToChar(String S, char C) {
        int N = S.length();
        int[] ans = new int[N];
        int prev = Integer.MIN_VALUE / 2;

        for (int i = 0; i < N; ++i) {
            if (S.charAt(i) == C) prev = i;
            ans[i] = i - prev;
        }

        prev = Integer.MAX_VALUE / 2;
        for (int i = N-1; i >= 0; --i) {
            if (S.charAt(i) == C) prev = i;
            ans[i] = Math.min(ans[i], prev - i);
        }

        return ans;
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

    int MOD = 1000000007;
    public int uniqueLetterString(String S) {
        int[][] idx = new int[26][2];
        for (int i = 0; i < 26; i++) {
            idx[i][0] = idx[i][1] = -1;
        }

        long ret = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            ret = (ret + (i - idx[c - 'A'][1]) * (idx[c - 'A'][1] - idx[c - 'A'][0]) % MOD) % MOD;
            idx[c- 'A'][0] = idx[c - 'A'][1];
            idx[c - 'A'][1] = i;
        }

        int n = S.length();
        for (int i = 0; i < 26; i++) {
            ret = (ret + (idx[i][1] - idx[i][0]) * (n - idx[i][1]) % MOD) % MOD;
        }
        return (int)ret;
    }

    public int sumSubseqWidths(int[] A) {
        int N = A.length;
        Arrays.sort(A);

        long[] pow2 = new long[N];
        pow2[0] = 1;
        for (int i = 1; i < N; ++i)
            pow2[i] = (pow2[i-1] * 2) % MOD;

        long ret = 0;
        for (int i = 0; i < N; i++) {
            ret = (ret + (A[i] * (-pow2[N - i - 1] + pow2[i]) % MOD) % MOD) % MOD;
        }
        return (int)ret;
    }

    public String minRemoveToMakeValid(String s) {
        int left = 0, right = 0;
        Set<Integer> removed = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else if (s.charAt(i) == ')') right++;
            if (right > left) {
                right--;
                removed.add(i);
            }
        }
        left = 0;
        right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) =='(') left++;
            else if (s.charAt(i) == ')') right++;
            if (left > right) {
                left--;
                removed.add(i);
            }
        }
        // System.out.println(removed);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (removed.contains(i)) continue;
            sb.append(s.charAt(i));
        }
        return sb.toString();
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
