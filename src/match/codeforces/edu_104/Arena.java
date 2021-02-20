package match.codeforces.edu_104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;
public class Arena {
    MyScanner scanner = new MyScanner();

    private void findWinners(int n, int[] initials) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : initials) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

//        int winners = 0;
//        Integer min = map.firstKey(), max = map.lastKey();
//        int preSum = 0;
//        for (int key : map.keySet()) {
//            if (key != min) {
//                int curr = preSum + key * map.get(key);
//                if (curr > max) {
//                    winners += map.get(key);
//                }
//                else {
//                    for (int i = key + 1; i < curr; i++) {
//                        if (map.containsKey(i)) {
//                            curr += map.get(i) * i;
//                            if (curr > max) {
//                                winners += map.get(key);
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//
//            preSum += key * map.get(key);
//        }
        System.out.println(n - map.get(map.firstKey()));
    }

    public static void main(String[] args) {
        Arena test = new Arena();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int heros = test.scanner.nextInt();
            int[] initialLevels = test.scanner.nextIntArray(heros);
            test.findWinners(heros, initialLevels);
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
