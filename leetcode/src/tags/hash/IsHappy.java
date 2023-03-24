package tags.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Vin lan
 * @className IsHappy
 * @description 202.快乐数
 * @createTime 2023-03-20  14:40
 **/
public class IsHappy {
    public static void main(String[] args) {
        IsHappy obj = new IsHappy();
        System.out.println(obj.isHappy(19));
        System.out.println(obj.isHappy(2));
    }
    /**
     * 题目中说了会 无限循环，那么也就是说求和的过程中，sum会重复出现，这对解题很重要！
     * 当我们遇到了要快速判断一个元素是否出现集合里的时候，就要考虑哈希法了。
     */
    public boolean isHappy(int n) {
        Set<Integer> record = new HashSet<>();
        while (n != 1 && !record.contains(n)) {
            record.add(n);
            n = getNextNumber(n);
        }
        return n == 1;
    }

    /**
     * 取整数上的各个位
     * @param n
     * @return 返回各个位数上的平方和
     */
    private int getNextNumber(int n) {
        int res = 0;
        while (n > 0) {
            // 取个位
            int temp = n % 10;
            res += temp * temp;
            // 取10位，无限循环最终取到最高位后为0
            n = n / 10;
        }
        return res;
    }
}
