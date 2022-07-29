package tags.array;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className ExchangeOddAndEven
 * @description 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 * @createTime 2022-07-29  17:29
 **/
public class ExchangeOddAndEven {
    public static void main(String[] args) {
        ExchangeOddAndEven obj = new ExchangeOddAndEven();
        int[] nums = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(obj.exchange(nums)));
    }

    public int[] exchange(int[] nums) {
        int left = 0, right = nums.length - 1;
        // 使用双指针
        while (left < right) {
            // 如果左边是偶数，右边奇数则交换顺序
            if (nums[left] % 2 == 0 && nums[right] % 2 != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                // 如何实现有序呢？
                /*if (left > 0 && nums[left - 1] > nums[left]) {
                    int temp2 = nums[left - 1];
                    nums[left - 1] = nums[left];
                    nums[left] = temp2;
                }*/
                left++;
                right--;
            } else if (nums[right] % 2 == 0) {
                // 都是偶数，右边的往前移动一位后再判断
                right--;
            } else if (nums[left] % 2 != 0) {
                // 都是奇数，左边的往后移动一位再判断
                left++;
            }
        }
        return nums;
    }
}
