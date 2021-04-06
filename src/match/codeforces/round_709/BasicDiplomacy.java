package match.codeforces.round_709;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BasicDiplomacy {
    MyScanner scanner = new MyScanner();

    private void solve(int friends, int days, List<Set<Integer>> available) {
        int upperLimit = (int)Math.ceil((double)days/2);
        int[] appears = new int[friends + 1];
        for (int d = 0; d < days; d++) {
            for (int f : available.get(d)) {
                appears[f]++;
            }
        }

        Integer target = 0;
        for (int f = 1; f < appears.length; f++) {
            if (appears[f] > upperLimit) {
                target = f;
                break;
            }
        }
        int[] ans = new int[days];
        if (target == 0) {
            for (int d = 0; d < days; d++) {
                ans[d] = available.get(d).iterator().next();
            }
            System.out.println("YES");
            printArr(ans);
            return;
        }

        int targetTimes = appears[target];
        for (int d = 0; d < days && targetTimes > upperLimit; d++) {
            if (available.get(d).contains(target) && available.get(d).size() > 1) {
                available.get(d).remove(target);
                targetTimes--;
            }
        }

        if (targetTimes > upperLimit) {
            System.out.println("NO");
            return;
        }

        for (int d = 0; d < days; d++) {
            ans[d] = available.get(d).iterator().next();
        }
        System.out.println("YES");
        printArr(ans);
        return;
    }

    private void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num + " ");
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    public static void main(String[] args) {
        BasicDiplomacy test = new BasicDiplomacy();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int friends = test.scanner.nextInt(), days = test.scanner.nextInt();
            List<Set<Integer>> available = new ArrayList<>();
            for (int j = 0; j < days; j++) {
                available.add(new HashSet<>());
                int num = test.scanner.nextInt();
                for (int k = 0; k < num; k++) {
                    available.get(available.size() - 1).add(test.scanner.nextInt());
                }

            }
            test.solve(friends, days, available);
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
