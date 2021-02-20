package match.codeforces.round_703;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GuessingTheGreatestHard {
    MyScanner scanner = new MyScanner();

    private int solve(int n) {
        int start = 0, end = n - 1;
        int secMax = ask(start, end);
        boolean maxIsRight = secMax == 0 || secMax != end && ask(secMax, end) == secMax;
        if (maxIsRight) {// Guess Right
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (mid == 0 || ask(0, mid) != secMax) {
                    start = mid + 1;
                }
                else {
                    end = mid - 1;
                }
            }
            return start;
        }
        else {
            start = 0;
            end = n - 1;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (mid == n - 1 || ask(mid, n - 1) != secMax) {
                    end = mid - 1;
                }
                else {
                    start = mid + 1;
                }
            }
            return end;
        }
    }

    private int ask(int l, int r) {
        System.out.println("? " + (l + 1) + " " + (r + 1));
        System.out.flush();
        int ans = scanner.nextInt();
        return ans - 1;
    }

    private void print(int n) {
        System.out.println("! " + (n + 1));
        System.out.flush();
    }

    public static void main(String[] args) {
        GuessingTheGreatestHard test = new GuessingTheGreatestHard();
//        int t = test.scanner.nextInt();
//        for (int i = 0; i < t; i++) {
        int n = test.scanner.nextInt();
        int ans = test.solve(n);
        test.print(ans);
//        }
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
