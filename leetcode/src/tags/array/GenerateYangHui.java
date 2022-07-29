package tags.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vin lan
 * @className GenerateYangHui
 * @description
 * @createTime 2022-07-26  12:15
 **/
public class GenerateYangHui {
    public static void main(String[] args) {
        GenerateYangHui obj = new GenerateYangHui();
//        System.out.println(obj.generate(5));
        System.out.println(obj.getRow(2));
    }
    public List<List<Integer>> generate(int numRows) {
        // 外层循环
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            ArrayList<Integer> inList = new ArrayList<>();
            // 内层循环
            for (int j = 0; j < i; j++) {
                // 首 和 尾 都为 1
                if (j == 0 || j == i - 1) {
                    inList.add(1);
                } else {
                    // 需要找到上一层的第一个和第二个的索引
                    int firstIndex = j - 1;
                    List<Integer> preList = list.get(i - 2);
                    int sum = preList.get(firstIndex) + preList.get(j);
                    inList.add(sum);
                }
            }
            list.add(inList);
        }
        return list;
    }

    /**
     * https://leetcode.cn/problems/pascals-triangle-ii/
     * 119. 杨辉三角 II
     * @param rowIndex 0<= rowIndex <= 33
     * @return 返回「杨辉三角」的第 rowIndex 行
     */
    public List<Integer> getRow(int rowIndex) {
        // 外层循环
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            ArrayList<Integer> inList = new ArrayList<>();
            // 内层循环
            for (int j = 0; j <= i; j++) {
                // 首 和 尾 都为 1
                if (j == 0 || j == i) {
                    inList.add(1);
                } else {
                    // 需要找到上一层的第一个和第二个的索引
                    int firstIndex = j - 1;
                    List<Integer> preList = list.get(i - 1);
                    int sum = preList.get(firstIndex) + preList.get(j);
                    inList.add(sum);
                }
            }
            list.add(inList);
        }
        return list.get(rowIndex);
    }
}
