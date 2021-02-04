package matieral.list;
import matieral.common_use.ListNode;

/**
 * Test: https://leetcode.com/problems/reverse-linked-list/
 * Test: https://leetcode.com/problems/reverse-linked-list-ii/
 * Test: https://leetcode.com/problems/reverse-nodes-in-k-group/
 * Test: https://leetcode.com/problems/rotate-list/
 */

public class Reverse {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode curr = head, prev = null;
        for (int i = 0; i < m - 1; i++) {
            prev = curr;
            curr = curr.next;
        }

        ListNode lastPartFromFirst = prev;
        ListNode lastNodeOfSubList = curr;
        ListNode next = null;
        prev = null;
        for (int i = 0; i < n - m + 1 && curr != null; i++) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        if (lastPartFromFirst != null) lastPartFromFirst.next = prev;
        else head = prev;

        lastNodeOfSubList.next = curr;
        return head;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode lastNodeFromLastList = null;
        ListNode currLastNode = head;
        int listLen = listLen(head);
        int groups = listLen / k;
        ListNode prev = null, next = null, curr = head;
        for (int i = 0; i < groups; i++) {
            prev = null;
            for (int j = 0; j < k; j++) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            if (lastNodeFromLastList == null) {
                head = prev;
            }
            else {
                lastNodeFromLastList.next = prev;
            }
            currLastNode.next = curr;

            lastNodeFromLastList = currLastNode;
            currLastNode = curr;
        }
        return head;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null) {
            return head;
        }

        int len = listLen(head);
        k %= len;
        if (k == 0) return head;

        ListNode newHead = head, prev = null;
        for (int i = 0; i < len - k; i++) {
            prev = newHead;
            newHead = newHead.next;
        }
        prev.next = null;

        ListNode pnt = newHead;
        while (pnt.next != null) pnt = pnt.next;
        pnt.next = head;
        return newHead;

    }

    private int listLen(ListNode head) {
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            len++;
        }
        return len;
    }
}
