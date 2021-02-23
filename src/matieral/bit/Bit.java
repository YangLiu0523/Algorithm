package matieral.bit;

/**
 * Test: https://leetcode.com/problems/number-of-1-bits/
 */
public class Bit {
    public int hammingWeight(int n) {
        int bits = 0;
        while (n != 0) {
            bits++;
            n &= (n - 1);
        }
        return bits;
    }
}
