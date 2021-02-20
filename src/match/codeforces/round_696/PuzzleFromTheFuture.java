package match.codeforces.round_696;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PuzzleFromTheFuture {
    MyScanner scanner = new MyScanner();

    private String solve(String b, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append('1');
        int prev = 1 + (b.charAt(0) - '0');
        for (int i = 1; i < n; i++) {
            int curr = 1 + (b.charAt(i) - '0');
            if (curr == prev) {
                sb.append(0);
                prev = curr - 1;
            }
            else {
                sb.append(1);
                prev = curr;
            }

        }
        return sb.toString();
    }

    public static void main(String[] args) {
        PuzzleFromTheFuture test = new PuzzleFromTheFuture();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int len = test.scanner.nextInt();
            String b = test.scanner.nextLine();
            String ans = test.solve(b, len);
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
