package codeforces.round_699;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 答案的解法比我快多了，但是不好想
public class ABGraph {
    MyScanner scanner = new MyScanner();
    String yes = "YES", no = "NO";

    private boolean find(String[] labels, int len, LinkedList<Integer> path) {
        if (len == 0) {
            return true;
        }

        int head = path.get(0), tail = path.get(path.size() - 1);
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels.length; j++) {
                if (labels[head].charAt(i) != '*' && labels[i].charAt(head) == labels[tail].charAt(j)) { // Direction is important
                    path.add(0, i);
                    path.add(j);

                    if (find(labels, len - 2, path)) {
                       return true;
                    }
                    path.removeLast();
                    path.removeFirst();
                }
            }
        }
        return false;
    }

    private void findPalindrome(String[] labels, int palindromeLen) {
        LinkedList<Integer> path = new LinkedList<>();
        boolean found = false;
        if (palindromeLen % 2 == 0) {
            for (int i = 0; i < labels.length && !found; i++) {
                for (int j = 0; j < labels.length && !found; j++) {
                    if (i != j) {
                        path.addFirst(i);
                        path.addLast(j);
                        if (find(labels, palindromeLen - 2, path)) {
                            found = true;
                            break;
                        }
                        path.removeFirst();
                        path.removeLast();
                    }
                }
            }
        }
        else {
            for (int i = 0; i < labels.length; i++) {
                path.add(i);
                if(find(labels, palindromeLen - 1, path)) {
                    found = true;
                    break;
                }
                path.remove(0);
            }
        }

        if (found) {
            StringBuilder sb = new StringBuilder();
            for (int num : path) {
                sb.append((num + 1) + " ");
            }
            System.out.println(yes);
            System.out.println(sb.toString().substring(0, sb.length() - 1));
        }
        else {
            System.out.println(no);
        }
    }

    public static void main(String[] args) {
        ABGraph test = new ABGraph();
        int t = test.scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int vertices = test.scanner.nextInt(), palindromeLen = test.scanner.nextInt();
            String[] labels = new String[vertices];
            for (int j = 0; j < vertices; j++) {
                labels[j] = test.scanner.nextLine();
            }
            test.findPalindrome(labels, palindromeLen + 1);
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
