package tags.hash;

import java.util.HashMap;

/**
 * @author Vin lan
 * @className fourSumCount
 * @description 454.四数相加2
 * @createTime 2023-03-20  14:53
 **/
public class FourSumCount {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap();

        for (int a : nums1) {
            for (int b : nums2) {
                int sum = a + b;
                // key：a+b的和，value: a+b 和出现的次数
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int count = 0;
        for (int c : nums3) {
            for (int d : nums4) {
                if (map.containsKey(0 - (c + d))) {
                    count += map.get(-c - d);
                }
            }
        }
        return count;
    }
}
