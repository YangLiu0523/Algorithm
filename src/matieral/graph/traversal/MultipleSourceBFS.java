package matieral.graph.traversal;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/map-of-highest-peak/
 */
public class MultipleSourceBFS {
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        int[][] heights = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(heights[i], -1);
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int h = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] loc = queue.poll();
                int r = loc[0], c = loc[1];
                if (heights[r][c] == -1) { // Error prone
                    heights[r][c] = h;
                    for (int[] dir : dirs) {
                        int newR = dir[0] + r, newC = dir[1] + c;
                        if (newR >= 0 && newR < m && newC >= 0 && newC < n && heights[newR][newC] == -1) {
                            queue.offer(new int[]{newR, newC});
                        }
                    }

                }

            }
            h++;
        }
        return heights;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i - 1 >= 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if (j - 1 > i && nums[j] == nums[j - 1]) {
                    continue;
                }
                int l = j + 1, r = n - 1;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum < target) {
                        l++;
                    }
                    else if (sum > target) {
                        r--;
                    }
                    else {
                        res.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[l], nums[r])));
                        l++;
                        while (l < n && nums[l] == nums[l - 1]) {
                            l++;
                        }
                    }
                }
            }
        }
        return res;
    }
}
