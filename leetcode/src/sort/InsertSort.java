package sort;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className InsertSort
 * @description 插入排序
 * @createTime 2022-02-16  11:42
 **/
public class InsertSort {
    public static void main(String[] args) {
        int[] result = insertSort(new int[]{5, 1, 2, 2, 3, 1, 0, 4});
        System.out.println("结果：" + Arrays.toString(result));
    }

    /**
     * @param nums 5,1,2,2,3,1,0
     * @return 排好序的数组
     */
    private static int[] insertSort(int[] nums) {
        // 过程：1，第一个数字 5 是已经有序的，所以从第二个数字开始，和已经有序的序列进行比较
        // 首先记录下来这个戴排序的数字（这里是 1）
        // 2
        // 2
        // 2
        // 2
        System.out.println("原始数组:" + Arrays.toString(nums));
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            // 需要比较的数字
            int needOrderValue = nums[i];
            // i 前面已经有序的序列
            int j = i - 1;
            for (; j >= 0; j--) {
                if (nums[j] > needOrderValue) {
                    // 往后移动
                    nums[j + 1] = nums[j];
                } else {
                    // 小于或等于的情况，位置不需要改动，直接退出循环
                    break;
                }
            }
            nums[j + 1] = needOrderValue;
            System.out.println(Arrays.toString(nums));
        }
        return nums;
    }
}
