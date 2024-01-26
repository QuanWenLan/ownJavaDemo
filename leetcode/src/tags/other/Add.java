package tags.other;

/**
 * @author Lan
 * @createTime 2024-01-24  12:06
 * JZ65 不用加减乘除做加法
 **/
public class Add {
    public static void main(String[] args) {
        int num1 = 5;
        int num2 = 7;
        System.out.println(new Add().add(num1, num2));
    }

    public int add(int num1, int num2) {
        // 异或 操作产生的是不进位的加法， 相同取0，不相同取1
        // 与 匀速按结果提供两数相加后的二进制进位信息
        // 0101 -5
        // 0111 -7
        /**
         step 1：两数进行与运算可以产生进位的信息
         step 2：运算后执行左移1位就是每轮需要进位的方案
         step 3：两数进行异或运算可以产生非进位的加和结果
         step 4：将移位后的进位结果与非进位结果继续重复 step 1 - step 3 的步骤，直到不再产生进位为止
         */
        // add表示进位值
        int add = num2;
        // sum表示总和
        int sum = num1;
        // 当不再有进位的时候终止循环
        while (add != 0) {
            // 将每轮的无进位和与进位值做异或求和
            int temp = sum ^ add;
            // 进位值是用与运算产生的
            add = (sum & add) << 1;

            // 更新sum为新的和
            sum = temp;
        }
        return sum;
    }
    /**
     * 不使用新的变量，交换两个变量的值。比如有两个变量 a、b，我们希望交换它们的值。有两种不同的办法：
     * 1 a = a + b;b = a - b;a = a - b;
     * 2 a = a ^ b;b = a ^ b;a = a ^ b;
     * 异或 具体的一些博客：https://www.cnblogs.com/JhSonD/p/6374397.html
     * 1、交换律
     * 2、结合律（即(a^b)^c == a^(b^c)）
     * 3、对于任何数x，都有x^x=0，x^0=x
     * 4、自反性 A XOR B XOR B = A xor  0 = A
     */
}
