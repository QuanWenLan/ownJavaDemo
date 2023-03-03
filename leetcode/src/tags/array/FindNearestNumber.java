package tags.array;

import java.util.Arrays;

/**
 * @program: javaDemo->FindNearestNumber
 * @description: 获得全排列的下一个数   字典序算法
 * @author: lanwenquan
 * @date: 2023-02-09 22:38
 */
public class FindNearestNumber {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 4};
        System.out.println("12344 之后的10个全排列整数");
        //打印12345 之后的10个全排列整数
        for (int i = 0; i < 10; i++) {
            numbers = findNearestNumber(numbers);
            assert numbers != null;
            outputNumbers(numbers);
        }
    }

    private static void outputNumbers(int[] numbers) {
        for (int number : numbers) {
            System.out.print(number);
        }
        System.out.println();
    }

    /**
     * 1. 从后向前查看逆序区域， 找到逆序区域的前一位， 也就是数字置换的边界。
     * 2. 让逆序区域的前一位和逆序区域中大于它的最小的数字交换位置。
     * 3. 把原来的逆序区域转为顺序状态。
     * 如 12354 最大是 54321，最小是 12345
     * 为了和原数接近， 我们需要尽量保持高位不变，变换低位，如何变换取决于当前的整数的逆序区域，12354的逆序区域则是 54.
     * 此时查看这两个数的最大和最小，已经是最大的组合则需要从倒数第三位开始改变。
     * 倒数第三位是3，从后面的逆序区域中找到大于3的最小数字，让其和3的位置进行互换，如 12354 -> 12453，
     * 至此，第三位已确定，此时最后2位仍然是逆序状态。需要把最后2位转变为顺序状态，以此保证第三位是4的前提下，后两位
     * 最小 12453->12435
     */
    public static int[] findNearestNumber(int[] numbers) {
        // 1. 从后向前查看逆序区域， 找到逆序区域的前一位， 也就是数字置换的边界。
        int index = findTransferPoint(numbers);
        if (index == 0) {
            return null;
        }
        //2. 让逆序区域的前一位和逆序区域中大于它的最小的数字交换位置
        int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
        exchangeHead(numbersCopy, index);
        //3.把原来的逆序区域转为顺序
        reverse(numbersCopy, index);
        return numbersCopy;
    }

    private static void reverse(int[] num, int index) {
        for (int i = index, j = num.length - 1; i < j; i++, j--) {
            int temp = num[i];
            num[i] = num[j];
            num[j] = temp;
        }
    }

    private static void exchangeHead(int[] numbersCopy, int index) {
        int head = numbersCopy[index - 1];
        for (int i = numbersCopy.length - 1; i > 0; i--) {
            if (head < numbersCopy[i]) {
                numbersCopy[index - 1] = numbersCopy[i];
                numbersCopy[i] = head;
                break;
            }
        }
    }

    private static int findTransferPoint(int[] numbers) {
        for (int i = numbers.length - 1; i > 0; i--) {
            if (numbers[i] > numbers[i - 1]) {
                return i;
            }
        }
        return 0;
    }
}
