package com.lanwq.thinkinginjavademo.initializationcleanup;

/**
 * @author Vin lan
 * @className DimensionsArray
 * @description TODO 多维数组
 * @createTime 2021-01-06  10:41
 * <a href="https://www.cnblogs.com/ECJTUACM-873284962/p/7351805.html">https://www.cnblogs.com/ECJTUACM-873284962/p/7351805.html</a>
 **/
public class DimensionsArray {
    public static void main(String[] args) {
        System.out.println("一维数组");
        int[] a = {1, 2, 3};
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        System.out.println("二维数组");
        int[][] b = {{1, 2, 3}, {4, 5, 6, 0}, {7}};
        System.out.println(b.length);
        System.out.println(b[1].length);
        /*for (int i = 0; i < b.length; i++) {

        }*/
        System.out.println("三维数组");
    }
}
