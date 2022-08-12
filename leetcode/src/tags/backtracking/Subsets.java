package tags.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className Subsets
 * @description 78. 子集
 * https://leetcode.cn/problems/subsets/
 * @createTime 2022-08-12  17:08
 **/
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        backtracking(0, nums);
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
    public void backtracking(int startIndex, int [] nums) {
        // 放到这里不会出现重复问题
        result.add(new ArrayList<>(path));
        // 终止条件
        if (startIndex >= nums.length) {
            return;
        }

        for(int i = startIndex; i < nums.length; i++) {
            // 处理节点
            // nums[i] 是一个子集
            path.add(nums[i]);
            // 不能到这个位置把 nums[i] 加入到 result 中，这样的话会有重复的单节点被加进去，比如， 3 和 最后一次for循环遍历的3
            backtracking(i + 1, nums);
            path.removeLast();
        }
    }
}
