package sort;

import java.util.Arrays;

/**
 * @program: javaDemo->CountSort
 * @description: 计数排序
 * @author: lanwenquan
 * @date: 2023-01-17 21:21
 */
public class CountSort {
    public static void main(String[] args) {
        int[] array = new int[]{4, 4, 6, 5, 3, 2, 8, 1, 7, 5, 6, 0, 10};
        int[] sortedArray = countSort(array);
        System.out.println(Arrays.toString(sortedArray));
        // 优化后的
        int[] array2 = new int[]{95, 94, 91, 98, 99, 90, 99, 93, 91, 92};
        int[] sortedArray2 = countSort2(array2);
        System.out.println(Arrays.toString(sortedArray2));

    }

    public static int[] countSort(int[] array) {
        // 1 计算数组最大值
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        // 2 构建一个数组，整数是从0开始, 保证数组的最后一个下标是max
        int[] countArray = new int[max + 1];
        // 3 遍历数列，填充数组
        for (int i = 0; i < array.length; i++) {
            countArray[array[i]]++;
        }
        // 4 遍历统计数组，输出结果
        int index = 0;
        int[] sortedArray = new int[array.length];
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                sortedArray[index++] = i;
            }
        }
        return sortedArray;
    }

    /**
     * 优化
     *
     * @param array
     * @return
     */
    public static int[] countSort2(int[] array) {
        // 1 计算数组最大值和最小值，算出差值d
        int max = array[0];
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int d = max - min;
        int[] countArray = new int[d + 1];
        // 2 构建一个数组，长度为 d + 1
        for (int i = 0; i < array.length; i++) {
            countArray[array[i] - min]++;
        }
        // 3 遍历统计数组做变形处理，后面的元素（从第二个元素开始，下标为1）等于前面的元素之和。
        // 相加的目的是让统计数组存储的元素值，等于相应整数的最终排序位置的序号。
        // countArray = 1,2,1,1,1,1,0,0,1,2
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }
        // countArray = 1,3,4,5,6,7,7,7,8,10
        // 4 倒序遍历原始数列，从统计数组找到正确位置，输出到结果数组
        // 如最后一位成绩是92，找到countArray位置是92-90=2，值为4，所以排到第4位，再有92的话则会排到第3位，
        // 存储到sortedArray中时下标是2-1=1，
        int[] sortedArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            int n = array[i] - min;
            sortedArray[countArray[n] - 1] = array[i];
            countArray[n]--;
        }
        return sortedArray;
    }
}
