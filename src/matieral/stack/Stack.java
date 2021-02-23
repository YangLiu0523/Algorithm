package matieral.stack;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/maximum-score-from-removing-substrings/
 * Test: https://leetcode.com/problems/online-stock-span/ => Like
 *
 */
public class Stack {
    public int maximumGain(String s, int x, int y) {
        boolean aFirst = x >= y;
        int ret = 0;
        int big = Math.max(x, y), small = Math.min(x, y);
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c != 'a' && c != 'b') {
                int cntA = 0, cntB = 0;
                while (!stack.isEmpty()) {
                    if (stack.pop() == 'a') cntA++;
                    else cntB++;
                }
                ret += small* Math.min(cntA, cntB);
            }
            else {
                if (aFirst) {
                    if (c == 'b' && !stack.isEmpty() && stack.peek() == 'a') {
                        stack.pop();
                        ret += big;
                    }
                    else {
                        stack.push(c);
                    }
                }
                else {
                    if (c == 'a' && !stack.isEmpty() && stack.peek() == 'b') {
                        stack.pop();
                        ret += big;
                    }
                    else {
                        stack.push(c);
                    }
                }
            }
        }

        int cntA = 0, cntB = 0;
        while (!stack.isEmpty()) {
            if (stack.pop() == 'a') cntA++;
            else cntB++;
        }
        return ret + small * Math.min(cntA, cntB);
    }
}

class StockSpanner {
    Deque<int[]> stack;
    public StockSpanner() {
        stack = new ArrayDeque<>();
    }

    public int next(int price) {
        int cnt = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            cnt += stack.pop()[1];
        }
        stack.push(new int[]{price, cnt});
        return cnt;
    }
}