package tags.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vin lan
 * @className ThreeSum
 * @description 15 三数之和
 * https://leetcode.cn/problems/3sum/
 * @createTime 2023-03-20  14:18
 **/
public class ThreeSum {

    /**
     * //给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k
     * // ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     * //
     * // 你返回所有和为 0 且不重复的三元组。
     * //
     * // 注意：答案中不可以包含重复的三元组。
     *
     * //输入：nums = [-1,0,1,2,-1,-4]
     * //输出：[[-1,-1,2],[-1,0,1]]
     * //解释：
     * //nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
     * //nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
     * //nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
     * //不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
     * //注意，输出的顺序和三元组的顺序并不重要。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();
        Arrays.sort(nums);

        // 遍历
        int len = nums.length;
        // 只遍历到倒数第三个
        for(int i = 0; i < len - 2; i++) {
            // 如果排完序之后，第一个还是大于 0，直接返回。
            if (nums[i] > 0) {
                return resList;
            }

            // 去除重复的. 如排序后为 [-4, -1, -1, 0, 1, 1]，则当遍历到第一个 -1 时有 [-1, 0, 1]，第二个 -1 时也有 [-1, 0, 1]
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            // 遍历剩下的数据，使用 双指针
            // 这里有个问题，如果是[0, 0, 0] 的话
            int l = i + 1;
            int r = len - 1;
            while (l < r) {
                int zeroConfirm = nums[i] + nums[l] + nums[r];
                if (zeroConfirm < 0) {
                    l++;
                } else if (zeroConfirm > 0) {
                    r--;
                } else {
                    // 添加记录
                    ArrayList list = new ArrayList();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    resList.add(list);
                    // 还有都是 0 的情况 [0, 0, 0, 0, 0], 结果会保存出 [[0,0,0],[0,0,0]]，所以要去除重复的
                    while(l < r && nums[l] == nums[l + 1]) l++;
                    while(l < r && nums[r] == nums[r - 1]) r--;
                    // 找到正确答案，同时收缩双指针
                    l++;
                    r--;
                }
            }
        }
        return resList;
    }
}
