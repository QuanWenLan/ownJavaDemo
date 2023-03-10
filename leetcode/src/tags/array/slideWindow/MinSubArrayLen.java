package tags.array.slideWindow;

/**
 * @author Vin lan
 * @className MminSubArrayLen
 * @description 209.长度最小的子数组  https://leetcode.cn/problems/minimum-size-subarray-sum/
 * @createTime 2023-03-10  14:24
 **/
public class MinSubArrayLen {
    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     */
    public static void main(String[] args) {
        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(minSubArrayLen(15, new int[]{1, 2, 3, 4, 5}));
        System.out.println(minSubArrayLen2(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(minSubArrayLen2(15, new int[]{1, 2, 3, 4, 5}));
    }

    /**
     * 自己解法，for循环遍历，执行通过
     * 用时：2297 ms, 在所有 Java 提交中击败了6.84%的用户
     * 内存：49 MB, 在所有 Java 提交中击败了32.35%的用户
     */
    public static int minSubArrayLen(int target, int[] nums) {
        int res = nums.length + 1, afterRes = 0, conditionIndex = 0, targetCopy = target;
        for (int i = 0; i < nums.length; i++) {
            targetCopy -= nums[i];
            afterRes++;
            // 条件不符合提前退出
            if (i == nums.length - 1 && targetCopy > 0 && res == nums.length + 1) {
                res = 0;
                break;
            }
            if (targetCopy <= 0) {
                // 找到一个结果之后继续找下一个
                res = Integer.min(res, afterRes);
                i = conditionIndex++;
                afterRes = 0;
                targetCopy = target;
            }
        }
        return res;
    }

    /**
     * 滑动窗口，三个问题
     * 窗口就是 满足其和 ≥ s 的长度最小的 连续 子数组。
     * 窗口的起始位置如何移动：如果当前窗口的值大于s了，窗口就要向前移动了（也就是该缩小了）。
     * 窗口的结束位置如何移动：窗口的结束位置就是遍历数组的指针，也就是for循环里的索引。
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：48.7 MB, 在所有 Java 提交中击败了63.75%的用户
     */
    public static int minSubArrayLen2(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        // sum 窗口数值之和, subLength 窗口长度. right 窗口结束位置， left 窗口起始位置
        int left = 0, subLength = 0, sum = 0, len = nums.length;
        for (int right = 0; right < len; right++) {
            sum += nums[right];
            // 这里使用while，每次更新
            while (sum >= target) {
                // 窗口起始位置 left 往前移动
                subLength = (right - left) + 1;
                result = Integer.min(result, subLength);
                sum -= nums[left++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
