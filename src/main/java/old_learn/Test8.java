package old_learn;

public class Test8 {
    /*
    無法非常理解題目，真的滿怪的
    https://ithelp.ithome.com.tw/articles/10213270
     */
    public static void main(String[] args) {
        int[] nums = new int[]{0, -2, 1, 4, -5, 5, 6, 7, 8};
        int[] nums2 = new int[]{7, -2, 1, 4};
        System.out.println(maxSubArray(nums2));
    }

    public static int maxSubArray(int[] nums) {
        int res = nums[0];
        int curr = nums[0];
        for(int i = 1; i < nums.length; i++) {
            curr += nums[i];
            System.out.println("curr:" + curr);
            if (curr < 0 || nums[i] > curr)
                curr = nums[i];
            if (res < curr)
                res = curr;
        }
        return res;
    }
}
