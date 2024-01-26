package tags.other;

/**
 * @author Vin lan
 * @className HammingWeight
 * @description https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/ 二进制中1的个数，长度小于32
 * @createTime 2021-06-23  16:36
 **/
public class HammingWeight {
    // https://developer.aliyun.com/article/771042
    public static void main(String[] args) {
        HammingWeight obj = new HammingWeight();
//        System.out.println(obj.hammingWeight(0B00000000000000000000000000001011));
//        System.out.println(obj.hammingWeight(0B000000000000000000000000000010111));
        System.out.println(obj.hammingWeight2(10));
    }

    /**
     * n & n-1 最终会变成0，相邻的只相差1，计数即可
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int i = 0;
        while (n != 0) {
            n = n & (n - 1);
            i++;
        }
        return i;
    }

    /**
     * 我们可以检查该数字的二进制每一位是否为1，如果遍历二进制每一位呢？可以考虑移位运算，每次移动一位就可以。
     * 我们都知道数字1与数字相位与运算，其实只是最后一位为1就是1，最后一位为0就是0，这样我们只需要将数字1移位运算，就可以遍历二进制的每一位，再去做位与运算，结果为1的就是二进制中为1的。
     * 10 -> 0000 0000 0000 0000 0000 0000 0000 1010
     *
     * @param n
     * @return
     */
    public int hammingWeight2(int n) {
        int res = 0;
        //遍历32位
        for (int i = 0; i < 32; i++) {
            //按位比较
            if ((n & (1 << i)) != 0)
                res++;
        }
        return res;
    }
}
