package tags.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className CombinationSum2
 * @description 40. 组合总和 II
 * https://leetcode.cn/problems/combination-sum/
 * 参考学习链接：https://www.programmercarl.com/0039.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8C.html
 * @createTime 2022-08-09  16:45
 **/
public class CombinationSum2 {
    public static void main(String[] args) {
        CombinationSum2 obj = new CombinationSum2();
        int[] candidates = new int[]{2, 3, 5, 7};
        System.out.println(obj.combinationSum(candidates, 7));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 先升序排序
        Arrays.sort(candidates);
        backTracking(0, candidates, target);
        return result;
    }

    /**
     * 一条路径的元素
     */
    private LinkedList<Integer> path = new LinkedList<>();
    /**
     * 一条路径的结果
     */
    private int sum;
    /**
     * 所有结果
     */
    private List<List<Integer>> result = new ArrayList<List<Integer>>();

    /**
     * 回溯方法
     * target:目标和
     * startIndex:结束位置
     */
    public void backTracking(int startIndex, int[] candidates, int target) {
        // 2 终止条件，这里没有保证需要几个数字，只保证和相同和至少一个数字的被选数量不同即可(这一点如何保证呢？)
        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new ArrayList(path));
            return;
        }
        int len = candidates.length;
        // 这里如果 i=0 开始的话则会有重复的组合出现，这一点如何保证呢？保证就在这里，正常按照遍历从0开始，如图片：combinationsum.jpg
        for (int i = startIndex; i < len; i++) {
            int ele = candidates[i];
            // 这里可以进行剪枝处理,后面的不需要进行遍历循环了，直接退出当前这一层for循环
            if (sum + ele > target) {
                break;
            }
            sum += ele;
            // 处理节点
            path.add(ele);
            backTracking(i, candidates, target);
            sum -= ele;
            path.removeLast();
        }
    }
}
