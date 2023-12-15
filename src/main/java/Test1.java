import java.util.HashMap;
import java.util.Map;

public class Test1 {
    public static void main(String[] args){
        int[] nums = new int[]{2, 7, 11, 15};
        int[] result = twoSum(nums, 9);
        for(int i = 0; i < result.length; i ++){
            System.out.println(result[i]);
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No Answer");
    }
}
