package tags.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className Permute2
 * @description 47. 全排列2
 * https://leetcode.cn/problems/permutations-ii/
 * @createTime 2022-08-16  16:02
 **/
public class Permute2 {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        Permute2 obj = new Permute2();
        List<List<Integer>> permute = obj.permuteUnique(nums);
        System.out.println(permute);
    }

    /**
     * 存放结果集
     */
    private LinkedList<Integer> path;
    private List<List<Integer>> result;

    /**
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     * 和全排列不同的是有重复的，所以我们需要进行去重操作，去重需要将数组进行排序，才能通过相邻的数判断重复
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        path = new LinkedList<>();
        result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backtracking(nums, used);
        return result;
    }

    public void backtracking(int[] nums, boolean[] used) {
        // 全排列终止条件是和 nums 一样长
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
        }
        // startIndex 不能从下一位开始了，需要从0开始
        for (int i = 0; i < nums.length; i++) {
            // 元素使用过
            // used[i - 1] == true，说明同一树枝nums[i - 1]使用过
            // used[i - 1] == false，说明同一树层nums[i - 1]使用过
            // 如果同一树层nums[i - 1]使用过则直接跳过
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            //如果同⼀树⽀nums[i]没使⽤过开始处理
            if (!used[i]) {
                //标记同⼀树⽀nums[i]使⽤过，防止同一树枝重复使用
                used[i] = true;
                path.add(nums[i]);
                backtracking(nums, used);
                // 回溯
                used[i] = false;
                path.removeLast();
            }
        }
    }
}
