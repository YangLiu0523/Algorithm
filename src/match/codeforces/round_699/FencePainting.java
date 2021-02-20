package match.codeforces.round_699;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class FencePainting {
    MyScanner scanner = new MyScanner();
    String yes = "YES", no = "NO";

    private void canPaint(int[] initial, int[] desire, int[] changes) {
        Map<Integer, List<Integer>> missing = new HashMap<>();
        Map<Integer, Integer> colorOnDesire = new HashMap<>();
        int missed = 0;

        for (int i = 0; i < initial.length; i++) {
            if (initial[i] != desire[i]) {
                missing.computeIfAbsent(desire[i], v -> new ArrayList<>()).add(i);
                missed++;
            }
            colorOnDesire.put(desire[i], i);
        }

        if (missed > changes.length) {
            System.out.println(no);
            return;
        }

        // Special handling, learn from answer
        Integer special = -1;
        Integer lastChange = changes[changes.length - 1];
        if (missing.containsKey(lastChange)) {
            special = missing.get(lastChange).iterator().next();
            missing.get(lastChange).remove(special);
            if (missing.get(lastChange).size() == 0) {
                missing.remove(lastChange);
            }
        }
        else if (colorOnDesire.containsKey(lastChange)) {
            special = colorOnDesire.get(lastChange);
        }
        else {
            System.out.println(no);
            return;
        }

        List<Integer> paintOrder = new ArrayList<>();
        for (int i = 0; i < changes.length - 1; i++) {
            Integer change = changes[i];
            if (missing.containsKey(change)) {
                int idx = missing.get(change).remove(0);
                paintOrder.add(idx + 1);
                if (missing.get(change).isEmpty()) {
                    missing.remove(change);
                }
            } else {
                paintOrder.add(special + 1);
            }
        }
        paintOrder.add(special + 1);

        if (!missing.isEmpty()) {
            System.out.println(no);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int num : paintOrder) {
            sb.append(num + " ");
        }
        System.out.println(yes);
        System.out.println(sb.toString().substring(0, sb.length() - 1));
    }

    public static void main(String[] args) {
        FencePainting test = new FencePainting();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int planks = test.scanner.nextInt(), painters = test.scanner.nextInt();
            int[] initialColors = new int[planks];
            for (int j = 0; j < planks; j++) {
                initialColors[j] = test.scanner.nextInt();
            }

            int[] desiredColors = new int[planks];
            for (int j = 0; j < planks; j++) {
                desiredColors[j] = test.scanner.nextInt();
            }

            int[] colors = new int[painters];
            for (int j = 0; j < painters; j++) {
                colors[j] = test.scanner.nextInt();
            }

            test.canPaint(initialColors, desiredColors, colors);

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
