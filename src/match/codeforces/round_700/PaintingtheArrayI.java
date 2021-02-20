package match.codeforces.round_700;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Case: 1 1 2 3 1 1 1
 */

public class PaintingtheArrayI {
    MyScanner scanner = new MyScanner();

    public int findMax(int n, int[] arr) { // maximum
        int ret = 0;
        int[] pos = new int[n + 1], next = new int[n]; // Good trick
        Arrays.fill(pos, n + 1);
        for (int i = n - 1; i >= 0; i--) {
            next[i] = pos[arr[i]];
            pos[arr[i]] = i;
        }

        int x = -1, y = -1;
        for (int i = 0; i < arr.length; i++) {
            if (x >= 0 && arr[i] == arr[x]) {
                ret = y >= 0 && arr[y] == arr[i] ? ret : ret + 1;
                y = i;
            }
            else if (y >= 0 && arr[i] == arr[y]) {
                ret = x >= 0 && arr[x] == arr[i] ? ret : ret + 1;
                x = i;
            }
            else {
                if (x == -1 || y == -1) { // error prone
                    ret++;
                    if (x == -1) y = i;
                    else x = i;
                }
                else if (next[x] < next[y]) {
                    ret++;
                    x = i;
                }
                else {
                    ret++;
                    y = i;
                }
            }
        }
        System.out.println(ret);
        return ret;
    }
    public static void main(String[] args) {
        PaintingtheArrayI test = new PaintingtheArrayI();
        int n = test.scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = test.scanner.nextInt();
        }
        test.findMax(n, arr);
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
