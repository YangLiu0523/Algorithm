package match.topcoder.SRM798;

import java.util.*;

public class SyllableCountEstimator {
	public int estimate(String W) {
		Set<String> minus = new HashSet<>(Arrays.asList("au", "oa", "oo", "iou"));
		Set<Character> voswls = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
		int ret = 0;
		for (int i = 0; i < W.length(); i++) {
			char c = W.charAt(i);
			if (voswls.contains(c)) {
				ret++;
			}

			if (i - 1 >= 0 && minus.contains(W.substring(i - 1, i + 1)) || i - 2 >= 0 && W.substring(i - 2, i + 1).equals("iou")) {
				ret--;
			}
			if (i == W.length() - 1 && W.charAt(i) == 'e') {
				ret--;
				if (i - 2 >= 0 && W.charAt(i - 1) == 'l' && !voswls.contains(W.charAt(i - 2))) {
					ret++;
				}
			}
		}
		return Math.max(ret, 1);
	}
}
