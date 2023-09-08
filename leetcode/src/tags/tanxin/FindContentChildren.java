package tags.tanxin;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className FindContentChildren
 * @description 455 分发饼干
 * @createTime 2022-08-16  17:19
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 *
 * 对每个孩子 i，都有一个胃口值  g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；
 * 并且每块饼干 j，都有一个尺寸 s[j] 。
 * 如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
 * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 * 示例  1:
 * 输入: g = [1,2,3], s = [1,1]
 * 输出: 1 解释:你有三个孩子和两块小饼干，3 个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是 1，你只能让胃口值是 1 的孩子满足。所以你应该输出 1。
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
