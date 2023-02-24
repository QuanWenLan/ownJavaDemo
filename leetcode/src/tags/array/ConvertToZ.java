package tags.array;

/**
 * @author Vin lan
 * @className ConvertToZ
 * @description https://leetcode.cn/problems/zigzag-conversion/ N字形变换
 * @createTime 2023-02-24  14:27
 **/
public class ConvertToZ {
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        // 定于一个二维数组来存储排好的字母
        String[][] res = new String[numRows][s.length()];
        // 先构造从上往下的 行数
        int len = s.length(), i = 0, rowIndex = 0, colIndex = 0;
        for (; i < len; i++) {
            String v = String.valueOf(s.charAt(i));
            if (rowIndex < numRows) {
                // 填充竖的
                res[rowIndex++][colIndex] = v;
            } else {
                // 填充横的
                int row2 = --rowIndex;
                int j = i;
                while (--row2 != 0  && j < len) {
                    colIndex++;
                    res[row2][colIndex] = String.valueOf(s.charAt(j));
                    j++;
                }
                if (j != len) {
                    i = --j;
                } else {
                    break;
                }
                rowIndex = 0;
                colIndex++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < res.length; j++) {
            for (int k = 0; k < res[j].length; k++) {
                if (res[j][k] != null) {
                    sb.append(res[j][k]);
                }
            }
        }
        return sb.toString();
    }
}
