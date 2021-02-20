package match.codeforces.round_701;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AddAndDivide {
    MyScanner scanner = new MyScanner();

    private int findMin(int a, int b) {
        int min = Integer.MAX_VALUE;

        for (int secOp = b == 1 ? 1 : 0; secOp <= 30; secOp++) {
            int tmpB = secOp + b, tmpA = a;
            int firstOp = 0;
            while (tmpA != 0) {
                tmpA /= tmpB;
                firstOp++;
            }
            min = Math.min(min, firstOp + secOp);
        }
        return min;
    }


    public static void main(String[] args) {
        AddAndDivide test = new AddAndDivide();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int a = test.scanner.nextInt(), b = test.scanner.nextInt();
            int res = test.findMin(a, b);
            System.out.println(res);
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
