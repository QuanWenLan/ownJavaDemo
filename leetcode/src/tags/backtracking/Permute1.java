package tags.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className Permute1
 * @description 46. 全排列
 * https://leetcode.cn/problems/permutations/
 * @createTime 2022-08-16  16:02
 **/
public class Permute1 {
    public static void main(String[] args) {
        int [] nums = new int []{1,2,3};
        Permute1 obj = new Permute1();
        List<List<Integer>> permute = obj.permute(nums);
        System.out.println(permute);
    }
    /**
     * 存放结果集
     */
    private LinkedList<Integer> path;
    private List<List<Integer>> result;

    public List<List<Integer>> permute(int[] nums) {
        path = new LinkedList<>();
        result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
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
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            backtracking(nums, used);
            // 回溯
            used[i] = false;
            path.removeLast();
        }
    }
}
