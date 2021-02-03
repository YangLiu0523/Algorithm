package matieral.math;

/**
 * Test: https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/
 */

public class numIsTwoPow {

    public boolean isTwoPow(int i) {
        return Math.pow(2, (int) (Math.log(i) / Math.log(2))) == i;
    }
}
