package tags.array;

/**
 * @author Lan
 * @createTime 2024-01-22  09:55
 * 240 搜索二维矩阵 2
 * https://leetcode.cn/problems/search-a-2d-matrix-ii/description/
 * 剑指offer的 JZ4 二维数组中的查找 相同
 * 74. 搜索二维矩阵 也可以用同样的方法解决 searchMatrix
 **/
public class SearchMatrix {
    public static void main(String[] args) {
        // matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
        // true
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        // write code here
        boolean res = false;
        int row = 0, maxRow = matrix.length - 1;
        int column = matrix[0].length - 1;
        // 从行的最右边的数开始比较
        for (; row <= maxRow; row++) {
            for (; column >= 0; ) {
                int val = matrix[row][column];
                if (val == target) {
                    res = true;
                    break;
                } else if (val > target) {
                    // 如果最右边的数比要比较的数大的话，这个数的这一列的数都比目标数大，直接跳过这一列
                    column--;
                } else {
                    // 如果还小于的话，则可能是在下一行
                    break;
                }
            }
            if (res) {
                break;
            }
        }
        return res;
    }
    /**
     * LCR 121. 寻找目标值 - 二维数组
     * 0 <= n <= 1000
     * 0 <= m <= 1000
     */
    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        // plants 有0 0 的情况
        boolean res = false;
        int row = 0, maxRow = plants.length - 1;
        if (maxRow < 0) {
            return false;
        }
        int column = plants[0].length - 1;
        // 从行的最右边的数开始比较
        for (; row <= maxRow; row++) {
            for (; column >= 0; ) {
                int val = plants[row][column];
                if (val == target) {
                    res = true;
                    break;
                } else if (val > target) {
                    // 如果最右边的数比要比较的数大的话，这个数的这一列的数都比目标数大，直接跳过这一列
                    column--;
                } else {
                    // 如果还小于的话，则可能是在下一行
                    break;
                }
            }
            if (res) {
                break;
            }
        }
        return res;
    }
}
