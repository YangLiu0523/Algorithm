package codeforces.round_700;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class TheGreatHero {
    MyScanner scanner = new MyScanner();

    private void fight(int heroPower, int heroHealth, int[] monPower, int[] monHealth) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)-> monPower[a] - monPower[b]);
        for (int i = 0; i < monHealth.length; i++) {
            pq.offer(i);
        }

        while (!pq.isEmpty()) {
            int i = pq.poll();
            while (monHealth[i] > 0) {
                if (heroHealth <= 0) {
                    System.out.println("NO");
                    return;
                }
                heroHealth -= monPower[i];
                monHealth[i] -= heroPower;
            }
        }

        System.out.println("YES");
    }

    public static void main(String[] args) {
        TheGreatHero test = new TheGreatHero();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int heroPower = test.scanner.nextInt(), heroHealth = test.scanner.nextInt();
            int monsters = test.scanner.nextInt();
            int[] mPower = new int[monsters], mHealth = new int[monsters];
            for (int j = 0; j < monsters; j++) {
                mPower[j] = test.scanner.nextInt();
            }
            for (int j = 0; j < monsters; j++) {
                mHealth[j] = test.scanner.nextInt();
            }
            test.fight(heroPower, heroHealth, mPower, mHealth);
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
