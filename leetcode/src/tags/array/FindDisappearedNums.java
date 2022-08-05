package tags.array;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vin lan
 * @className FindDisappearedNums
 * @description
 * @createTime 2022-08-01  16:06
 **/
public class FindDisappearedNums {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("-9");
        System.out.println(bigDecimal.toString());
        int[] nums = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        FindDisappearedNums obj = new FindDisappearedNums();
        System.out.println(obj.findDisappearedNumbers(nums));
    }

    /**
     * n 是数组长度
     * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
     * 请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
     * 解析：
     * 由于 nums 的数字范围均在 [1,n] 中，我们可以利用这一范围之外的数字，来表达「是否存在」的含义。
     *
     * 具体来说，遍历 nums，每遇到一个数 x，就让 nums[x−1] 增加 n。
     * 由于 nums 中所有数均在 [1,n] 中，增加以后，这些数必然大于 n。
     * 最后我们遍历 nums，若 nums[i] 未大于 n，就说明没有遇到过数 i+1。这样我们就找到了缺失的数字。
     *
     * 注意，当我们遍历到某个位置时，其中的数可能已经被增加过，因此需要对 n 取模来还原出它本来的值
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // 抄袭一下官方题解
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}
