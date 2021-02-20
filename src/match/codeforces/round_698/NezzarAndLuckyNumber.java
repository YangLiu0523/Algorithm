package match.codeforces.round_698;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NezzarAndLuckyNumber {
    MyScanner scanner = new MyScanner();

    public static void main(String[] args) {
        NezzarAndLuckyNumber test = new NezzarAndLuckyNumber();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = test.scanner.nextInt(), d = test.scanner.nextInt();
            int[] arr = test.scanner.nextIntArray(n);
            for (int num : arr) {
                if (num >= d * 10) {
                    System.out.println("YES");
                }
                else {
                    boolean[] dp = new boolean[d * 10];
                    for (int j = 0; j < d; j++) {
                        dp[j * 10 + d] = true;
                    }
                    for (int j = d; j < dp.length; j++) {
                        if (dp[j]) {
                            for (int k = 0; k <= j && k + j < dp.length; k++) {
                                if (dp[k]) {
                                    dp[k + j] = true;
                                }
                            }
                        }
                    }
                    if (dp[num]) System.out.println("YES");
                    else System.out.println("NO");
                }

            }

        }
    }

    class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] nextIntArray(int len) {
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                arr[i] = nextInt();
            }
            return arr;
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
