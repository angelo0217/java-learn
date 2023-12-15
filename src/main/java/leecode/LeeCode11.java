package leecode;

import java.util.Arrays;

/**
 * 無重複字串長度
 */
public class LeeCode11 {

    public static void main(String[] args) {
        var nums = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println("ans:" + new LeeCode11().maxArea(nums));
    }


    public int maxArea(int[] height) {
        int s = 0, e = height.length -1, max = 0;

        while (s < e){
            max = Math.max(max, Math.min(height[s], height[e]) * (e - s));

            if(height[s] < height[e]){
                s++;
            } else {
                e--;
            }
        }

        return max;
    }
}
