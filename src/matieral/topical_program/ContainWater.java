package matieral.topical_program;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/container-with-most-water/
 * Test: https://leetcode.com/problems/trapping-rain-water/
 * Test: https://leetcode.com/problems/trapping-rain-water-ii/
 * Test: https://leetcode.com/problems/pour-water/
 */

public class ContainWater {
    public int maxArea(int[] height) {
        int n = height.length;
        int l = 0, r = n - 1;
        int maxArea = 0;
        while (l < r) {
            int curr;
            if (height[r] > height[l]) {
                curr = (r - l) * height[l];
                l++;
            }
            else {
                curr = (r - l) * height[r];
                r--;
            }
            maxArea = Math.max(maxArea, curr);
        }
        return maxArea;
    }

    public int trap(int[] height) {
        int n = height.length, trappedWater = 0;
        Deque<Integer> stack = new ArrayDeque<>(); // Decrease
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
                int hIdx = stack.pop(), h = height[hIdx];
                if (!stack.isEmpty()) {
                    int water = (i - stack.peek() - 1) * (Math.min(height[i], height[stack.peek()]) - h);
                    trappedWater+= water;
                }
            }
            stack.push(i);
        }
        return trappedWater;
    }

    public int[] pourWater(int[] heights, int V, int K) {
        int n = heights.length;

        for (int i = 0; i < V; i++) {
            int choosed = K, j = K;
            while (j - 1 >= 0 && heights[j - 1] <= heights[j]) {
                if (heights[j - 1] < heights[j]) {
                    choosed = j - 1;
                }
                j--;
            }
            if (heights[choosed] < heights[K]) {
                heights[choosed]++;
                continue;
            }

            j = K;
            while (j + 1 < n && heights[j + 1] <= heights[j]) {
                if (heights[j + 1] < heights[j]) {
                    choosed = j + 1;
                }
                j++;
            }
            if (heights[choosed] < heights[K]) {
                heights[choosed]++;
                continue;
            }

            heights[K]++;
        }
        return heights;
    }

    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;

        boolean[][] added = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[]{i, j, heightMap[i][j]});
                    added[i][j] = true;
                }
            }
        }

        int sum = 0;
        int lowest = 0;//整个外围（木桶）中，最低的木
        while (!pq.isEmpty()) {
            int[] loc = pq.poll();
            lowest = Math.max(lowest, loc[2]);

            for (int[] dir : dirs) {
                int r = dir[0] + loc[0];
                int c = dir[1] + loc[1];

                if (r < 0 || r >= m || c < 0 || c >= n || added[r][c]) continue;
                pq.offer(new int[]{r, c, heightMap[r][c]});
                added[r][c] = true;
                if (heightMap[r][c] < lowest) {
                    sum += lowest - heightMap[r][c];
                }
            }
        }
        return sum;
    }
}
