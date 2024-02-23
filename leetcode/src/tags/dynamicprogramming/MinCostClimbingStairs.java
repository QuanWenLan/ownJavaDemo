package tags.dynamicprogramming;

/**
 * @author Lan
 * @createTime 2024-02-22  11:27
 **/
public class MinCostClimbingStairs {
    public static void main(String[] args) {
        MinCostClimbingStairs obj = new MinCostClimbingStairs();
        System.out.println(obj.minCostClimbingStairs(new int[]{10, 15, 20}));
        System.out.println(obj.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }

    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len + 1];

        // 从下标为 0 或下标为 1 的台阶开始，因此支付费用为0
        dp[0] = 0;
        dp[1] = 0;

        // 计算到达每一层台阶的最小费用
        for (int i = 2; i <= len; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return dp[len];
    }
    /**
     * 1 确定dp数组以及下标的含义
     * 使用动态规划，就要有一个数组来记录状态，本题只需要一个一维数组dp[i]就可以了。
     * dp[i]的定义：到达第i台阶所花费的最少体力为dp[i]。
     * 对于dp数组的定义，大家一定要清晰！
     *
     * 2 确定递推公式
     * 可以有两个途径得到dp[i]，一个是dp[i-1] 一个是dp[i-2]。
     *
     * dp[i - 1] 跳到 dp[i] 需要花费 dp[i - 1] + cost[i - 1]。
     *
     * dp[i - 2] 跳到 dp[i] 需要花费 dp[i - 2] + cost[i - 2]。
     *
     * 那么究竟是选从dp[i - 1]跳还是从dp[i - 2]跳呢？
     *
     * 一定是选最小的，所以dp[i] = min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
     *
     * 3 dp数组如何初始化
     * 看一下递归公式，dp[i]由dp[i - 1]，dp[i - 2]推出，既然初始化所有的dp[i]是不可能的，那么只初始化dp[0]和dp[1]就够了，其他的最终都是dp[0]dp[1]推出。
     *
     * 那么 dp[0] 应该是多少呢？ 根据dp数组的定义，到达第0台阶所花费的最小体力为dp[0]，那么有同学可能想，那dp[0] 应该是 cost[0]，例如 cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1] 的话，dp[0] 就是 cost[0] 应该是1。
     *
     * 这里就要说明本题力扣为什么改题意，而且修改题意之后 就清晰很多的原因了。
     *
     * 新题目描述中明确说了 “你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。” 也就是说 到达 第 0 个台阶是不花费的，但从 第0 个台阶 往上跳的话，需要花费 cost[0]。
     *
     * 所以初始化 dp[0] = 0，dp[1] = 0;
     *
     * 4 确定遍历顺序
     * 最后一步，递归公式有了，初始化有了，如何遍历呢？
     *
     * 本题的遍历顺序其实比较简单，简单到很多同学都忽略了思考这一步直接就把代码写出来了。
     *
     * 因为是模拟台阶，而且dp[i]由dp[i-1]dp[i-2]推出，所以是从前到后遍历cost数组就可以了。
     *
     * 5 举例推导dp数组
     * minCostClimbingStairs.png
     */
}
