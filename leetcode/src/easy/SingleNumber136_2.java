package easy;

import java.util.HashMap;

/**
 * @author Vin lan
 * @className easy.SingleNumber136
 * @description 136 只出现一次的数字2  https://leetcode-cn.com/problems/single-number-ii/
 * @createTime 2021-06-09  15:05
 **/
public class SingleNumber136_2 {
    public static void main(String[] args) {
        System.out.println(singleNumber1(new int[]{0, 1, 0, 1, 0, 1, 99}));
    }

    public static int singleNumber1(int[] nums) {
        int length = nums.length;
        int number = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(length);
        for (int i = 0; i < length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) {
                number = key;
                break;
            }
        }
        return number;
    }
}
