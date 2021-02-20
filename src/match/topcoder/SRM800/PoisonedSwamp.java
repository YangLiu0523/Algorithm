package match.topcoder.SRM800;

import java.util.*;

public class PoisonedSwamp {
	int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public String cross(String[] swamp) {
		int m = swamp.length, n = swamp[0].length();
		int upperLimit = 10;
		if (swamp[m - 1].charAt(n - 1) == '#') {
			return "impossible";
		}
		int[][] dist = new int[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
		pq.offer(new int[]{0, 0, 0});
		while (!pq.isEmpty()) {
			int[] info = pq.poll();
			dist[info[0]][info[1]] = info[2];
			for (int[] dir : dirs) {
				int r = info[0] + dir[0], c = info[1] + dir[1];
				if (r >= 0 && r < m && c >= 0 && c < n && swamp[r].charAt(c) != '#') {
					char curr = swamp[r].charAt(c);
					int poison = curr == 'S' ? 0 : curr == '.' ? info[2] : info[2] + (curr - '0');
					if (r == m - 1 && c == n - 1 && poison < upperLimit) {
						return "possible";
					}
					if (poison < dist[r][c] && poison < upperLimit) {
						pq.offer(new int[]{r, c, poison});
					}
				}
			}
		}

		return "impossible";
	}
}
