package tags.array;

import java.util.Arrays;

/**
 * @program: javaDemo->MissingNumber
 * @description: 丢失的数字 268
 * https://leetcode.cn/problems/missing-number/
 * @author: lanwenquan
 * @date: 2022-07-30 17:04
 */
public class MissingNumber {
    public static void main(String[] args) {
        MissingNumber obj = new MissingNumber();
        System.out.println(obj.missingNumber(new int[]{8, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    public int missingNumber(int[] nums) {
        // 先将数组排序，升序
        Arrays.sort(nums);
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            int val = nums[i];
            if (val != i) {
                return i;
            }
        }
        // 如果是最后一个还没找到则说明是丢失做了最后一个
        return n;
    }

    /**
     * 我们先把索引补一位，然后让每个元素和自己相等的索引相对应
     * 这样做了之后，就可以发现除了缺失元素之外，所有的索引和元素都组成一对儿了，现在如果把这个落单的索引 2 找出来，也就找到了缺失的那个元素。
     * 如何找？只要把所有的元素和索引做异或运算，成对儿的数字都会消为 0，只有这个落单的元素会剩下。
     * @param nums
     * @return
     */
    public int missingNumber2(int[] nums) {
        int n = nums.length;
        int res = 0;
        // 先和新补的索引异或一下
        res ^= n;
        // 和其他的元素、索引做异或
        for (int i = 0; i < n; i++)
            res ^= i ^ nums[i];
        return res;
    }

// 详细解析参见：
// https://labuladong.github.io/article/?qno=268

}
