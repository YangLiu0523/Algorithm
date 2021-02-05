package matieral.design;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/implement-queue-using-stacks/
 * Test: https://leetcode.com/problems/implement-stack-using-queues/
 */

public class StackVSQueue {
    class MyQueue {
        Deque<Integer> stackIn = new ArrayDeque<>();
        Deque<Integer> stackOut = new ArrayDeque<>();
        /** Initialize your data structure here. */
        public MyQueue() {

        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stackIn.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (stackOut.isEmpty()) {
                while (!stackIn.isEmpty()) {
                    stackOut.push(stackIn.pop());
                }
            }
            return stackOut.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (stackOut.isEmpty()) {
                while (!stackIn.isEmpty()) {
                    stackOut.push(stackIn.pop());
                }
            }
            return stackOut.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stackIn.isEmpty() && stackOut.isEmpty();
        }
    }

    class MyStack {
        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        int ret = 0;
        /** Initialize your data structure here. */
        public MyStack() {

        }

        /** Push element x onto stack. */
        public void push(int x) {
            if (q1.isEmpty()) {
                q2.offer(x);
            }
            else q1.offer(x);
            ret = x;
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            if (q1.isEmpty()) {
                while (q2.size() > 1) {
                    ret = q2.poll();
                    q1.offer(ret);

                }
                return q2.poll();
            }
            else {
                while (q1.size() > 1) {
                    ret = q1.poll();
                    q2.offer(ret);
                }
                return q1.poll();
            }
        }

        /** Get the top element. */
        public int top() {
            return ret;
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return q1.isEmpty() && q2.isEmpty();
        }
    }


}
