package matieral.minimax;
import java.util.*;
/**
 * Test: https://leetcode.com/problems/cat-and-mouse/
 * Test: https://leetcode.com/problems/cat-and-mouse-ii/
 */

public class CatAndMouse {
    int DRAW = 0, MOUSE_TURN = 1, CAT_TURN = 2, MOUSE_WIN = 1, CAT_WIN = 2;
    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        int[][][] status = new int[50][50][3];
        int[][][] neutral = new int[50][50][3];

        for (int m = 0; m < n; m++) {
            for (int c = 0; c < n; c++) {
                neutral[m][c][MOUSE_TURN] = graph[m].length;
                neutral[m][c][CAT_TURN] = graph[c].length;
                for (int x : graph[c]) {
                    if (x == 0) {
                        neutral[m][c][CAT_TURN]--;
                        break;
                    }
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int t = 1; t <= 2; t++) {
                status[0][i][t] = MOUSE_WIN;
                queue.add(new int[]{0, i, t, MOUSE_WIN});
                if (i > 0) {
                    status[i][i][t] = CAT_WIN;
                    queue.add(new int[]{i, i, t, CAT_WIN});
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            int m = node[0], c = node[1], t = node[2], s = node[3];
            for (int[] parent: parents(graph, m, c, t)) {
                int m2 = parent[0], c2 = parent[1], t2 = parent[2];
                if (status[m2][c2][t2] == DRAW) {
                    if (t2 == MOUSE_TURN && s == MOUSE_WIN || t2 == CAT_TURN && s == CAT_WIN) {
                        status[m2][c2][t2] = s;
                        queue.add(new int[]{m2, c2, t2, s});
                    }
                    else {
                        neutral[m2][c2][t2]--;
                        if (neutral[m2][c2][t2] == 0) {
                            status[m2][c2][t2] = t2 == MOUSE_TURN ? CAT_WIN : MOUSE_WIN;
                            queue.add(new int[]{m2, c2, t2, status[m2][c2][t2]});
                        }
                    }
                }
            }
        }
        return status[1][2][MOUSE_TURN];
    }

    private List<int[]> parents(int[][] graph, int m, int c, int t) {
        List<int[]> ans = new ArrayList<>();
        if (t == CAT_TURN) {
            for (int m2 : graph[m]) {
                ans.add(new int[]{m2, c, MOUSE_TURN});
            }
        }
        else {
            for (int c2 : graph[c]) {
                if (c2 > 0) {
                    ans.add(new int[]{m, c2, CAT_TURN});
                }
            }
        }
        return ans;
    }
}
