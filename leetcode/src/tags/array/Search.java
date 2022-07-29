package tags.array;

/**
 * @author Vin lan
 * @className Search
 * @description 剑指 Offer 53 - I. 在排序数组中查找数字 I
 * @createTime 2022-07-27  16:53
 **/
public class Search {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        Search obj = new Search();
        System.out.println(obj.search(nums, 8));
    }

    public int search(int[] nums, int target) {
        int midIndex = -1;
        // 使用二分查找，需要时排好序的数组才可以使用
        int left = 0, right = nums.length - 1;
        // 这样的写法可以防止 left + right 溢出int的范围
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midVal = nums[mid];
            // 在中间部分的右边,left 往右移动到 mid 右边的一个位置
            if (target > midVal) {
                left = mid + 1;
            } else if (target < midVal) {
                // right 移动到 mid 左边的一个位置
                right = mid - 1;
            } else {
                // 相等的情况下
                midIndex = mid;
                break;
            }
        }
        if (midIndex == -1) {
            return 0;
        }
        // midIndex 往左找到不符合的位置
        int leftIndex = midIndex;
        while (leftIndex - 1 >= 0 && nums[leftIndex - 1] == target) {
            leftIndex--;
        }
        // midIndex 往左找到不符合的位置
        int rightIndex = midIndex;
        while (rightIndex + 1 < nums.length && nums[rightIndex + 1] == target) {
            rightIndex++;
        }
        return rightIndex - leftIndex + 1;
    }
}
/**
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
