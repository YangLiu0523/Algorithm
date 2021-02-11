package codeforces.round_700;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SearchingLocalMinimum {
    MyScanner scanner = new MyScanner();

    public void search(int n) {
        if (n == 1) {
            System.out.println("! " + 1);
            return;
        }

        int i1 = ask(1);
        int i2 = ask(2);
        if (i1 < i2) {
            System.out.println("! " + 1);
            return;
        }
        int in = ask(n);
        int in1 = ask(n - 1);
        if (in < in1) {
            System.out.println("! " + n);
            return;
        }

        int l = 1, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int m = ask(mid);
            int smallM = ask(mid - 1);
            int bigM = ask(mid + 1);
            if (m < smallM && m < bigM) {
                System.out.println("! " + mid);
                return;
            }
            else if (m > smallM && m > bigM) {
                r = mid;
            }
            else if (smallM < bigM) {
                r = mid;
            }
            else if (smallM > bigM) {
                l = mid;
            }
        }
    }

    private int ask(int i) {
        System.out.println("? " + i);
        System.out.flush();
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        SearchingLocalMinimum test = new SearchingLocalMinimum();
        int n = test.scanner.nextInt();
        test.search(n);
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
