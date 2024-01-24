package old_learn;

import java.util.Arrays;

public class Test7 {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 4, 5, 6, 7, 8, 10, 11, 15, 21, 66, 77, 88, 89};
        System.out.println("index: " + searchInsert(nums, 7) + ", length: " + nums.length);
    }

    public static Integer searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 0, j = nums.length-1;
        int index = -1;
        while(i <= j) {
            index = (i + j) / 2;
            System.out.println("next:" + index);
            if(nums[index] == target)
                return index;
            if(nums[index] > target)
                j = index - 1;
            else
                i = index + 1;
        }
        return i;
    }
}
