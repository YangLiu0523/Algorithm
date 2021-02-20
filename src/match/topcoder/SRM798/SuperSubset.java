package match.topcoder.SRM798;

import java.io.*;
import java.util.*;

public class SuperSubset {
	int MOD = 1000000007;

	public int solve(int[] A, int Y) {
		int[] cnt = new int[10001];
		cnt[0] = 1;
		for (int a : A) {
			for (int i = 10000; i >= 0; i--) {
				if (cnt[i] != 0) {
					cnt[i] = (cnt[i] * 2) % MOD;
				}
				if (i - a >= 0 && cnt[i - a] != 0) {
					cnt[i] = (cnt[i] + cnt[i - a]) % MOD;
				}
			}
		}
		return cnt[Y];
	}

	// CUT begin
	public static void main(String[] args){
		System.err.println("match.topcoder.SRM798.SuperSubset (1000 Points)");
		System.err.println();
		HashSet<Integer> cases = new HashSet<Integer>();
		for (int i = 0; i < args.length; ++i) cases.add(Integer.parseInt(args[i]));
		runTest(cases);
	}

	static void runTest(HashSet<Integer> caseSet) {
		int cases = 0, passed = 0;
		while (true) {
			String label = Reader.nextLine();
			if (label == null || !label.startsWith("--"))
				break;

			int[] A = new int[Integer.parseInt(Reader.nextLine())];
			for (int i = 0; i < A.length; ++i)
				A[i] = Integer.parseInt(Reader.nextLine());
			int Y = Integer.parseInt(Reader.nextLine());
			Reader.nextLine();
			int __answer = Integer.parseInt(Reader.nextLine());

			cases++;
			if (caseSet.size() > 0 && !caseSet.contains(cases - 1))
				continue;
			System.err.print(String.format("  Testcase #%d ... ", cases - 1));

			if (doTest(A, Y, __answer))
				passed++;
		}
		if (caseSet.size() > 0) cases = caseSet.size();
		System.err.println(String.format("%nPassed : %d/%d cases", passed, cases));

		int T = (int)(System.currentTimeMillis() / 1000) - 1613590846;
		double PT = T / 60.0, TT = 75.0;
		System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 1000 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}

	static boolean doTest(int[] A, int Y, int __expected) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		SuperSubset instance = new SuperSubset();
		int __result = 0;
		try {
			__result = instance.solve(A, Y);
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
		private static final String dataFileName = "match.topcoder.SRM798.SuperSubset.sample";
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
