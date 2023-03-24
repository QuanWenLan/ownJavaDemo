package tags.array;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className SortedSquares
 * @description 977 有序数组的平方
 * @createTime 2023-03-20  14:24
 **/
public class SortedSquares {
    public static void main(String[] args) {
        SortedSquares obj = new SortedSquares();
        System.out.println(Arrays.toString(obj.sortedSquares(new int[]{2, 3, 4, 5, 6})));
    }

    public int[] sortedSquares(int[] nums) {
        int len = nums.length;

        //1 解法
        /*for(int i = 0; i < len; i++) {
         nums[i] = nums[i] * nums[i];
         }

         // 排序
         Arrays.sort(nums);
         */

        //2 解法
        // 需要重新定义一个数组用来装结果
        int[] ans = new int[len];
        // 注意这里要i <= j，因为最后要处理两个元素
        for(int i = 0, j = len - 1, pos = len - 1; i <= j; --pos) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                ans[pos] = nums[i] * nums[i];
                ++i;
            } else {
                ans[pos] = nums[j] * nums[j];
                --j;
            }
        }
        return  ans;
    }
}
