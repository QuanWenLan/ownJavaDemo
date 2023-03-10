package tags.array.slideWindow;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Vin lan
 * @className ContainsNearbyDuplicate
 * @description 219 存在重复元素2
 * @createTime 2023-03-10  15:36
 **/
//给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i-j) <= k 。如果存在，返回 true ；否则，返回 false 。
// 示例 1：
//输入：nums = [1,2,3,1], k = 3
//输出：true
//
// 示例 2：
//输入：nums = [1,0,1,1], k = 1
//输出：true
//
// 1 <= nums.length <= 105
// -109 <= nums[i] <= 109
// 0 <= k <= 105
public class ContainsNearbyDuplicate {
    public static void main(String[] args) {
//        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2));
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        int left = 0, right = 0, len = nums.length;
        // 考虑数组nums的每个长度不超过k+1的滑动窗口，同一个滑动窗口中的任意两个下标差的绝对值不超过k
        // ，则说明当结束下标是i时，开始下表则是max(0,i-k)，使用哈希集合存储滑动窗口的元素
        // 遍历结束，如果所有滑动窗口中都没重复元素返回false
        Set<Integer> set = new HashSet<Integer>();
        for (; right < len; right++) {
            int rightVal = nums[right];
            // 注意，i 和 j 是不同的
            if (right > k) {
                left = right - k - 1;
                int leftVal = nums[left];
                set.remove(leftVal);
            }
            // 不包含返回true
            boolean add = set.add(rightVal);
            if (!add) {
                return true;
            }
        }
        return false;
    }

    /**
     * 维护一个哈希表，里面始终最多包含 k 个元素，当出现重复值时则说明在 k 距离内存在重复元素
     * 每次遍历一个元素则将其加入哈希表中，如果哈希表的大小大于 k，则移除最前面的数字
     * 时间复杂度：O(n)
     */
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
            if(set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

}
