package tags.array;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className RunningSum
 * @description 1480. 一维数组的动态和
 * https://leetcode.cn/problems/running-sum-of-1d-array/
 * @createTime 2022-07-29  17:09
 **/
public class RunningSum {
    /**
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,6,10]
     * 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4]
     * 1 <= nums.length <= 1000
     * -10^6 <= nums[i] <= 10^6
     */

    public static void main(String[] args) {
        RunningSum obj = new RunningSum();
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(obj.runningSum(nums)));
    }

    public int[] runningSum(int[] nums) {
        int len = nums.length;
        // 需要记录上一个位置处的和
        int curIndexSum = nums[0];
        for (int i = 1; i < len; i++) {
            int curVal = nums[i];
            curIndexSum += curVal;
            nums[i] = curIndexSum;
        }
        return nums;
    }
}
