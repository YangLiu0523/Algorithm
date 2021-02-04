package matieral.list;
import matieral.common_use.ListNode;

/**
 * Ref: https://www.educative.io/courses/grokking-the-coding-interview/g76PJVmL5PZ
 *
 * Test: https://leetcode.com/problems/linked-list-cycle/
 * Test: https://leetcode.com/problems/linked-list-cycle-ii/
 * Test: https://leetcode.com/problems/happy-number/
 * Test: https://leetcode.com/problems/middle-of-the-linked-list/
 * Test：https://leetcode.com/problems/palindrome-linked-list/
 * Test: https://leetcode.com/problems/reorder-list/
 * Test: https://leetcode.com/problems/circular-array-loop/
 */

public class FastSlowPointer {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true; // !!
        }
        return false;
    }

    // Find cycle start & find cycle length
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        int cycleLen = 0;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                cycleLen = calculateCycleLength(slow);
                break;
            }
        }
        if (cycleLen == 0) {
            return null;
        }

        fast = head;
        slow = head;
        for (int i = 0; i < cycleLen; i++) {
            fast = fast.next;
        }

        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    private int calculateCycleLength(ListNode node) {
        ListNode pnt = node;
        int len = 0;
        while (pnt != null) {
            pnt = pnt.next;
            len++;
            if (pnt == node) return len;
        }
        return 0;
    }

    public boolean isHappy(int n) {
        int slow = n, fast = n;
        while (fast != 1) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
            if (slow == fast) {
                return fast == 1;
            }
            if (fast == 1) return true;
        }
        return fast == 1;

    }

    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n /= 10;
            totalSum += d* d;
        }
        return totalSum;
    }

    public ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode reversedSecond = reverse(slow);
        ListNode secondPnt = reversedSecond;
        ListNode pnt = head;
        while (pnt != null && secondPnt != null) {
            if (pnt.value != secondPnt.value) break;
            pnt = pnt.next;
            secondPnt = secondPnt.next;
        }

        reverse(reversedSecond);
        return pnt == null || secondPnt == null;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public void reorderList(ListNode head) {
        ListNode mid = findMiddle(head);
        ListNode reversedSecond = reverse(mid);
        intersect(head, reversedSecond);
    }

    private ListNode findMiddle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private void intersect(ListNode first, ListNode second) {
        ListNode pnt1 = first, pnt2 = second;
        ListNode tmp;
        while (pnt1 != null && pnt2 != null) {
            tmp = pnt1.next;
            pnt1.next = pnt2;
            pnt1 = tmp;

            tmp = pnt2.next;
            pnt2.next = pnt1;
            pnt2 = tmp;
        }

        if (pnt1 != null) pnt1.next = null;
    }

    public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int slow = i, fast = i;
            boolean isForward = nums[i] > 0;
            while (fast != -1) {
                slow = findNextIdx(nums, slow, isForward);
                fast = findNextIdx(nums, findNextIdx(nums, fast, isForward), isForward);
                if (slow == fast && slow != -1) return true;
            }
        }
        return false;
    }

    private int findNextIdx(int[] nums, int idx, boolean forward) {
        if (idx == -1) return -1;
        int n = nums.length;
        int nextIdx = (idx + nums[idx] % n + n) % n; // Error prone [-2, -3, -9]
        if (forward && nums[nextIdx] < 0 || !forward && nums[nextIdx] > 0 || idx == nextIdx)         {
            return -1;
        }
        return nextIdx;
    }
}
