package tags.tanxin;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className FindContentChildren
 * @description 455 分发饼干
 * @createTime 2022-08-16  17:19
 **/
public class FindContentChildren {
    public static void main(String[] args) {
        FindContentChildren obj = new FindContentChildren();
        int[] g = new int[]{1, 2, 3};
        int[] s = new int[]{1, 2};
        System.out.println(obj.findContentChildren(g, s));
    }

    public int findContentChildren(int[] g, int[] s) {
        // 大饼干喂饱大的孩子先，所以需要先排序
        Arrays.sort(g);
        Arrays.sort(s);
        // 饼干的索引
        int index = s.length - 1;
        int result = 0;
        // 从孩子的胃口值开始
        for (int i = g.length - 1; i >= 0; i--) {
            if (index >= 0 && s[index] >= g[i]) {
                index--;
                // 满足了一个孩子
                result++;
            }
        }
        return result;
    }
}
