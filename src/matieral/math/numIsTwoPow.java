package matieral.math;

/**
 * Test: https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/
 */

public class numIsTwoPow {

    public boolean isTwoPow(int i) {
        return (i & (i - 1)) == 0;
    }
}
