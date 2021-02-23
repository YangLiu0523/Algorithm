package matieral.topical_program;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/sliding-window-median/
 * Test: https://leetcode.com/problems/best-meeting-point/
 * Test: https://leetcode.com/problems/shortest-distance-from-all-buildings/ => Different with above
 */
public class Median {
    PriorityQueue<Integer> lo = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
    PriorityQueue<Integer> hi = new PriorityQueue<>();
    Map<Integer, Integer> invalid = new HashMap<>();

    int loCnt = 0, hiCnt = 0;

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] ans = new double[n - k + 1];
        for (int i = 0; i < k; i++) {
            addNum(nums[i]);
        }
        for (int i = k; i < n; i++) {
            ans[i - k] = median();
            removePrev(nums[i - k]);
            addNum(nums[i]);
        }
        ans[n - k] = median();

        return ans;
    }

    private double median(){
        while (!lo.isEmpty() && invalid.containsKey(lo.peek())) {
            int num = lo.poll();
            invalid.put(num, invalid.get(num) - 1);
            if (invalid.get(num) == 0) {
                invalid.remove((Integer)num);
            }
        }
        while (!hi.isEmpty() && invalid.containsKey(hi.peek())) {
            int num = hi.poll();
            invalid.put(num, invalid.get(num) - 1);
            if (invalid.get(num) == 0) {
                invalid.remove((Integer)num);
            }
        }

        balance();


        return loCnt == hiCnt ? ((long)lo.peek() + hi.peek()) / 2.0 : lo.peek();
    }

    private void removePrev(int num) {
        invalid.put(num, invalid.getOrDefault(num, 0) + 1);
        if (num <= lo.peek()) {
            loCnt--;
        }
        else {
            hiCnt--;
        }
    }

    private void addNum(int num) {
        if (lo.isEmpty() && hi.isEmpty()) {
            lo.offer(num);
            loCnt++;
        }
        else if (!lo.isEmpty() && num <= lo.peek()) {
            lo.offer(num);
            loCnt++;
        }
        else {
            hi.offer(num);
            hiCnt++;
        }

        balance();
    }

    private void balance(){
        while (loCnt > hiCnt + 1) {
            loCnt--;
            hiCnt++;
            hi.offer(lo.poll());
        }
        while (hiCnt > loCnt) {
            loCnt++;
            hiCnt--;
            lo.offer(hi.poll());
        }
    }

    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = collectRows(grid);
        List<Integer> cols = collectCols(grid);
        return minDistance1D(rows) + minDistance1D(cols);
    }
    private int minDistance1D(List<Integer> list) {
        int i = 0, j = list.size() - 1;
        int dist = 0;
        while (i < j) {
            dist += list.get(j) - list.get(i);
            i++;
            j--;
        }
        return dist;
    }
    private List<Integer> collectRows(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                }
            }
        }
        return rows;
    }

    private List<Integer> collectCols(int[][] grid) {
        List<Integer> cols = new ArrayList<>();
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] == 1) {
                    cols.add(col);
                }
            }
        }
        return cols;
    }

    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int dist = Integer.MAX_VALUE;
    // int[][] cost;
    public int shortestDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // cost = new int[m][n];
//         for (int i = 0; i < m; i++) {
//             Arrays.fill(cost, Integer.MAX_VALUE);
//         }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int curr = bfs(grid, i, j);
                    dist = Math.min(dist, curr);
                    // cost[i][j] = Math.min()
                }
            }
        }
        return dist == Integer.MAX_VALUE ? -1 : dist;
    }


    public int bfs(int[][] grid, int r, int c) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[r][c] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});

        int dist = 0, totalDist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            dist++;
            for (int i = 0; i < size; i++) {
                int[] loc = queue.poll();
                for (int[] dir : dirs) {
                    int row = loc[0] + dir[0], col = loc[1] + dir[1];
                    if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
                        visited[row][col] = true;
                        if (grid[row][col] == 1) {
                            totalDist += dist;
                            // if (totalDist >= dist) {
                            //     return Integer.MAX_VALUE;
                            // }
                        }
                        else if (grid[row][col] == 0) {
                            queue.offer(new int[]{row, col});
                        }
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    grid[r][c] = 2;
                    return Integer.MAX_VALUE;
                }
            }
        }
        return totalDist;
    }

}
