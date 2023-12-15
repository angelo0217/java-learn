package leecode;

import model.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeeCode19 {
    public static void main(String[] args) {
//        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode ans = new LeeCode19().removeNthFromEnd(listNode, 2);
        boolean hasNext = true;
        while (hasNext) {
            ans = LeeCode2.printListNode(ans);
            if (ans == null) {
                hasNext = false;
            }
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int i = 1;
        ListNode beta = head;
        while (beta.next != null) {
            beta = beta.next;
            i++;
        }
        System.out.println("l: " + i);
        ListNode listNode = new ListNode();
        if (head.next != null) {
            if (i == n) {
                return head.next;
            } else {
                listNode.val = head.val;
                setListNode(listNode, head.next, i - 1, n);
            }
        } else {
            return null;
        }

        return listNode;
    }

    public void setListNode(ListNode listNode, ListNode source, int i, int n) {
        System.out.println("i:"+ i +",n:" + n + ", source:" + source.val);
        if (i == n) {
            listNode.next = source.next;
        } else {
            if(source.next != null) {
                i--;
                listNode.next = new ListNode(source.val);
                setListNode(listNode.next, source.next, i, n);
            }
        }
    }
}
