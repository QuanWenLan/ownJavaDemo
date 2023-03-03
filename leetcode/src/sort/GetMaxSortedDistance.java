package sort;

/**
 * @program: javaDemo->GetMaxSortedDistance
 * @description: 获取一个无序数组排序后的相邻元素的最大差值
 * @author: lanwenquan
 * @date: 2023-02-08 22:19
 */
public class GetMaxSortedDistance {
    public static void main(String[] args) {
        System.out.println(getMaxSortedDistance1(new int[]{2, 6, 3, 4, 5, 13, 9}));
    }

    // 利用计数排序来实现
    public static int getMaxSortedDistance1(int[] array) {
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
        // array 数组对号入座
        int[] resArr = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            resArr[array[i] - min]++;
        }
        // 判断0最多连续出现的次数，计算最大相邻差
        int start = 0, distance = 0;
        for (int i = 0; i < resArr.length; i++) {
            if (resArr[i] == 0) {
                if (i != 0) {
                    start = i - 1;
                }
                int j = i;
                while (j < resArr.length) {
                    if (resArr[j] == 0) {
                        j++;
                    } else {
                        break;
                    }
                }

                if (j - start > distance) {
                    distance = j - start;
                }
            }
        }
        return distance;
    }
}
