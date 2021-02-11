import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class PaintingtheArrayII {
    MyScanner scanner = new MyScanner();

    public int findMin(int n, int[] arr, LinkedList<Integer> l1, LinkedList<Integer> l2, int i) { // maximum
        if (i == n) {
            return l1.size() + l2.size();
        }

        int num = arr[i];
        boolean putInA = l1.isEmpty() || l1.getLast() != num;
        boolean putInB = l2.isEmpty() || l2.getLast() != num;
        if (l1.isEmpty() && l2.isEmpty()) putInB = false;

        int ans = 0;
        if (!putInA && !putInB) {
            ans =  findMin(n, arr, l1, l2, i + 1);
        }
        else if (putInA && !putInB) {
            l1.add(num);
            ans =  findMin(n, arr, l1, l2, i + 1);
            l1.removeLast();

        }
        else if (!putInA && putInB) {
            l2.add(num);
            ans =  findMin(n, arr, l1, l2, i + 1);
            l2.removeLast();
        }
        else {
            l1.addLast(num);
            int a = findMin(n, arr, l1, l2, i + 1);
            l1.removeLast();

            l2.addLast(num);
            int b = findMin(n, arr, l1, l2, i + 1);
            l2.removeLast();

            ans = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {
        PaintingtheArrayII test = new PaintingtheArrayII();
        int n = test.scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = test.scanner.nextInt();
        }
//        test.findMin(n, arr, );
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
