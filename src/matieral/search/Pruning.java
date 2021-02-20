package matieral.search;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/minimum-knight-moves/
 */

public class Pruning {
    int[][] dirs = {{-2, 1}, {-2, -1}, {-1, 2}, {-1, -2}, {1, 2}, {1, -2}, {2, 1}, {2, -1}};

    public int minKnightMoves(int x, int y) {
        if(x == 0 && y == 0) return 0;
        x = Math.abs(x);
        y = Math.abs(y);
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        // 省下了一个象限
        visited.add(0+"_"+0);
        queue.offer(0+"_"+0);

        int step = 0;
        while (!queue.isEmpty()) {
            step++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String[] entry = queue.poll().split("_");
                int r = Integer.valueOf(entry[0]), c = Integer.valueOf(entry[1]);

                for (int[] dir: dirs) {
                    int newX = r + dir[0], newY = c + dir[1];
                    if (x == newX && y == newY) return step;

                    // Important, dont move withing 第四象限
                    if (newX >= -2 && newY >= -2) {
                        String nxt = newX + "_" + newY;
                        if (!visited.contains(nxt)) {
                            queue.offer(nxt);
                            visited.add(nxt);
                        }
                    }
                }
            }
        }
        return -1;
    }
}
