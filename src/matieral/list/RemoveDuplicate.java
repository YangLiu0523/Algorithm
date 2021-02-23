package matieral.list;
import matieral.common_use.ListNode;

/**
 * Test: https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * Test: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 */
public class RemoveDuplicate {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        while (head != null) {
            if (head.next != null && head.next.value == head.value) {
                while (head.next != null && head.next.value == head.value) {
                    head = head.next;
                }
                prev.next = head.next; // Important
            }
            else {
                prev = prev.next;
            }
            head = head.next;
        }
        return dummy.next;
    }
}
