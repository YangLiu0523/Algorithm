package match.codeforces.round_697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OddDivisor {
    MyScanner scanner = new MyScanner();

    private boolean canDivide(long n) {
        for (long j = 2; j <= n; j *= 2) {
            if (j == n) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        OddDivisor test = new OddDivisor();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            long n = test.scanner.nextLong();
            boolean can = test.canDivide(n);
            if(can)  System.out.println("YES"); else System.out.println("NO");
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
