package matieral.monotonic;
import java.util.*;

/**
 * Test:https://leetcode.com/problems/sliding-window-maximum/
 * Test: https://leetcode.com/problems/jump-game-vi/
 *
 * Related with sliding window, find max/ min value in selected interval
 */

public class MonotonicQueue {
    public int[] maxSlidingWindow(int[] nums, int k) { // Decrease
        Deque<Integer> queue = new ArrayDeque<>();
        int maxIdx = 0;

        for (int i = 0; i < k - 1; i++) {
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (nums[i] >= nums[maxIdx]) {
                maxIdx = i;
            }
        }

        int[] ret = new int[nums.length - k + 1];
        for (int i = k - 1; i < nums.length; i++) {
            if (!queue.isEmpty() && queue.peekFirst() + k <= i) {
                int idx = queue.pollFirst();
                if (idx == maxIdx) {
                    maxIdx = queue.isEmpty() ? i : queue.peekFirst();
                }
            }

            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (nums[i] >= nums[maxIdx]) {
                maxIdx = i;
            }
            ret[i - k + 1] = nums[maxIdx];
        }
        return ret;
    }

    public int maxResult(int[] nums, int k) {
        int n = nums.length;

        Deque<int[]> queue = new ArrayDeque<>();
        queue.offerFirst(new int[]{nums[n - 1], n - 1});

        for (int i = n - 2; i >= 0; i--) { // Decrease
            if (!queue.isEmpty() && queue.peekFirst()[1] - k > i) {
                queue.pollFirst();
            }

            int val = queue.peekFirst()[0] + nums[i];
            while (!queue.isEmpty() && queue.peekLast()[0] < val) {
                queue.pollLast();
            }
            queue.offerLast(new int[]{val, i});
        }
        return queue.peekLast()[0];
    }
}
