package old_learn;

import java.util.Arrays;

public class Test6 {
    public static void main(String[] args){
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        int cnt = removeDuplicates(nums);
        System.out.println("cnt: " + cnt);
        Arrays.stream(nums).forEach(num -> System.out.println(num));
    }
    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
