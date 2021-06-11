package easy;

/**
 * @author Vin lan
 * @className easy.BuySellStock
 * @description 121 买卖股票的最佳时机1  https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 * @createTime 2021-06-09  10:46
 **/
public class BuySellStock121 {

    public static void main(String[] args) {
        maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        maxProfit2(new int[]{7, 1, 5, 3, 6, 4});
    }

    public static int maxProfit(int[] prices) {
        if (prices.length == 0 || prices.length > Math.pow(10, 5)) {
            return 0;
        }
        int maxProfit = 0;
        for (int j = 0; j < prices.length; j++) {
            int buyPrice = prices[j];
            for (int i = j + 1; i < prices.length; i++) {
                int max = prices[i];
                if (max > buyPrice) {
                    maxProfit = Math.max(maxProfit, max - buyPrice);
                }
            }
        }
        return maxProfit;
    }

    /**
     * 考虑每次如何获取最大收益？第i天的最大收益只需要知道前i天的最低点就可以算出来了。
     * 而第i天以前（包括第i天）的最低点和i-1天的最低点有关，至此我们的动态方程就出来了
     * dp[i] = min(dp[i-1], dp[i])
     * 其中dp[0]=prices[0],然后动态计算之后的就可以了。 得到了前i天的最低点以后，只需要维护一个max用来保存最大收益就可以了
     * dp[i]表示截止到i，价格的最低点是多少   dp[i]=min(dp[i-1],nums[i])
     *
     */
    public static int maxProfit2(int[] prices) {
        if (prices.length == 0 || prices.length > Math.pow(10, 5)) {
            return 0;
        }
        int max = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            max = Math.max(prices[i] - min, max);
        }
        return max;
    }

}
