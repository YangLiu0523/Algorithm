package match.codeforces.round_703;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EasternExhibition {
    MyScanner scanner = new MyScanner();

    private long solve(int[] xLoc, int[] yLoc, int n){
        if (n % 2 == 1) {
            return 1;
        }
        else {
            Arrays.sort(xLoc);
            Arrays.sort(yLoc);

            int xStart = xLoc[n / 2 - 1], xEnd = xLoc[n / 2];
            int yStart = yLoc[n / 2 - 1], yEnd = yLoc[n / 2];

            long xRange = xEnd - xStart + 1, yRange = yEnd - yStart + 1;
            return xRange * yRange;

        }
    }

    public static void main(String[] args) {
        EasternExhibition test = new EasternExhibition();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = test.scanner.nextInt();
            int[] xLoc = new int[n];
            int[] yLoc = new int [n];
            for (int j = 0; j < n; j++) {
                xLoc[j] = test.scanner.nextInt();
                yLoc[j] = test.scanner.nextInt();
            }
            long ans = test.solve(xLoc, yLoc, n);
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
