package tags.dynamicprogramming;

/**
 * @author Vin lan
 * @className ClimbStairs
 * @description 70. 爬楼梯
 * https://leetcode.cn/problems/climbing-stairs/
 * @createTime 2022-08-24  14:51
 **/
public class ClimbStairs {
    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * 分析：
     * 爬到第一层楼梯有一种方法，爬到二层楼梯有两种方法。
     *
     * 那么第一层楼梯再跨两步就到第三层 ，第二层楼梯再跨一步就到第三层。
     *
     * 所以到第三层楼梯的状态可以由第二层楼梯 和 到第一层楼梯状态推导出来，那么就可以想到动态规划了。
     * 步骤
     * 1 确定 dp 数组的定义
     * dp[i] 爬到第i层楼梯，有dp[i]种方法
     * 2 确定递推公式
     * dp[i] = dp[i-1]+dp[i-2]
     * 3 dp 数组初始化
     * dp[1] = 1;
     * dp[2]= 2;
     * 4 确定遍历的顺序
     * 遍历顺序一定是从前向后遍历的
     * 5 举例子
     */
    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int [] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(5));
    }
}
