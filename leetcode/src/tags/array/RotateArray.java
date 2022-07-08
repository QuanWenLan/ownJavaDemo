package tags.array;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className RotateArray189
 * @description 旋转数组 189
 * https://leetcode-cn.com/problems/rotate-array/
 * @createTime 2021-06-10  14:18
 **/
public class RotateArray {
    public static void main(String[] args) {
        rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        System.out.println();
        rotate2(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        System.out.println();
        new RotateArray().rotate3(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
    }

    /**
     * 向右移动一次，就相当与 队头元素 出队，后一个元素变成对头；出队的元素重新入队，变成 队尾
     * 裂开，执行时间超时
     *
     * @param nums
     * @param k    出队 k 次，入队 k 次
     */
    public static void rotate(int[] nums, int k) {
        int len = nums.length;
        for (int i = 0; i < k; i++) {
            // 队头
            int head = nums[len - 1];
            for (int j = len - 1 - 1; j >= 0; j--) {
                nums[j + 1] = nums[j];
            }
            nums[0] = head;
        }
    }

    /**
     *
     */
    public static void rotate2(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
        Arrays.stream(nums).forEach(r ->System.out.print(r + " "));
    }

    /**
     * https://leetcode-cn.com/problems/rotate-array/solution/shu-zu-fan-zhuan-xuan-zhuan-shu-zu-by-de-5937/
     * 翻转数组
     */
    public void rotate3(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        Arrays.stream(nums).forEach(r ->System.out.print(r + " "));
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }

}
