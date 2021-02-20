package match.codeforces.round_698;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NezzarAndColorfulBalls {
    MyScanner scanner = new MyScanner();

    public static void main(String[] args) {
        NezzarAndColorfulBalls test = new NezzarAndColorfulBalls();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = test.scanner.nextInt();
            int[] arr = test.scanner.nextIntArray(n);

            int prev= arr[0], cnt = 1, max = 1;
            for (int j = 1; j < n; j++) {
                if (arr[j] == prev) {
                    cnt++;
                    max = Math.max(cnt, max);
                } else {
                    prev = arr[j];
                    cnt = 1;
                }
            }
            System.out.println(max);
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
