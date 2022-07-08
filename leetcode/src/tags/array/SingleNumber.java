package tags.array;

import java.util.HashMap;

/**
 * @author Vin lan
 * @className easy.SingleNumber136
 * @description 136 只出现一次的数字
 * https://leetcode-cn.com/problems/single-number/
 * @createTime 2021-06-09  15:05
 **/
public class SingleNumber {
    public static void main(String[] args) {
        System.out.println(singleNumber1(new int[]{4, 1, 2, 1, 2}));
        System.out.println(singleNumber2(new int[]{4, 1, 2, 1, 2}));
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

    public static int singleNumber2(int[] nums) {
        int length = nums.length;
        int number = 0;
        HashMap<Integer, Integer> map = new HashMap<>(length);
        for (int i = 0; i < length; i++) {
            if (map.containsKey(nums[i])) {
                map.remove(nums[i]);
            } else {
                map.put(nums[i], 1);
            }
        }
        for (Integer key : map.keySet()) {
            number = key;
        }
        return number;
    }

    /**
     * 使用位运算解决
     * a^a=0；自己和自己异或等于0
     *
     * a^0=a；任何数字和0异或还等于他自己
     *
     * a^b^c=a^c^b；异或运算具有交换律
     */
    public int singleNumber3(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++)
            result = result ^ nums[i];
        return result;
    }
}
