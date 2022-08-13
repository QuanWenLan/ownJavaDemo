package tags.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: javaDemo->MajorityElement
 * @description: 169 多数元素
 * @author: lanwenquan
 * https://leetcode.cn/problems/majority-element/
 * @date: 2022-07-26 21:53
 */
public class MajorityElement {
    // 统计个数之后获取最大的
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if (!counts.containsKey(nums[i])) {
                counts.put(nums[i], 1);
            } else {
                counts.put(nums[i], counts.get(nums[i]) + 1);
            }
        }

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }

        return majorityEntry.getKey();
    }
}
