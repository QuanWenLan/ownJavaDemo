package tags.tanxin;

/**
 * @author Vin lan
 * @className LemonadeChange
 * @description 860. 柠檬水找零
 * https://leetcode.cn/problems/lemonade-change/
 * @createTime 2022-08-19  16:32
 **/
public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        if (bills[0] > 5) {
            return false;
        }
        int five = 0, ten = 0, twenty = 0;
        for (int bill : bills) {
            // 情况一
            if (bill == 5) five++;
            // 情况二
            if (bill == 10) {
                if (five <= 0) {
                    return false;
                }
                ten++;
                five--;
            }
            // 情况三
            if (bill == 20) {
                // 优先消耗10美元，因为5美元的找零用处更大，能多留着就多留着
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                    twenty++; // 其实这行代码可以删了，因为记录20已经没有意义了，不会用20来找零
                } else if (five >= 3) {
                    five -= 3;
                    twenty++; // 同理，这行代码也可以删了
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
