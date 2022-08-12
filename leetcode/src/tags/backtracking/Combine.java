package tags.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Vin lan
 * @className Combine
 * @description
 * @createTime 2022-08-08  11:56
 **/
public class Combine {
    public static void main(String[] args) {
        Combine obj = new Combine();
        List<List<Integer>> list = obj.combine(4, 4);
        System.out.println(list);
    }

    public List<List<Integer>> combine(int n, int k) {
        backTrack(1, k, n);
        return result;
    }

    /**
     * 一个结果就是一条N叉树到节点的路径
     */
    private LinkedList<Integer> path = new LinkedList<>();
    /**
     * 所有的结果集
     */
    private List<List<Integer>> result = new ArrayList<>();

    /**
     * 回溯：不需要返回值，入参需要确定
     * 1 先通过回溯的逻辑来确定需要哪些参数
     * 1.1 需要确定遍历多深，也就是k的大小，所以需要一个参数 k
     * 1.2 需要从哪个下标开始遍历后面的数，所以需要一个参数 startIndex
     * 1.3 一次终止后就是一个结果，所以需要一个List<Integer>
     * 1.4 最后需要将所有的结果都保存起来，需要一个 List<List<Integer>>
     */
    public void backTrack(int startIndex, int k, int n) {
        // 2 终止条件，说明遇到了符合条件的只，需要将结果加入到集合中（这里需要一个参数，存放所有的数组集合）
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 单层逻辑
        // 3 for 遍历所有的数,n 是 1，n，所以不用通过取下标
//        for (int i = startIndex; i <= n; i++) {
        // 可以进行有优化
        int atLeastNum = n - (k - path.size()) + 1;
        for (int i = startIndex; i <= atLeastNum; i++) {
            // 3.1 处理节点
            path.add(i);
            System.out.println("递归之前 => " + path);
            // 3.2
            backTrack(i + 1, k, n);
            // 3.3 回退处理这个节点
            path.removeLast();
            System.out.println("递归之后 => " + path);
        }
    }
}
