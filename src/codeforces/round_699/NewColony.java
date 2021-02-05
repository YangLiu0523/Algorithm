package codeforces.round_699;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NewColony {

    MyScanner scanner = new MyScanner();

    public int rollPosition(int[] heights, int boulders) {
        int lastPos = -1;
        for (int i = 0; i < boulders; i++) {
            int pos = 0;
            while (pos + 1 < heights.length) {
                if (heights[pos] < heights[pos + 1]) {
                    heights[pos]++;
                    lastPos = pos;
                    break;
                }
                pos++;
            }
            if (pos + 1 == heights.length) return -1;
        }
        return lastPos + 1;
    }

    public static void main(String[] args) {
        NewColony test = new NewColony();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int mountains = test.scanner.nextInt(), boulders = test.scanner.nextInt();
            int[] heights = new int[mountains];
            for (int j = 0; j < mountains; j++) {
                heights[j] = test.scanner.nextInt();
            }

            int ans = test.rollPosition(heights, boulders);
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
