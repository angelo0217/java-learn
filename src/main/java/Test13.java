import java.util.ArrayList;
import java.util.List;

public class Test13 {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 2, 4, 3, 7};
//        List<Integer> data = new ArrayList<>();
//        System.out.println(getSunLevel(nums, 7, data));

        getSunGroup(nums, 7);
    }


    public static void getSunGroup(int[] nums, int target) {
        List<List<Integer>> result = levelGroups(0, 0, nums, target, new ArrayList<>(), new ArrayList<>());
        for (List<Integer> integers : result) {
            for (Integer integer : integers) {
                System.out.print(nums[integer] + ",");
            }
            System.out.println();
        }
    }


    public static List<List<Integer>> levelGroups(int level, int val, int[] nums, int target, List<Integer> data, List<List<Integer>> levelGroups) {
        int index = (data.size() != 0)? data.get(data.size() - 1) : level;
        for (int i = index; i < nums.length; i++) {
            if (level == 0) {
                System.out.println("init data");
                data = new ArrayList<>();
            }
            if(!data.contains(i)){

                if (nums[i] + val == target) {
                    List<Integer> newData = new ArrayList<>(data);
                    newData.add(i);
                    levelGroups.add(new ArrayList<>(newData));
                } else {
                    data.add(i);
                    levelGroups(level + 1, nums[i] + val, nums, target, data, levelGroups);
                    data.remove(data.size() - 1);
                }
            }
        }

        return levelGroups;
    }

    public static int getSunLevel(int[] nums, int target, List<Integer> data) {
        int result = levelSum(0, 0, nums, target, data);
        return result;
    }

    public static int levelSum(int level, int val, int[] nums, int target, List<Integer> data) {
        int index = (data.size() != 0)? data.get(data.size() - 1) : level;
        for (int i = index; i < nums.length; i++) {
            if (level == 0) {
                System.out.println("init data");
                data = new ArrayList<>();
            }
            if(!data.contains(i)){
                if (nums[i] + val == target) {
                    System.out.println("~~~~~~~~~~~" + level);
                    return level;
                } else {
                    data.add(i);
                    int sum = levelSum(level + 1, nums[i] + val, nums, target, data);
                    System.out.println("sum:" + sum);
                    if(sum != 0){
                        return level;
                    }
                    data.remove(data.size() - 1);
                }
            }
        }
        return 0;
    }

}

