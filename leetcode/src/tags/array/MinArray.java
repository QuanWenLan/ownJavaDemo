package tags.array;

/**
 * @program: javaDemo->MinArray
 * @description: 剑指 Offer 11. 旋转数组的最小数字
 * https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 * 和 https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/ 相同
 * @author: lanwenquan
 * @date: 2022-07-26 22:36
 */
public class MinArray {
    public static void main(String[] args) {
        System.out.println(minArray(new int[]{3, 4, 5, 5, 5, 1, 2}));
    }

    public static int minArray(int[] numbers) {
        // 需要找到旋转的那个点，也就是升序排列错误的时候
        int firstIndex = -1;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                firstIndex = i + 1;
                break;
            }
        }
        // 没有则说明是升序排序的
        if (firstIndex == -1) {
            firstIndex = 0;
        }
        return numbers[firstIndex];
    }
}
