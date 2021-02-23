package matieral.design;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/min-stack/
 * Test: https://leetcode.com/problems/maximum-frequency-stack/
 */
public class Stack {
}

class MinStack {
    Deque<Integer> stack1;
    Deque<Integer> stack2;
    /** initialize your data structure here. */
    public MinStack() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }

    public void push(int x) {
        if (stack2.isEmpty() || stack2.peek() >= x) {
            stack2.push(x);
        }
        stack1.push(x);
    }

    public void pop() {
        int elem = stack1.pop();
        if (stack2.peek() == elem) stack2.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int getMin() {
        return stack2.peek();
    }
}

class Elem implements Comparable<Elem>{
    int number;
    int freq;
    int seq;

    public Elem(int num, int freq, int seq) {
        this.number = num;
        this.freq = freq;
        this.seq = seq;
    }

    public int compareTo(Elem e) {
        return this.freq == e.freq ? e.seq - this.seq : e.freq - this.freq;
    }
}

class FreqStack {
    int seq;
    Map<Integer, Integer> freq;
    PriorityQueue<Elem> pq;

    public FreqStack() {
        pq = new PriorityQueue<>();
        freq = new HashMap<>();
        seq = 0;
    }

    public void push(int x) {
        freq.put(x, freq.getOrDefault(x, 0) + 1);
        pq.offer(new Elem(x, freq.get(x), seq++));
    }

    public int pop() {
        int ret = pq.poll().number;
        freq.put(ret, freq.get(ret) - 1);
        return ret;
    }
}