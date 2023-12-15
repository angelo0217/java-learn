package leecode;

import model.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 兩數相加
 */
public class LeeCode2 {

    public static void main(String[] args){
//        int[] nums1 = new int[]{9,9,9,9,9,9,9};
//        int[] nums2 = new int[]{9,9,9,9};
//        List<Integer> ans = new LeeCode2().towArySum(nums2, nums1);
//
//        ans.forEach(num -> System.out.println(num));

        ListNode listNode = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))));
        ListNode listNode2 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))))));

        ListNode ans = new LeeCode2().addTwoNumbers(listNode, listNode2);

        boolean hasNext = true;
        while (hasNext){
            ans = printListNode(ans);
            if(ans == null){
                hasNext = false;
            }
        }
    }

    public static ListNode printListNode(ListNode ans){
        System.out.println(ans.val);
        return ans.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = null;
        ListNode tmp = null;
        boolean hasNext = true;
        int sum;
        boolean push = false;
        while(hasNext){
            sum = ((l1 == null)? 0 : l1.val) + ((l2 == null)? 0 : l2.val);

            if(push) sum = sum + 1;

            push = false;
            if(sum >= 10){
                push = true;
                sum = sum - 10;
            }

            if(ans == null){
                ans = new ListNode(sum);
                tmp = ans;
            } else {
                tmp.val = sum;
            }

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
            if(l1 == null && l2 == null){
                hasNext = false;
            } else {
                tmp.next = new ListNode();
                tmp = tmp.next;
            }
        }
        if(push) tmp.next = new ListNode(1);;

        return ans;
    }


    public List<Integer> towArySum(int[] nums1, int[] nums2){
        var ans = new ArrayList<Integer>();
        int max = (nums1.length > nums2.length)? nums1.length : nums2.length;
        boolean push = false;
        for(int i = 0; i < max; i ++){
            int sum = (nums1.length <= i)? 0 + nums2[i] : (nums2.length <= i) ? 0 + nums1[i] : nums1[i] + nums2[i];

            if(push) sum = sum + 1;
            push = false;
            if(sum >= 10){
                push = true;
                sum = sum - 10;
            }
            ans.add(sum);
        }
        if(push) ans.add(1);
        return ans;
    }
}
