package match.codeforces.round_700;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;


/**
 * Case: 1 1 2 3 1 1 1
 */


public class PaintingtheArrayII {
    MyScanner scanner = new MyScanner();

    public int findMin(int n, int[] arr) {  // 1 indexed
        int[] next = new int[n + 1];
        int[] pos = new int[n + 1];
        Arrays.fill(pos, n + 1);
        for (int i = n; i >= 0; i--) {
            next[i] = pos[arr[i]];
            pos[arr[i]] = i;
        }

        int x = 0, y = 0, res = 0;
        for (int i = 1; i <= n; i++) {
            if (arr[i] == arr[x]) {
                x = i;
            }
            else if (arr[i] == arr[y]) {
                y = i;
            }
            else if (next[x] > next[y]) {
                res += 1;
                x = i;
            }
            else {
                res += 1;
                y = i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        PaintingtheArrayII test = new PaintingtheArrayII();
        int n = test.scanner.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = test.scanner.nextInt();
        }
        int ans = test.findMin(n, arr);
        System.out.println(ans);
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
