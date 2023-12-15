package leecode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 兩數之和
 */
public class LeeCode1 {

    public static void main(String[] args){
        int[] nums = new int[]{2, 7, 11, 15};
        int[] ans = new LeeCode1().towSums(nums, 9);

        Arrays.stream(ans).forEach(num -> System.out.println(num));
    }


    public int[] towSums(int[] nums, int target){
        var map = new HashMap<Integer, Integer>();
        int diff;
        for(int i = 0; i < nums.length; i ++){
            diff = target - nums[i];
            if(map != null && map.get(diff) != null){
                return new int[]{map.get(diff), i};
            }
            map.put(nums[i], i);
        }

        throw new IllegalArgumentException("No Answer");
    }
}
