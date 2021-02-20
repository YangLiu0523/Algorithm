package match.topcoder.SRM799;

import java.util.*;

public class CoolPairsEasy {
	public int count(String[] firstname, String[] lastname) {
		int cnt = 0, n = firstname.length;
		Map<String, Set<Integer>> map= new HashMap<>();
		for (int i = 0; i < lastname.length; i++) {
			map.computeIfAbsent(lastname[i], v -> new HashSet<>()).add(i);
		}
		for (int i = 0; i < firstname.length; i++) {
			if (map.containsKey(firstname[i])) {
				int s = map.get(firstname[i]).size();
				if (map.get(firstname[i]).contains(i)) {
					s--;
				}
				cnt += s;
			}
		}

		return cnt;
	}
}
