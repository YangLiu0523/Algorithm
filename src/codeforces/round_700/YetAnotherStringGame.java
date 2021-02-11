package codeforces.round_700;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class YetAnotherStringGame {
    MyScanner scanner = new MyScanner();

    public void playGame(String line) {
        char[] arr = line.toCharArray();
        boolean aliceTurn = true;
        int i = 0;
        while (i < arr.length) {
            if (aliceTurn) {
                arr[i] = arr[i] == 'a' ? 'b' : 'a';
            }
            else {
                arr[i] = arr[i] == 'z' ? 'y' : 'z';
            }
            i++;
            aliceTurn = !aliceTurn;
        }
        System.out.println(new String(arr));
    }

    public static void main(String[] args) {
        YetAnotherStringGame test = new YetAnotherStringGame();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            String str = test.scanner.nextLine();
            test.playGame(str);
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
