package tags.tanxin;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className LargestSumAfterKNegations
 * @description 1005. K 次取反后最大化的数组和
 * https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations/
 * @createTime 2022-08-19  13:58
 **/
public class LargestSumAfterKNegations {
    public static void main(String[] args) {
        LargestSumAfterKNegations obj = new LargestSumAfterKNegations();
        int [] nums = new int [] {-8,3,-5,-3,-5,-2};
        int sum = obj.largestSumAfterKNegations(nums, 6);
        System.out.println(sum);
    }

    public int largestSumAfterKNegations(int[] nums, int k) {
        // 先将数组排序，负数会在前面
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {
            // k 执行一次少一次，大于0才执行修改
            if (k > 0 && nums[i] < 0) {
                // 判断是不是负数，每个负数都变成正数才是最大和（一次贪心）
                nums[i] = -1 * nums[i];
                k--;
            }
        }
        // System.out.println(Arrays.toString(nums));
        // 如果是正数，最大限度修改最小的数，最小的数 （两次贪心）
        Arrays.sort(nums);
        if (k % 2 == 1) nums[0] = -1 * nums[0];
        // System.out.print(Arrays.toString(nums));
        return Arrays.stream(nums).sum();
    }
}
