class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class FastSlowPointers {
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}