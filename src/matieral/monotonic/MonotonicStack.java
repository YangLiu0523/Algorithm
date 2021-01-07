package matieral.monotonic;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/daily-temperatures/
 * Test: https://leetcode.com/problems/jump-game-v/
 * Test: https://leetcode.com/problems/odd-even-jump/ => not typical
 *
 * Reference: https://leetcode.com/problems/odd-even-jump/discuss/977074/Java-mono-Stack-easy-understanding
 *
 * normal mono stack you used before can find the first bigger/smaller elements on the right and left(depending on increasing or decreasing mono stack you use).
 * Use decreasing mono stack here can help us find the first bigger index.
 */

public class MonotonicStack {
    public int[] dailyTemperatures(int[] T) {
        int[] ret = new int[T.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = T.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
                stack.pop();
            }
            ret[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return ret;
    }

    public int maxJumps(int[] arr, int d) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] maxSteps = new int[arr.length];
        Arrays.fill(maxSteps, 1);
        for (int i = 0; i < arr.length; i++) {
            while(!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                int idx = stack.pop();
                int m = 0;
                for (int j = idx + 1; j <= idx + d && j < i; j++) {
                    if (arr[j] == arr[idx]) break;
                    m = Math.max(m, maxSteps[j]);
                }
                maxSteps[idx] = Math.max(maxSteps[idx], m + 1);
                if (idx + d >= i) {
                    maxSteps[i] = Math.max(maxSteps[i], maxSteps[idx] + 1);
                }
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int idx = stack.pop();
            int m = 0;
            for (int j = idx + 1; j <= idx + d && j < arr.length; j++) {
                if (arr[j] == arr[idx]) break;
                m = Math.max(m, maxSteps[j]);
            }
            maxSteps[idx] = Math.max(maxSteps[idx], m + 1);
        }

        int ret = 1;
        for (int i = 0; i < arr.length; i++) {
            ret = Math.max(ret, maxSteps[i]);
        }
        return ret;
    }

    private void helper(int[] A, int[] next, boolean isIncrease) {
        Integer[] idx = new Integer[A.length];
        for (int i = 0; i < A.length; i++) {
            idx[i] = i;
        }

        if (isIncrease) {
            Arrays.sort(idx, (i, j) -> A[i] == A[j] ? i - j : A[i] - A[j]);
        }
        else {
            Arrays.sort(idx, (i, j) -> A[i] == A[j] ? i - j : A[j] - A[i]);
        }

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < A.length; i++) {
            while (!stack.isEmpty() && stack.peek() <= idx[i]) {
                next[stack.pop()] = idx[i];
            }
            stack.push(idx[i]);
        }
    }

    public int oddEvenJumps(int[] A) {
        int n = A.length;
        int[] oddNext = new int[n]; // Ceiling
        Arrays.fill(oddNext, n);
        int[] evenNext = new int[n]; // Floor
        Arrays.fill(evenNext, n);

        helper(A, oddNext, true);
        helper(A, evenNext, false);

        boolean[] canOdd = new boolean[n];
        boolean[] canEven = new boolean[n];
        canOdd[n - 1] = canEven[n - 1] = true;

        for (int i = n - 2; i >= 0; i--) {
            canOdd[i] = oddNext[i] == n ? false : canEven[oddNext[i]];
            canEven[i] = evenNext[i] == n ? false : canOdd[evenNext[i]];
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (canOdd[i]) cnt++;
        }
        return cnt;
    }

}
