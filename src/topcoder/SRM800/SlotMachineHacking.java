package topcoder.SRM800;

import java.io.*;
import java.util.*;

public class SlotMachineHacking {
	public int win(String[] reels, int[] steps) {
		int n = reels.length;
		int[] idx = new int[n];
		Set<String> visited = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append('c');
		}
		String target = sb.toString();
		while(true) {
			sb = new StringBuilder();
			for (int i = 0; i < n; i++) {
				idx[i] = (idx[i] + steps[i]) % n;
				sb.append(reels[i].charAt(idx[i]));
			}
			String curr = sb.toString();
			if (curr.equals(target) && visited.size() != 0) {
				return visited.size() - 1;
			}
			else {
				visited.add(curr);
			}
		}
	}
}
