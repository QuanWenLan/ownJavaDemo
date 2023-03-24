package tags.backtracking;

import java.util.*;

/**
 * @author Vin lan
 * @className CombinationSum
 * @description 39. 组合总和
 * https://leetcode.cn/problems/combination-sum-ii/
 * 参考学习链接：https://www.programmercarl.com/0039.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8C.html
 * @createTime 2022-08-09  16:45
 **/

/**
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * <p>
 * 注意：解集不能包含重复的组合，candidates 有重复的数字（2个1）
 * candidates = [10,1,2,7,6,1,5], target = 8,输出
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 */
public class CombinationSum {
    public static void main(String[] args) {
        CombinationSum obj = new CombinationSum();
        int[] candidates = new int[]{4, 4, 2, 1, 4, 2, 2, 1, 3};
        System.out.println(obj.combinationSum2(candidates, 6));
//        int[] candidates2 = new int[]{1, 1, 2, 1};
//        System.out.println(obj.combinationSum2(candidates2, 3));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 先升序排序
        Arrays.sort(candidates);
        backTracking(0, candidates, target, new boolean[candidates.length]);
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
     * 此题还需要加一个bool型数组used，用来记录同一树枝上的元素是否使用过,要去重的是“同一树层上的使用过”，如果判断同一树层上元素（相同的元素）是否使用过了呢。
     * <p>
     * 如果candidates[i] == candidates[i - 1] 并且 used[i - 1] == false，
     * 就说明：前一个树枝，使用了candidates[i - 1]，也就是说同一树层使用过candidates[i - 1]
     */
    public void backTracking(int startIndex, int[] candidates, int target, boolean[] used) {
        // 2 终止条件
        if (sum > target) {
            return;
        }
        if (sum == target) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        int len = candidates.length;
        for (int i = startIndex; i < len; i++) {
            // 在同一树层，已经使用过了这个数
            // used[i - 1] == true，说明同一树枝candidates[i - 1]使用过
            // used[i - 1] == false，说明同一树层candidates[i - 1]使用过
            int ele = candidates[i];
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }
            // 这里可以进行剪枝处理,后面的不需要进行遍历循环了，直接退出当前这一层for循环
            if (sum + ele > target) {
                break;
            }
            // 符合条件才能赋值为 true，不能放在上面 break 的上面
            used[i] = true;
            sum += ele;
            // 处理节点
            path.add(ele);
            //每个节点仅能选择一次，所以从下一位开始
            backTracking(i + 1, candidates, target, used);
            used[i] = false;
            sum -= ele;
            path.removeLast();
        }
    }

    // 第二种解法
    List<List<Integer>> lists = new ArrayList<>();
    Deque<Integer> deque = new LinkedList<>();
    int sum2 = 0;

    public List<List<Integer>> combinationSum22(int[] candidates, int target) {
        //为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort(candidates);
        //加标志数组，用来辅助判断同层节点是否已经遍历
        boolean[] flag = new boolean[candidates.length];
        backTracking2(candidates, target, 0, flag);
        return lists;
    }

    public void backTracking2(int[] arr, int target, int index, boolean[] flag) {
        if (sum2 == target) {
            lists.add(new ArrayList(deque));
            return;
        }
        for (int i = index; i < arr.length && arr[i] + sum <= target; i++) {
            //出现重复节点，同层的第一个节点已经被访问过，所以直接跳过
            if (i > 0 && arr[i] == arr[i - 1] && !flag[i - 1]) {
                continue;
            }
            flag[i] = true;
            sum += arr[i];
            deque.push(arr[i]);
            //每个节点仅能选择一次，所以从下一位开始
            backTracking2(arr, target, i + 1, flag);
            int temp = deque.pop();
            flag[i] = false;
            sum -= temp;
        }
    }
}
