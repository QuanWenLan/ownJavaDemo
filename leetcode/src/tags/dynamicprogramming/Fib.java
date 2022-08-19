package tags.dynamicprogramming;

/**
 * @author Vin lan
 * @className Fib
 * @description 509. 斐波那契数
 * https://leetcode.cn/problems/fibonacci-number/
 * @createTime 2022-08-19  17:29
 **/
public class Fib {
    public static void main(String[] args) {
        Fib obj = new Fib();
        System.out.println(obj.fib(5));
    }
    /**
     动态规划五部曲
     1 确定dp 数组以及下标的含义
     dp[i]的定义为：第i个数的斐波那契数值是dp[i]
     2 确定递推公式
     状态转移方程 dp[i] = dp[i - 1] + dp[i - 2];
     3 dp数组如何初始化
     题目也初始化好了，dp[0] = 0; dp[1] = 1;
     4 确定遍历顺序
     从递归公式dp[i] = dp[i - 1] + dp[i - 2];中可以看出，dp[i]是依赖 dp[i - 1] 和 dp[i - 2]，那么遍历的顺序一定是从前到后遍历的
     5 举例推到dp 数组
     按照这个递推公式dp[i] = dp[i - 1] + dp[i - 2]，我们来推导一下，当N为10的时候，dp数组应该是如下的数列：

     0 1 1 2 3 5 8 13 21 34 55
     */

    public int fib(int n) {
        if (n < 1) return n;
        // 1 确定dp数组
        int[] dp = new int [n + 1];
        // 3 初始化
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
