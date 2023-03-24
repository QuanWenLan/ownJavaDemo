package tags.tanxin;

/**
 * @author Vin lan
 * @className MaxSubArray
 * @description 53 最大子数组和
 * @createTime 2022-08-17  10:06
 **/
public class MaxSubArray {
    public static void main(String[] args) {
        MaxSubArray obj = new MaxSubArray();
        int[] nums = new int[]{-1, -1, -1, -2, -3};
        System.out.println(obj.maxSubArray(nums));
    }

    public int maxSubArray(int[] nums) {
        // 当前连续和为负数之后，从下一个元素开始计算和
        int result = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            count += value;
            if (count > result) {
                result = count;
            }
            if (count <= 0) {
                count = 0;
            }
        }
        return result;
    }
}
