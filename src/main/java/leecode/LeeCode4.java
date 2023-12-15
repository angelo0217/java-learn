package leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 無重複字串長度
 */
public class LeeCode4 {

    public static void main(String[] args) {
        var nums1 = new int[] {};
        var nums2 = new int[] {2, 3};
        System.out.println(new LeeCode4().findMedianSortedArrays(nums1, nums2));
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] newNums = new int[nums1.length + nums2.length];
        int i = 0;
        int x = 0;
        int y = 0;

        while(x < nums1.length  || y < nums2.length){
            if(x == nums1.length){
                newNums[i] = nums2[y];
                y++;
            } else if (y == nums2.length){
                newNums[i] = nums1[x];
                x++;
            } else if(nums1[x] < nums2[y]) {
                newNums[i] = nums1[x];
                x++;
            } else {
                newNums[i] = nums2[y];
                y++;
            }

            i++;
        }

        Arrays.stream(newNums).forEach(n -> System.out.println("n:" + n));

        if(newNums.length % 2 == 1){
            return newNums[newNums.length/2];
        } else {
            System.out.println(newNums.length/2);
            return (double) (newNums[newNums.length/2] + newNums[newNums.length/2 - 1]) / 2;
        }
    }
}
