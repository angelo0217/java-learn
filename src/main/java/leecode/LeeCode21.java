package leecode;

import model.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeeCode21 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode listNode2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        ListNode result = new LeeCode21().mergeTwoLists(listNode, listNode2);
        print(result);
    }

    public static void print(ListNode list){
        System.out.println(list.val);
        if(list.next != null) print(list.next);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) return null;
        ListNode listNode = new ListNode();
        setNode(l1, l2, listNode);
        return listNode;
    }

    public void setNode(ListNode l1, ListNode l2, ListNode result){
        if(l1 == null){
            result.val = l2.val;
            result.next = l2.next;
        } else if(l2 == null){
            result.val = l1.val;
            result.next = l1.next;
        } else {
            if(l1.val < l2.val){
                result.val = l1.val;
                result.next = new ListNode();
                setNode(l1.next, l2, result.next);
            } else {
                result.val = l2.val;
                result.next = new ListNode();
                setNode(l1, l2.next, result.next);
            }
        }
    }

}
