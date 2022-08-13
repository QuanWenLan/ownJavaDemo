package tags.array;

import java.util.Arrays;

/**
 * @program: javaDemo->MergeArr
 * @description: 面试题 10.01. 合并排序的数组
 * 面试题 10.01. 合并排序的数组
 * @author: lanwenquan
 * @date: 2022-07-26 22:46
 */
public class MergeArr {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 0, 0, 0};
        int[] b = new int[]{2, 5, 6};
        MergeArr obj = new MergeArr();
        obj.merge(a, 3, b, 3);
        System.out.println(Arrays.toString(a));
    }

    public void merge(int[] A, int m, int[] B, int n) {
        System.arraycopy(B, 0, A, m, n);
        Arrays.sort(A);
    }
}
