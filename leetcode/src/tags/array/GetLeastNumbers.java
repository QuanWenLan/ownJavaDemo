package tags.array;

import java.util.Arrays;

/**
 * @program: javaDemo->GetLeastNumbers
 * @description: 剑指 offer 40 最小的k个数
 * @author: lanwenquan
 * @date: 2022-07-26 22:19
 */
public class GetLeastNumbers {
    public int[] getLeastNumbers(int[] arr, int k) {
        // 升序排序取前 k 个
        Arrays.sort(arr);
        int [] res = new int [k];
        System.arraycopy(arr, 0, res, 0, k);
        return res;
    }
}
