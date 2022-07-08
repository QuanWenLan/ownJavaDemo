package tags.array;

import java.util.HashMap;

/**
 * @author Vin lan
 * @className TwoNumSum 1
 * @description TODO 两数之和  https://leetcode-cn.com/problems/two-sum
 * @createTime 2020-12-17  17:34
 **/
public class TwoNumSum {
    public static void main(String[] args) {
        int[] result = twoSum(new int[]{2, 7, 11, 15}, 9);
        int[] result2 = twoSum2(new int[]{2, 7, 11, 15}, 9);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    /**
     * 执行用时：2 ms , 在所有 Java 提交中击败了41.37%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了41.81%的用户
     * 解析：
     * 注意到暴力解法的时间复杂度较高的原因是寻找 target - x 的时间复杂度过高。因此，我们需要一种更优秀的方法，能够快速寻找数组中是否存在目标元素。如果存在，我们需要找出它的索引。
     * 使用哈希表，可以将寻找 target - x 的时间复杂度降低到从 O(N)O(N) 降低到 O(1)O(1)
     * 如果存在对应的一个值，则直接返回这个值所对应的索引和它本身所对应的索引
     */
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }

        return new int[0];
    }

    /**
     * 暴力解法
     * 执行用时：2 ms , 在所有 Java 提交中击败了11%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了41.81%的用户
     */
    public static int[] twoSum2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int firstNum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int twoNum = nums[j];
                if (target == firstNum + twoNum) {
                    int[] a = {i, j};
                    return a;
                }
            }
        }
        return new int[]{};
    }
}

