package tags.math;

/**
 * @program: javaDemo->GreatestCommonDivisor
 * @description: 求2个整数的最大公约数，要求尽量优化算法性能
 * @author: lanwenquan
 * @date: 2023-02-07 22:44
 */
public class GreatestCommonDivisor {
    public static void main(String[] args) {
        System.out.println(getGreatestCommonDivisor1(25, 5));
        System.out.println(getGreatestCommonDivisor1(100, 80));
        System.out.println(getGreatestCommonDivisor1(27, 14));

        System.out.println("=================");
        System.out.println(getGreatestCommonDivisor2(25, 5));
        System.out.println(getGreatestCommonDivisor2(100, 80));
        System.out.println(getGreatestCommonDivisor2(27, 14));
    }

    public static int getGreatestCommonDivisor1(int a, int b) {
        int big = Math.max(a, b);
        int small = Math.min(a, b);

        if (big % small == 0) {
            return small;
        }
        for (int i = small / 2; i > 1; i--) {
            if (small % i == 0 && big % i == 0) {
                return i;
            }
        }
        return 1;
    }

    /**
     * 辗转相除法、欧几里得算法
     * 一个定理：两个正整数a和b（ a>b） ， 它们的最大公约数等于a除以b的余数c和b之间的最大公约数。
     * 例如10和25， 25除以10商2余5， 那么10和25的最大公约数， 等同于10和5的最大公约数。
     * 首先， 计算出a除以b的余数c， 把问题转化成求b和c的最大公约数；
     * 然后计算出b除以c的余数d， 把问题转化成求c和d的最大公约数；
     * 再计算出c除以d的余数e， 把问题转化成求d和e的最大公约数
     * @param a
     * @param b
     * @return
     */
    public static int getGreatestCommonDivisor2(int a, int b) {
        int big = Math.max(a, b);
        int small = Math.min(a, b);
        if (big % small == 0) {
            return small;
        }
        int c = big % small;
        return getGreatestCommonDivisor2(c, b);
    }
}
