package sort;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className SelectSort
 * @description
 * @createTime 2022-02-16  12:24
 **/
public class SelectSort {
    public static void main(String[] args) {
        int[] result = selectSort(new int[]{5, 1, 2, 2, 3, 1, 0, 4});
        System.out.println("结果：" + Arrays.toString(result));
    }

    private static int[] selectSort(int[] nums) {
        System.out.println("原始数组：" + Arrays.toString(nums));
        // 步骤：遍历数组，数组的第一个是有序的，假设第一个数是最小的数，在后面的序列中找出第二小的数
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int assumeMinIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (nums[assumeMinIndex] > nums[j]) {
                    assumeMinIndex = j;
                }
            }
            // 找到了最小的数的索引,假设的最小的数不是最小的时候，交换这个两个数，
            if (assumeMinIndex != i) {
                int temp = nums[assumeMinIndex];
                nums[assumeMinIndex] = nums[i];
                nums[i] = temp;
            }
        }
        return nums;
    }
}
