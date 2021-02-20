package match.codeforces.round_697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BallInBerland {
    MyScanner scanner = new MyScanner();

    private long solve(int bNum, int aNum, int[] boys, int[] girls, int n) {
        int[] bCnt = new int[bNum + 1], gCnt = new int[aNum + 1];
        for (int i = 0; i < n; i++) {
            bCnt[boys[i]]++;
            gCnt[girls[i]]++;
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            bCnt[boys[i]]--;
            gCnt[girls[i]]--;
            ans += n - 1 - i - bCnt[boys[i]] - gCnt[girls[i]];
        }
        return ans;
    }

    public static void main(String[] args) {
        BallInBerland test = new BallInBerland();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int boyNum = test.scanner.nextInt(), girlNum = test.scanner.nextInt(), n = test.scanner.nextInt();
            int[] boys = test.scanner.nextIntArray(n);
            int[] girls =  test.scanner.nextIntArray(n);
            long ans = test.solve(boyNum, girlNum, boys, girls, n);
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
