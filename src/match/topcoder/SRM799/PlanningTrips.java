package match.topcoder.SRM799;

import java.util.*;

/**
 * Review it!
 */

public class PlanningTrips {
	public int find(int a, int[] num) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int n : num) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}
		for (Integer key : map.keySet()) {
			int val = map.get(key);
			if (val >= a) {
				int mod = val % a;
				int div = val / a;

				if (mod == 0) {
					map.remove(key);
				}
				else {
					map.put(key, mod);
				}

				map.put(key + 1, map.getOrDefault(key + 1, 0) + div);
			}
		}
		int maxKey = map.lastKey();
		return map.size() == 1 && map.get(maxKey) == 1 ? maxKey : maxKey + 1;
	}
}
