package tags.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className SubsetsWithDup
 * @description 90. 子集 II
 * https://leetcode.cn/problems/subsets-ii/
 * @createTime 2022-08-12  17:32
 **/
public class SubsetsWithDup {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        boolean [] used = new boolean [nums.length];
        // 去重需要排序
        Arrays.sort(nums);
        backtracking(0, nums, used);
        return result;
    }

    /**
     * 一条路径的元素
     */
    private LinkedList<Integer> path = new LinkedList<>();
    /**
     * 所有结果
     */
    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    /**
     回溯：我们需要收集的是所有的节点，注意条件：数组中的元素 互不相同
     */
    public void backtracking(int startIndex, int [] nums, boolean [] used) {
        // 放到这里不会出现重复问题
        result.add(new ArrayList<>(path));
        // 终止条件
        if (startIndex >= nums.length) {
            return;
        }

        for(int i = startIndex; i < nums.length; i++) {
            // 处理节点
            // used[i - 1] == true，说明同一树枝 nums[i - 1]使用过
            // used[i - 1] == false，说明同一树层 nums[i - 1]使用过
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                // 有相同的跳过
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            backtracking(i + 1, nums, used);
            used[i] = false;
            path.removeLast();
        }
    }
}
