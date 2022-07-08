package tags.array;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Vin lan
 * @className TopKFrequent
 * @description 347. 前 K 个高频元素
 * https://leetcode.cn/problems/top-k-frequent-elements/
 * @createTime 2022-07-08  16:32
 **/
public class TopKFrequent {
    public static void main(String[] args) {
        TopKFrequent obj = new TopKFrequent();
        int[] nums = obj.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        System.out.println(Arrays.toString(nums));
        int[] nums2 = obj.topKFrequent(new int[]{-1, -1}, 1);
        System.out.println(Arrays.toString(nums2));
    }

    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            /*Integer integer = map.get(num);
            if (integer == null) {
                map.put(num, 1);
            } else {
                integer += 1;
                map.put(num, integer);
            }*/
             map.put(num, map.getOrDefault(num, 1) + 1);
        }

        return map.entrySet()
                .stream()
                .sorted((m1, m2) -> m2.getValue() - m1.getValue())
                .limit(k)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }
}
