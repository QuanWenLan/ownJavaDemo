package tags.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className CombinationSum3
 * @description 216 组合总和3
 * @createTime 2022-08-08  17:02
 **/
public class CombinationSum3 {

    public static void main(String[] args) {
        CombinationSum3 obj = new CombinationSum3();
        List<List<Integer>> lists = obj.combinationSum3(3, 7);
        System.out.println(lists);
    }

    /**
     * 一个结果就是一条N叉树到节点的路径
     */
    private LinkedList<Integer> path = new LinkedList<>();
    private int sum = 0;
    /**
     * 所有的结果集
     */
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtrack(1, k, n);
        return result;
    }

    /**
     * startIndex：开始点
     * k：遍历几个数
     * target：k 个数的和
     */
    public void backtrack(int startIndex, int k, int target) {
        // 2 终止条件
        if (sum > target) {
            return;
        }
        if (path.size() == k) {
            if (sum == target) {
                result.add(new ArrayList<>(path));
                System.out.println("添加了一个结果" + result);
            }
            return;
        }

        // 单层逻辑，数字是 1-9
        for (int i = startIndex; i <= 9; i++) {
            // 处理节点
            sum += i;
            path.add(i);
            System.out.println("递归之前 => " + path + ", sum =>" + sum);
            backtrack(i + 1, k, target);
            // 回溯节点，处理了一个之后，需要回调到上一个，所以需要删掉
            sum -= i;
            path.removeLast();
            System.out.println("递归之后 => " + path + ", sum =>" + sum);
        }
    }
}
