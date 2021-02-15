package codeforces.edu_104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CatCycle {
    MyScanner scanner = new MyScanner();

    private void whereIsB(int spots, int hours) {
        int ans = 0;
        if (spots % 2 == 0) {
            ans = (hours - 1) % spots + 1;
        }
        else {
            ans = (hours - 1 + 2 * (hours - 1) / (spots - 1)) % spots + 1;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        CatCycle test = new CatCycle();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int spots = test.scanner.nextInt(), hour = test.scanner.nextInt();
            test.whereIsB(spots, hour);
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
