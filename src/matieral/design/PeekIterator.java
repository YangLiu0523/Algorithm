package matieral.design;

import java.util.*;

/**
 * Test: https://leetcode.com/problems/peeking-iterator/
 */
class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> iter;
    Integer head;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        iter = iterator;
        if (iter.hasNext()) {
            head = iter.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return head;

    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        int tmp = head;
        if (iter.hasNext()) head = iter.next();
        else head = null;
        return tmp;
    }

    @Override
    public boolean hasNext() {
        return head != null;
    }
}