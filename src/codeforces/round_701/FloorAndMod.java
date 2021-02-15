package codeforces.round_701;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FloorAndMod {
    MyScanner scanner = new MyScanner();

    private long find(int x, int y) {
        long ans = 0;
        for (int k = 1; k * k < x; k++) { // Fixed k
            ans += Math.max(0, Math.min(y, x / k - 1) - k);
        }
        return ans;
    }

    public static void main(String[] args) {
        FloorAndMod test = new FloorAndMod();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int a = test.scanner.nextInt(), b = test.scanner.nextInt();
            long ret = test.find(a, b);
            System.out.println(ret);
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
