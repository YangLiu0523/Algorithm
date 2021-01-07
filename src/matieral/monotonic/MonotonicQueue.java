package matieral.monotonic;
import java.util.*;

/**
 * Test:https://leetcode.com/problems/sliding-window-maximum/
 * Test: https://leetcode.com/problems/jump-game-vi/
 *
 * Related with sliding window
 */

public class MonotonicQueue {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return new int[0];
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> nums[b] - nums[a]);
        for (int i = 0; i < k - 1; i++) {
            queue.offer(i);
        }

        int[] ret = new int[nums.length - k + 1];
        for (int i = k - 1; i < nums.length; i++) {
            queue.offer(i);
            while (i - queue.peek() >= k) queue.poll();

            ret[i - k + 1] = nums[queue.peek()];
        }
        return ret;
    }

    // O(NlgN)
    public int maxResult2(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int val = nums[n - 1];
        pq.offer(new int[]{val, n - 1});

        for (int i = n - 2; i >= 0; i--) {
            while (i + k < pq.peek()[1]) {
                pq.poll();
            }

            val = pq.peek()[0] + nums[i];
            pq.offer(new int[]{pq.peek()[0] + nums[i], i});
        }
        return val;
    }

    // O(N)
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        Deque<int[]> dq = new LinkedList<>();
        int val = nums[n - 1];
        dq.offerLast(new int[]{val, n - 1});

        for (int i = n - 2; i >= 0; i--) {
            while (dq.peekFirst() != null && dq.peekFirst()[1] > i + k) {
                dq.pollFirst();
            }
            val = dq.peekFirst()[0] + nums[i];
            while (dq.peekLast() != null && dq.peekLast()[0] <= val){
                dq.pollLast();
            }
            dq.offerLast(new int[]{val, i});
        }
        return val;
    }
}
