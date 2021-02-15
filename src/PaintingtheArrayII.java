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

    public int findMin(int n, int[] arr) { // maximum
        int[] next = new int[n];
        int[] pos = new int[n + 1];
        Arrays.fill(pos, n + 1);
        for (int i = n - 1; i >= 0; i--) {
            next[i] = pos[arr[i]];
            pos[arr[i]] = i;
        }

        int x = -1, y = -1, ret = 0;
        for (int i = 0; i < n; i++) {
            if (x >= 0 && arr[x] == arr[i] || y >= 0 && arr[y] == arr[i]) {
                continue;
            }
            else if (x == -1 || y == -1) {
                ret++;
                if (x == -1) x = i;
                else y = i;
            }
            else {
                ret++;
                if (next[x] > next[y]) {
                    x = i;
                }
                else {
                    y = i;
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        PaintingtheArrayII test = new PaintingtheArrayII();
        int n = test.scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
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
