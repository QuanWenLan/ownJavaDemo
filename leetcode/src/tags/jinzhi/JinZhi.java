package tags.jinzhi;

/**
 * @author Vin lan
 * @className 进制转换
 * @description
 * @createTime 2021-08-02  10:53
 **/
public class JinZhi {
    public static void main(String[] args) {
        JinZhi jinZhi = new JinZhi();
        jinZhi.binaryConvert(10);
    }

    public void binaryConvert(int number) {
        StringBuilder ans = new StringBuilder();
        while(number > 0) {
            ans.append(number % 2);
            number = number / 2;
        }
        ans.reverse();
        System.out.println(ans);
    }

    //168和171 是两个相对应的题目 https://leetcode-cn.com/problems/excel-sheet-column-title/
    /**
     对于一般性的进制转换题目，只需要不断地对columnNumber 进行 % 运算取得最后一位，
     然后对 columnNumber 进行 / 运算，将已经取得的位数去掉，直到 columnNumber 为 0 即可 */
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            columnNumber--;
            sb.append((char)(columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        sb.reverse();
        return sb.toString();
    }

    // https://leetcode-cn.com/problems/excel-sheet-column-number/
    /**
     标签：字符串遍历，进制转换
     初始化结果 ans = 0，遍历时将每个字母与 A 做减法，因为 A 表示 1，所以减法后需要每个数加 1，计算其代表的数值 num = 字母 - ‘A’ + 1
     因为有 26 个字母，所以相当于 26 进制，每 26 个数则向前进一位
     所以每遍历一位则ans = ans * 26 + num
     以 ZY 为例，Z 的值为 26，Y 的值为 25，则结果为 26 * 26 + 25=701
     以 ABC 为例，A 的值是1，B的值是2，C的值是3，则结果为：( ( (0*26 + 1) * 26 + 2) ) * 26 + 3
     */
    public int titleToNumber(String columnTitle) {
        char[] ch = columnTitle.toCharArray();
        int len = ch.length;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            ans = ans * 26 + (ch[i] - 'A' + 1);
        }
        return ans;
    }
}
