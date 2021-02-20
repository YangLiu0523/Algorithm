package match.codeforces.round_696;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DifferentDivisors {
    MyScanner scanner = new MyScanner();

    private long solve(int gap) {
        int a = gap + 1;
        while (true) {
            if (isPrime(a)) {
                break;
            }
            else {
                a++;
            }
        }

        int b = a + gap;
        while (true) {
            if (isPrime(b)) {
                break;
            }else {
                b++;
            }
        }
        return a * b;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        DifferentDivisors test = new DifferentDivisors();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = test.scanner.nextInt();
            long ans = test.solve(n);
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
