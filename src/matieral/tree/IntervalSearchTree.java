package matieral.tree;

/**
 * Test: https://leetcode.com/problems/my-calendar-i/
 * Reference: https://www.coursera.org/lecture/algorithms-part1/interval-search-trees-ot9vw
 */

class IntervalSearchTree {
    int maxEndpoiont;
    int start;
    int end;
    IntervalSearchTree left;
    IntervalSearchTree right;

    public IntervalSearchTree(int start, int end) {
        this.start = start;
        this.end = end;
        this.left = null;
        this.right = null;
        this.maxEndpoiont = Integer.MIN_VALUE;
    }

    public void insert(int s, int e) {
        if (s > this.start) {
            if (this.right == null) {
                this.right = new IntervalSearchTree(s, e);
                this.right.maxEndpoiont = e;
            }
            else {
                this.right.insert(s, e);
            }
        }
        else {
            if (this.left == null) {
                this.left = new IntervalSearchTree(s, e);
                this.left.maxEndpoiont = e;
            }
            else {
                this.left.insert(s, e);
            }
        }
        this.maxEndpoiont = Math.max(this.maxEndpoiont, Math.max(e, this.end));

    }

    public IntervalSearchTree intersect(int s, int e) {
        if (Math.min(this.end, e) >= Math.max(s, this.start)) {
            return this;
        }
        else if (this.left == null || this.left.maxEndpoiont < s) {
            if (this.right == null) return null;
            else return this.right.intersect(s, e);
        }
        else {
            if (this.left == null) return null;
            else return this.left.intersect(s, e);
        }
    }
}

