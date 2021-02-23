package matieral.math;
import java.util.*;
import matieral.common_use.ListNode;
/**
 * Test: https://leetcode.com/problems/random-pick-index/ => Like
 * Test: https://leetcode.com/problems/linked-list-random-node/
 */

public class ReservoirSampling {
    private int[] nums;
    private Random rand;

    public ReservoirSampling(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }

    public int pick(int target) {
        int count = 0;
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                count++;

                if (rand.nextInt(count) == 0) {
                    idx = i;
                }
            }
        }
        return idx;
    }
}

class ReservoirSampling2 {
    private ListNode head;

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public ReservoirSampling2(ListNode head) {
        this.head = head;
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int scope = 1, ret = 0;
        ListNode tmp = head;
        while (tmp != null) {
            if (Math.random() < 1.0 / scope) {
                ret = tmp.value;
            }
            scope++;
            tmp = tmp.next;
        }
        return ret;
    }
}
