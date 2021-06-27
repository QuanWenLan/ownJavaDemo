package jizhioffer.learn;

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
        System.out.println(obj.hammingWeight(0B00000000000000000000000000001011));
        System.out.println(obj.hammingWeight(0B000000000000000000000000000010111));
    }

    /**
     * n & n-1 最终会变成0，相邻的只相差1，计数即可
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int i = 0;
        while (n != 0) {
           n = n & (n-1);
           i++;
        }
        return i;
    }
}
