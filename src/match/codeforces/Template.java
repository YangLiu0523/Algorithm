package match.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class   Template {
    MyScanner scanner = new MyScanner();


    private void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num + " ");
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    public static void main(String[] args) {
        Template test = new Template();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = test.scanner.nextInt();
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

    private void print(boolean b, String s1, String s2) {
        if (b) {
            System.out.println(s1);
        }
        else {
            System.out.println(s2);
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
