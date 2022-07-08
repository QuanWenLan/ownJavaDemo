package tags.array;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className RemoveElement27
 * @description 27 移除元素
 * https://leetcode.cn/problems/remove-element
 * @createTime 2021-12-03  14:26
 **/
public class RemoveElement {
    public static void main(String[] args) {
        RemoveElement obj = new RemoveElement();
        System.out.println(obj.removeElement(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}, 2));
        System.out.println(obj.removeElement2(new int[]{1, 1, 2, 2, 3, 3}, 2));
    }

    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int i = 0;
        for (; i < len; i++) {
            if (nums[i] == val) {
                // 更新数组，将后面的往前移1位
                for (int j = i; j < len - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                i--;
                len--; // 后面移动的数也就不需要继续判断了
            }
        }
        System.out.println(Arrays.toString(nums));
        return i;
    }

    /**
     * 如果 fast 遇到需要去除的元素，则直接跳过，否则就告诉 slow 指针，并让 slow 前进一步。
     */
    public int removeElement2(int[] nums, int val) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
