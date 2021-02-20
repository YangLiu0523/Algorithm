package match.codeforces.round_703;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ShiftingStacks {
    MyScanner scanner = new MyScanner();

    private boolean solve(int n, int[] arr) {
//        if (n == 1) return true;

        int target = n * (n - 1) / 2;

        long sum = arr[0];
        for (int i = 0; i < n; i++) {
            int curr = (i) * (i + 1) / 2;
            sum += arr[i];
            if (sum < curr) {
                return false;
            }
            if (sum >= target) {
                return true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ShiftingStacks test = new ShiftingStacks();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int h = test.scanner.nextInt();
            int[] arr = test.scanner.nextIntArray(h);
            boolean ans = test.solve(h, arr);
            if (ans) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
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
