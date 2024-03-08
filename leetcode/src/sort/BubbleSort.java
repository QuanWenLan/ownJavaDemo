package sort;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className BubbleSort
 * @description 冒泡排序
 * @createTime 2022-02-16  14:16
 **/
public class BubbleSort {
    public static void main(String[] args) {
        int[] result = sortArray(new int[]{5, 1, 2, 2, 3, 1, 0, 4});
        System.out.println("结果：" + Arrays.toString(result));
    }

    public static int[] sortArray(int[] nums) {
        System.out.println("原始数组:" + Arrays.toString(nums));
        int len = nums.length;
        //标志位
        boolean flag = true;
        //注意看 for 循环条件
        for (int i = 0; i < len - 1 && flag; ++i) {
            //如果没发生交换，则依旧为false，下次就会跳出循环
            flag = false;
            for (int j = 0; j < len - i - 1; ++j) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    //发生交换，则变为true,下次继续判断
                    flag = true;
                }
            }
        }
        return nums;

    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
