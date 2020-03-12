package com.lanwq.sort;

/**
 * @ClassName Bubble
 * @Description TODO 冒泡排序，第一层循环只是比较出第一组数的相邻两个数的大小，并交换两个数
 * 然后依次循环, 每次循环结束之后可以少比一次最左边或者是最右边的数
 * @Author Administrator
 * @Date 2019/7/29  23:01
 * @Version 1.0
 */
public class Bubble {
    public static void main(String[] args) {
        int[] array = {12, 10, 20, 9, 15, 7};
        // 外层循环比较的次数
        for (int i = 0; i < array.length - 1; i++) {
            // 比较内存循环相邻的两个数
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j] + array[j + 1];
                    array[j] = tmp - array[j];
                    array[j + 1] = tmp - array[j + 1];
                }
            }
        }
        for (int a : array) {
            System.out.print(a + " ");
        }
    }
}
