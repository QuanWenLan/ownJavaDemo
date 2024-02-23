package tags.dynamicprogramming;

/**
 * @author Lan
 * @createTime 2024-02-23  09:37
 * 不同路径
 **/
public class UniquePaths {
    /**
     * 1 定义dp数组
     * dp[i][j]: 到该位置有多少条路径
     * 2 递推dp方程 (i 横坐标，j：纵坐标)
     * 假如在其中的一个位置(i,j)，在这个位置有多少条路径，因为这个位置
     * 只能从上面往下走，也就是上一个 dp[i-1][j] 有多少条路径推导来
     * 或者是从左往右走，也就是左一个 dp[i][j-1] 有多少条路径推导来
     * 所以dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * 3 初始化
     * 机器人只能往右或者是往下，走第一行只有一种路径，走第一列也只有一种路径
     * 所以 dp[0][j] = 1; dp[i][0] = 1;
     * 4 遍历方式
     * 从左往右，或者是从上往下遍历，因为需要由上面一个以及左边一个推导出后一个
     * 5 打印
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // 初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        // 打印
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
