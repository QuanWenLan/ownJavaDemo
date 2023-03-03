package tags.math;

/**
 * @program: javaDemo->pow2
 * @description: 判断是否是2的整数倍
 * @author: lanwenquan
 * @date: 2023-02-01 23:13
 */
public class pow2 {
    public static void main(String[] args) {
        System.out.println(isPow2(9));
        System.out.println(isPow2(8));
    }

    public static boolean isPow2(int value) {
        // 例如8 二进制 1000
        // 减一 则是    0111，做 & 操作，会是0
        int v = (value - 1) & value;
        return v == 0;
    }
}
