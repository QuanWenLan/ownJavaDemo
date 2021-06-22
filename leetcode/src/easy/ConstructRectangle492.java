package easy;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className ConstructRectangle492
 * @description https://leetcode-cn.com/problems/construct-the-rectangle/
 * @createTime 2021-06-21  15:39
 **/
public class ConstructRectangle492 {
    public static void main(String[] args) {
        ConstructRectangle492 obj = new ConstructRectangle492();
        System.out.println(Arrays.toString(obj.constructRectangle(32)));
        System.out.println(Arrays.toString(obj.constructRectangle(322221)));
    }

    /**
     * 好蠢一个我，直接裂开
     * @param area
     * @return
     */
    public int[] constructRectangle(int area) {
        int length, width = 2;
        int bestLength = area, bestWidth = 1;
        while (width <= area) {
            if ((area % width == 0) && (length = area / width) >= width) {
                System.out.println("width:" + width);
                System.out.println("length:" + length);
                if (length - width < bestLength - bestWidth) {
                    bestLength = length;
                    bestWidth = width;
                }
            } else if ((area % width == 0) && (length = area / width) < width) {
                break;
            }
            width++;
        }
        return new int[]{bestLength, bestWidth};
    }
}
