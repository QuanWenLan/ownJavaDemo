package tags.tanxin;

/**
 * @author Vin lan
 * @className CanCompleteCircuit
 * @description 134. 加油站
 * https://leetcode.cn/problems/gas-station/
 * @createTime 2022-08-19  15:59
 **/
public class CanCompleteCircuit {

    public static void main(String[] args) {
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{1, 2, 3, 4, 5};
        CanCompleteCircuit obj = new CanCompleteCircuit();
        System.out.println(obj.canCompleteCircuit(gas, cost));
    }

    /**
     * 暴力解法
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        // 从第一个加油站出发，尝试走一圈
        while (i < n) {
            // 加的汽油和，消耗的汽油和
            int sumOfGas = 0, sumOfCost = 0;
            int cnt = 0;
            // 一圈
            while (cnt < n) {
                // 因为加油站是环形的
                int j = (i + cnt) % n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                // 如果这个站点发现油不够了
                if (sumOfCost > sumOfGas) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return i;
            } else {
                // 不行的话 从下一个站点开始 检查
                i = i + cnt + 1;
            }
        }
        return -1;
    }
}
