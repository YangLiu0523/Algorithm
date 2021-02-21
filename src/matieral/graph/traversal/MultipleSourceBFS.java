package matieral.graph.traversal;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/map-of-highest-peak/
 * Test: https://leetcode.com/problems/walls-and-gates/
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

    int INF = 2147483647;
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        int m = rooms.length, n = rooms[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    int dist = 0;
                    Set<Integer> visited = new HashSet<>();
                    Queue<Integer> queue = new LinkedList<>();
                    queue.offer(i * n + j);
                    visited.add(i * n + j);
                    while(!queue.isEmpty()) {
                        int size = queue.size();
                        for (int k = 0; k < size; k++) {
                            int loc = queue.poll();
                            int r = loc / n, c = loc % n;
                            for (int[] dir : dirs) {
                                int rr = dir[0] + r, cc = dir[1] + c;
                                if (rr >= 0 && rr < m && cc >= 0 && cc < n) {
                                    if (rooms[rr][cc] <= 0 || rooms[rr][cc] < rooms[r][c] + 1) {
                                        continue;
                                    }

                                    rooms[rr][cc] = Math.min(rooms[rr][cc], rooms[r][c] + 1);
                                    if (!visited.contains(rr * n + cc)) {
                                        visited.add(rr * n + cc);
                                        queue.offer(rr * n + cc);
                                    }

                                }
                            }
                        }

                    }
                }
            }
        }

    }

}
