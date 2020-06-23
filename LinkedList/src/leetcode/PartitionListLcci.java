package leetcode;

public class PartitionListLcci {
    // 双指针头插法：
    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        // 经典dum
        ListNode dum = new ListNode(-1);
        dum.next = head;
        // 方便插入节点，居家必备prev
        ListNode prev = head;
        head = head.next;
        // prev和head一前一后，经典头插
        while (head != null) {
            if (head.val < x) {
                // 第一次head.val<x，prev停下，head继续，停在下一个>x的node前
                while (head.next != null && head.next.val < x)
                    head = head.next;
                // 相当于中间有不止一个node.val<x，标记第一个aux即prev.next和head指向的最后一个
                // dum指向aux，prev指向head.next，其余不变
                ListNode aux = prev.next;
                prev.next = head.next;
                head.next = dum.next;
                dum.next = aux;
                head = prev.next;
            } else {
                prev = prev.next;
                head = head.next;
            }
        }
        return dum.next;
    }
}
