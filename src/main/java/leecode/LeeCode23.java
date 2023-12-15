package leecode;

import model.ListNode;

import java.util.*;

/**
 * 兩數之和
 */
public class LeeCode23 {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode listNode2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode listNode3 = new ListNode(2, new ListNode(6));
        ListNode[] listNodes = new ListNode[]{listNode, listNode2, listNode3};
        ListNode result = new LeeCode23().mergeKLists(listNodes);

        print(result);
    }

    public static void print(ListNode list) {
        System.out.println("val: " + list.val);
        if (list.next != null) print(list.next);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val) return -1;
                else if (o1.val == o2.val) return 0;
                else return 1;
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        for (ListNode node : lists) {
            if (node != null) queue.add(node);
        }
        while (!queue.isEmpty()) {
            p.next = queue.poll();
            p = p.next;
            if (p.next != null) queue.add(p.next);
        }
        return dummy.next;
    }

//    public ListNode mergeKLists(ListNode[] lists) {
//        if(lists == null || lists.length <= 0) return null;
//
//        Integer min = null;
//        Map<Integer, List<Integer>> map = new HashMap<>();
//        ListNode listNode = null;
//        ListNode tmp = null;
//        boolean close = false;
//        while (!close) {
//            min = null;
//            map.clear();
//            for (int i = 0; i < lists.length; i++) {
//                if (lists[i] != null) {
//
//                    if (min == null || lists[i].val < min) min = lists[i].val;
//                    if (map.get(lists[i].val) == null) map.put(lists[i].val, new ArrayList<>());
//                    map.get(lists[i].val).add(i);
//                }
//            }
//
//            System.out.println(map.size());
//            if (map.size() == 0) {
//                tmp = null;
//                close = true;
//            } else {
//                for (Integer idx : map.get(min)) {
//                    if(listNode != null) {
//                        tmp.next = new ListNode();
//                        tmp = tmp.next;
//                    } else {
//                        listNode = new ListNode(lists[idx].val);
//                        tmp = listNode;
//                    }
//                    System.out.println("put:" + lists[idx].val);
//                    tmp.val = lists[idx].val;
//                    lists[idx] = lists[idx].next;
//                }
//            }
//        }
//        return listNode;
//    }
}
