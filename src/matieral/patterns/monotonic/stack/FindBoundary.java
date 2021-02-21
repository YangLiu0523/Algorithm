package matieral.patterns.monotonic.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *  Test: https://leetcode.com/problems/largest-rectangle-in-histogram/ low-high-low
 *  Test: https://leetcode.com/problems/trapping-rain-water/  high-low-high
 *  Test: https://leetcode.com/problems/sum-of-subarray-minimums/  high-low-high
 */

public class FindBoundary {
    public int largestRectangleArea(int[] heights) { // Increase : want to find last smaller one
        int n = heights.length;
        int largestArea = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[i] < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int area = h * (i - stack.peek() - 1);
                // System.out.println(area);
                largestArea = Math.max(largestArea, area);
            }
            stack.push(i);
        }

        while (stack.peek() != -1) {
            int idx = stack.pop();
            largestArea = Math.max(heights[idx] * (n - stack.peek() - 1), largestArea);
        }
        return largestArea;
    }

    public int trap(int[] height) { // Decrease => want to find last bigger one, find boundary
        int water = 0, n = height.length;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int popIdx = stack.pop();
                if (!stack.isEmpty()) {
                    int deltaH = Math.min(height[i], height[stack.peek()]) - height[popIdx];
                    water += deltaH * (i - stack.peek() - 1);
                }
            }
            stack.push(i);
        }
        return water;
    }

    public int sumSubarrayMins(int[] arr) {
        int MOD = 1000000007, n = arr.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) { // here is >= error prone
                stack.pop();
            }
            prev[i] = stack.isEmpty()? -1 : stack.peek();
            stack.push(i);
        }

        stack = new ArrayDeque<>();
        int[] next = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) { // here is >
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
}
