package codeforces.round_701;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ReplaceAndKeepSorted {
    MyScanner scanner = new MyScanner();

    public static void main(String[] args) {
        ReplaceAndKeepSorted test = new ReplaceAndKeepSorted();
        int n = test.scanner.nextInt(), q = test.scanner.nextInt(), k = test.scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = test.scanner.nextInt();
        }

        for (int i = 0; i < q; i++) {
            int l = test.scanner.nextInt() - 1, r = test.scanner.nextInt() - 1;
            int ans = k + (arr[r] - arr[l] + 1) - 2 * (r - l + 1);
            System.out.println(ans);
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
