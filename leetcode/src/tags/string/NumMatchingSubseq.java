package tags.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lan
 * @createTime 2024-02-21  15:51
 * https://leetcode.cn/problems/number-of-matching-subsequences/
 * 792. 匹配子序列的单词数
 * 给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。
 * 字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
 * 例如， “ace” 是 “abcde” 的子序列。
 **/
public class NumMatchingSubseq {
    public static void main(String[] args) {
        NumMatchingSubseq obj = new NumMatchingSubseq();
//        System.out.println(obj.numMatchingSubseq2("abcde", new String[]{"a", "bb", "acd", "ace"}));
        System.out.println(obj.numMatchingSubseq2("dsahjpjauf", new String[]{"ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"}));
    }

    /**
     * 不可避免的是，我们要对每个 words[i]  进行检查，因此优化的思路可放在如何优化单个 words[i] 的判定操作。
     * 朴素的判定过程需要使用双指针扫描两个字符串，其中对于原串的扫描，会有大量的字符会被跳过（无效匹配），即只有两指针对应的字符相同时，匹配串指针才会后移。
     * 对于任意一个 w=words[i] 而言，假设我们当前匹配到 w[j] 位置，此时我们已经明确下一个待匹配的字符为 w[j+1]，因此我们可以直接在 s 中字符为 w[j+1]  的位置中找候选。
     * 具体的，我们可以使用哈希表 map 对 s 进行预处理：以字符 c=s[i] 为哈希表的 key，对应的下标 i 集合为 value，由于我们从前往后处理 s 进行预处理，因此对于所有的 value 均满足递增性质。
     * 举个例子: 对于 s = abcabc 而言，预处理的哈希表为 {a=[0,3], b=[1,4], c=[2,5]}
     * 最后考虑如何判定某个 w=words[i] 是否满足要求：待匹配字符串 w 长度为 m，我们从前往后对 w 进行判定，假设当前判断匹配位置为 w[i]，我们使用变量 idx 代表能够满足匹配 w[0:i] 的最小下标（贪心思路）。
     * 对于匹配的 w[i] 字符，可以等价为在 map[w[i]] 中找到第一个大于 idx 的下标，含义在原串 s 中找到字符为 w[i] 且下标大于 idx 的最小值，由于我们所有的 map[X] 均满足单调递增，该过程可使用「二分」进行。
     *
     * @param s
     * @param words
     * @return
     */
    public int numMatchingSubseq2(String s, String[] words) {
        int n = s.length(), ans = 0;
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(s.charAt(i), new ArrayList<>());
            list.add(i);
            map.put(s.charAt(i), list);
        }
        for (String w : words) {
            boolean ok = true;
            int m = w.length(), idx = -1;
            for (int i = 0; i < m && ok; i++) {
                List<Integer> list = map.getOrDefault(w.charAt(i), new ArrayList<>());
                int l = 0, r = list.size() - 1;
                while (l < r) {
                    int mid = l + r >> 1;
                    if (list.get(mid) > idx) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                }
                if (r < 0 || list.get(r) <= idx) {
                    ok = false;
                } else {
                    idx = list.get(r);
                }
            }
            if (ok) {
                ans++;
            }
        }
        return ans;
    }


    // 这样直接调用会超时
    public int numMatchingSubseq(String s, String[] words) {
        int len = words.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            boolean is = isSubsequence(words[i], s);
            if (is) {
                res++;
            }
        }
        return res;
    }

    /**
     * s: 子序列，t：字符串，判断 s 是不是 t 的子序列
     */
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        // fast 指向 t 的初始位置，slow 指向 s 的初始位置
        int fast = 0, slow = 0;
        while (fast < m && slow < n) {
            if (t.charAt(fast) == s.charAt(slow)) {
                slow++;
            }
            fast++;
        }
        // 最后如果slow移动到了末尾则说明s是t的子序列
        return n == slow;
    }
}
