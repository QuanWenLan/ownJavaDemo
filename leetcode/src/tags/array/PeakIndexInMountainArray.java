package tags.array;

/**
 * @author Vin lan
 * @className PeakIndexInMountainArray852
 * @description 山脉数组的峰顶索引 852
 * https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/
 * @createTime 2021-06-15  14:23
 **/
public class PeakIndexInMountainArray {
    public static void main(String[] args) {
        PeakIndexInMountainArray obj = new PeakIndexInMountainArray();
        obj.peakIndexInMountainArray2(new int[]{24, 69, 70, 99, 100, 99, 79, 78, 67, 36, 26, 19});
    }

    /**
     * 题目保证了是一个山脉数组，那就只需要找到最高的点，最高的点和其右侧的点相比较是大于的，那么只需要比较这个就能找到了
     *
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        int index = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * 利用二分查找
     * 记满足题目要求的下标 i 为 i(ans) 可以发现
     * 当 i < i(ans)时，arr[i] < arr[i+1]
     * 当 i >= i(ans)时，arr[i] > arr[i+1] 恒成立
     * 这与方法一的遍历过程也是一致的，因此 i(ans)即为【最小满足 arr[i] > arr[i+1]的下标 i 】
     * 时间复杂度：O(log n)，其中 n 是数组arr 的长度。我们需要进行二分查找的次数为O(log n)。
     * 空间复杂度：O(1)
     *
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray2(int[] arr) {
        int n = arr.length;
        int left = 1, right = n - 2, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            // 如果 arr[mid] > arr[mid + 1] 则说明峰顶在左边
            if (arr[mid] > arr[mid + 1]) {
                ans = mid;
                right = mid - 1;
            } else { // 说明峰顶在右边
                left = mid + 1;
            }
        }
        return ans;
    }
}
