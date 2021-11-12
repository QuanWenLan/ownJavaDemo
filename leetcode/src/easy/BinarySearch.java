package easy;

/**
 * @author Vin lan
 * @className BinarySearch
 * @description 二分查找
 * @createTime 2021-11-08  16:40
 **/
public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        System.out.println(binarySearch.search(nums, 9));
        int len = nums.length, startIndex = 0, endIndex = len - 1;
        System.out.println(binarySearch.search2(nums, 9, startIndex, endIndex));
    }

    /**
     * 循环方式
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            if (target == nums[0]) {
                return 0;
            } else {
                return -1;
            }
        }
        int len = nums.length, startIndex = 0, endIndex = len - 1, mid = (startIndex + endIndex) / 2;
        while (startIndex <= endIndex) {
            if (target < nums[mid]) {
                endIndex = mid - 1;
            } else if (target > nums[mid]) {
                startIndex = mid + 1;
            } else if (target == nums[mid]) {
                return mid;
            }
            mid = (startIndex + endIndex) / 2;
        }
        return -1;
    }

    /**
     * if 方式
     */
    public int search2(int[] nums, int target, int left, int right) {
        // 结束条件，一开始少写了这个，导致一直运行
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (target < nums[mid]) {
            // 递归体
            return search2(nums, target, left, mid - 1);
        } else if (target > nums[mid]) {
            return search2(nums, target, mid + 1, right);
        } else {
            return mid;
        }
    }
}
