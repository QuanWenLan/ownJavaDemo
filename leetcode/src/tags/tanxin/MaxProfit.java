package tags.tanxin;

/**
 * @author Vin lan
 * @className MaxProfit
 * @description 122. 买卖股票的最佳时机 II
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 * @createTime 2022-08-17  11:25
 **/
public class MaxProfit {
    public static void main(String[] args) {
        MaxProfit obj = new MaxProfit();
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(obj.maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        // 需要将收益拆解成每天的收益: p[4]-p[1] = (p[4]-p[3])+(p[3]-p[2])+(p[2-p[1]])，所以收益会比股票少一天
        int[] benefitArr = new int[prices.length - 1];
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            int benefit = prices[i] - prices[i - 1];
            // 只收集正数
            if (benefit > 0) {
                result += benefit;
            }
        }
        return result;
    }
}
