import java.io.*;
import java.util.*;

public class FlightPlan {
	int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	public long fly(int R, int C, int[] H, int cup, int cdn, int clr) {
		Map<Long, TreeMap<Long, Long>> dist = new HashMap<>(); // idx -> <h, cost>
		for (long i = 0; i < R * C; i++) {
			dist.put(i, new TreeMap<>());
		}

		long minCost = Long.MAX_VALUE;
		PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[2], b[2]));// idx, height, cost
		pq.offer(new long[]{0, H[0], 0});

		while (!pq.isEmpty()) {
			long[] info = pq.poll();
			dist.computeIfAbsent(info[0], v -> new TreeMap<>()).put(info[1], info[2]);
			int row = (int)info[0] / C, col = (int)info[0] % C, idx = row * C + col;
			if (row == R - 1 && col == C - 1) {
				long deltaCost = info[1] > H[idx] ? (info[1] - H[idx]) * cdn : info[1] < H[idx] ? (H[idx] - info[1]) * cup : 0;
				minCost = Math.min(minCost, info[2] + deltaCost);
			}
			for (int[] dir : dirs) {
				long r = dir[0] + row, c = dir[1] + col;
				if (r >= 0 && r < R && c >= 0 && c < C) {
					int newIdx = (int)(r * C + c);
					long newCost = H[newIdx] > info[1] ? cup * (H[newIdx] - info[1]) + (clr + info[2]) : clr + info[2];
					long newH = Math.max(info[1], H[newIdx]);
					if (dist.containsKey(newIdx) && dist.get(newIdx).containsKey(newH) && dist.get(newIdx).get(newH) <= newCost || newCost > minCost) {
						continue;
					}
					else {
						pq.offer(new long[]{newIdx, newH, newCost});
						Set<Long> removed = new HashSet<>();
						if (dist.containsKey(newIdx) && !dist.get(newIdx).isEmpty()) {
							TreeMap<Long, Long> map = dist.get(newIdx);

							long standerdH = map.firstKey(), standerC = map.get(standerdH);

							for (Map.Entry<Long, Long> e : map.entrySet()) {
								long tmpH = e.getKey(), tmpC = e.getValue();
								long upCost = standerC + (tmpH - standerdH) * cup;
								if (upCost < tmpC) {
									removed.add(tmpH);
								}
							}

							standerdH = map.lastKey(); standerC = map.get(standerdH);
							for (Map.Entry<Long, Long> e : map.entrySet()) {
								long tmpH = e.getKey(), tmpC = e.getValue();
								long downCost = standerC + (standerdH - tmpH ) * cdn;
								if (downCost < tmpC) {
									removed.add(tmpH);
								}
							}

							for (Long h : removed) map.remove(h);

						}

					}
				}
			}
		}

		return minCost;
	}


	// CUT begin
	public static void main(String[] args){
//		System.err.println("FlightPlan (1000 Points)");
//		System.err.println();
//		HashSet<Integer> cases = new HashSet<Integer>();
//		for (int i = 0; i < args.length; ++i) cases.add(Integer.parseInt(args[i]));
//		runTest(cases);
		FlightPlan test = new FlightPlan();
		int[] arr = {10, 8, 6, 8, 10};
		System.out.println(test.fly(1, 5, arr, 40, 10, 20));
	}

	static void runTest(HashSet<Integer> caseSet) {
		int cases = 0, passed = 0;
		while (true) {
			String label = Reader.nextLine();
			if (label == null || !label.startsWith("--"))
				break;

			int R = Integer.parseInt(Reader.nextLine());
			int C = Integer.parseInt(Reader.nextLine());
			int[] H = new int[Integer.parseInt(Reader.nextLine())];
			for (int i = 0; i < H.length; ++i)
				H[i] = Integer.parseInt(Reader.nextLine());
			int cup = Integer.parseInt(Reader.nextLine());
			int cdn = Integer.parseInt(Reader.nextLine());
			int clr = Integer.parseInt(Reader.nextLine());
			Reader.nextLine();
			long __answer = Long.parseLong(Reader.nextLine());

			cases++;
			if (caseSet.size() > 0 && !caseSet.contains(cases - 1))
				continue;
			System.err.print(String.format("  Testcase #%d ... ", cases - 1));

			if (doTest(R, C, H, cup, cdn, clr, __answer))
				passed++;
		}
		if (caseSet.size() > 0) cases = caseSet.size();
		System.err.println(String.format("%nPassed : %d/%d cases", passed, cases));

		int T = (int)(System.currentTimeMillis() / 1000) - 1613762559;
		double PT = T / 60.0, TT = 75.0;
		System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 1000 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}

	static boolean doTest(int R, int C, int[] H, int cup, int cdn, int clr, long __expected) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		FlightPlan instance = new FlightPlan();
		long __result = 0;
		try {
			__result = instance.fly(R, C, H, cup, cdn, clr);
		}
		catch (Throwable e) { exception = e; }
		double elapsed = (System.currentTimeMillis() - startTime) / 1000.0;

		if (exception != null) {
			System.err.println("RUNTIME ERROR!");
			exception.printStackTrace();
			return false;
		}
		else if (__result == __expected) {
			System.err.println("PASSED! " + String.format("(%.2f seconds)", elapsed));
			return true;
		}
		else {
			System.err.println("FAILED! " + String.format("(%.2f seconds)", elapsed));
			System.err.println("           Expected: " + __expected);
			System.err.println("           Received: " + __result);
			return false;
		}
	}

	static class Reader {
		private static final String dataFileName = "FlightPlan.sample";
		private static BufferedReader reader;

		public static String nextLine() {
			try {
				if (reader == null) {
					reader = new BufferedReader(new InputStreamReader(new FileInputStream(dataFileName)));
				}
				return reader.readLine();
			}
			catch (IOException e) {
				System.err.println("FATAL!! IOException");
				e.printStackTrace();
				System.exit(1);
			}
			return "";
		}
	}
// CUT end
}
