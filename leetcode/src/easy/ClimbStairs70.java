package easy;

/**
 * @author Vin lan
 * @className ClimbStairs70
 * @description  https://leetcode-cn.com/problems/climbing-stairs/
 * @createTime 2021-06-17  10:06
 **/
public class ClimbStairs70 {
    public static void main(String[] args) {
        ClimbStairs70 obj = new ClimbStairs70();
        System.out.println(obj.climbStairs(5));
    }

    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }

        // 先创建一个数组来保存历史数据
        int[] dp = new int[n + 1];
        // 给出初始值
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        // 通过关系式来计算出 dp[n]
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        // 把最终结果返回
        return dp[n];
    }

    /**
     * 一样的题目  https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
     * @param n
     * @return
     */
    public int numWays(int n) {
        if(n <= 1) {
            return 1;
        }

        // 先创建一个数组来保存历史数据
        int[] dp = new int[n+1];
        // 给出初始值
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        // 通过关系式来计算出 dp[n]
        for(int i = 3; i <= n; i++){
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
        }
        // 把最终结果返回
        return dp[n];
    }
}
/**
 * PS : 为什么要模1000000007（跟我念，一，八个零，七）。参考https://www.liuchuo.net/archives/645
 *
 * 大数相乘，大数的排列组合等为什么要取模
 * 1000000007是一个质数（素数），对质数取余能最大程度避免结果冲突/重复
 * int32位的最大值为2147483647，所以对于int32位来说1000000007足够大。
 * int64位的最大值为2^63-1，用最大值模1000000007的结果求平方，不会在int64中溢出。
 * 所以在大数相乘问题中，因为(a∗b)%c=((a%c)∗(b%c))%c，所以相乘时两边都对1000000007取模，再保存在int64里面不会溢出。
 * 这道题为什么要取模，取模前后的值不就变了吗？
 * 确实：取模前 f(43) = 701408733, f(44) = 1134903170, f(45) = 1836311903, 但是 f(46) > 2147483647结果就溢出了。
 *
 * _____，取模后 f(43) = 701408733, f(44) = 134903163 , f(45) = 836311896, f(46) = 971215059没有溢出。
 *
 * 取模之后能够计算更多的情况，如 f(46)
 *
 * 这道题的测试答案与取模后的结果一致。
 *
 * 总结一下，这道题要模1000000007的根本原因是标准答案模了1000000007。不过大数情况下为了防止溢出，模1000000007是通用做法，原因见第一点。
 */
