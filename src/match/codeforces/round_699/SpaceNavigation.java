package match.codeforces.round_699;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SpaceNavigation {
    String yes = "YES", no = "NO";
    MyScanner scanner = new MyScanner();

    private String canReach(String path, int x, int y) {
        int LCnt = 0, RCnt = 0, UCnt = 0, DCnt = 0;
        for (char c : path.toCharArray()) {
            if (c == 'L') LCnt++;
            else if (c == 'R') RCnt++;
            else if (c == 'U') UCnt++;
            else DCnt++;
        }

        boolean xReach = x == 0 || x > 0 && RCnt >= x || x < 0 && LCnt >= -x;
        boolean yReach = y == 0 || y > 0 && UCnt >= y || y < 0 && DCnt >= -y;
        return xReach && yReach ? yes : no;
    }

    public static void main(String[] args) {
        SpaceNavigation test = new SpaceNavigation();
        int n = test.scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int x = test.scanner.nextInt(), y = test.scanner.nextInt();
            String path =test.scanner.nextLine();
            String ans = test.canReach(path, x, y);

            if (i != n - 1) {
                System.out.println(ans);
            }
            else {
                System.out.print(ans);
            }
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
