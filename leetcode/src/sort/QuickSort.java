package sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author Vin lan
 * @className QuickSort
 * @description 快速排序
 * @createTime 2022-02-16  14:21
 **/
public class QuickSort {
    /**
     * 同冒泡排序一样，快速排序也属于交换排序，通过元素之间的比较
     * 和交换位置来达到排序的目的。
     * 冒泡排序在每一轮中只把1个元素冒泡到数列的一端，而快速排序则在每一轮挑选一个基准元素，并让其他比它大的元素移 动到数列一边，比它小的元素移动到数列的另一边，从而把数列拆解 成两个部分。
     * 然后再重复的将已经分好的左右两边继续这样操作。
     * 如何选择基准元素呢？pivot。直接选择首元素作为基准元素。
     * 实现分成左右两边，2中方法
     * （1）双边循环，定义left和right指针。从right指针开始，让指针所指向的元素和
     * 基准元素做比较。如果大于或等于pivot，则指针向左移动；如果小于 pivot，则right指针停止移动，切换到left指针。轮到left指针行动，让指针所指向的元素和基准元素做比较。如果
     * 小于或等于pivot，则指针向右移动；如果大于pivot，则left指针停止移动。交换这两个元素，最后把 pivot 元素与重合点的元素交换，这一回合结束
     */
    public static void main(String[] args) {
        int[] nums = {4, 7, 3, 5, 6, 2, 8, 1};
        System.out.println("开始：" + Arrays.toString(nums));
//        quickSort(nums, 0, nums.length - 1);
        quickSort2(nums, 0, nums.length - 1);
        System.out.println("结果：" + Arrays.toString(nums));

    }

    private static void quickSort(int[] nums, int startIndex, int endIndex) {
        // 递归结束条件
        if (startIndex >= endIndex) {
            return;
        }
        int pivotIndex = partition(nums, startIndex, endIndex);
        System.out.println(Arrays.toString(nums));
        quickSort(nums, startIndex, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, endIndex);
    }

    /**
     * 双边循环
     *
     * @param nums       待交换的数组
     * @param startIndex 开始索引
     * @param endIndex   结束索引
     * @return
     */
    public static int partition(int[] nums, int startIndex, int endIndex) {
        int pivot = nums[startIndex];
        int left = startIndex;
        int right = endIndex;
        // 从右边开始
        while (left != right) {
            // 控制right指针左移，从右边找到比基准元素小的
            while (left < right && nums[right] > pivot) {
                right--;
            }
            // 控制 left指针右移，从左边找到比基准元素大的
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            // 交换left和right指针的元素
            if (left < right) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
            }
        }
        // pivot 和 left和right指针的重合点位置元素交换
        nums[startIndex] = nums[left];
        nums[left] = pivot;
        return left;
    }

    /**
     * 方法二，使用单边循环
     *
     * @param nums
     * @param startIndex
     * @param endIndex
     */
    private static void quickSort2(int[] nums, int startIndex, int endIndex) {
        // 递归结束条件
        if (startIndex >= endIndex) {
            return;
        }
        int pivotIndex = partition2(nums, startIndex, endIndex);
        System.out.println("基准元素：" + pivotIndex + "排序一次后：" + Arrays.toString(nums));
        quickSort2(nums, startIndex, pivotIndex - 1);
        quickSort2(nums, pivotIndex + 1, endIndex);
    }

    /**
     * 单边循环
     *
     * @param nums       待交换的数组 {4, 7, 3, 5, 6, 2, 8, 1}
     * @param startIndex 开始索引
     * @param endIndex   结束索引
     */
    public static int partition2(int[] nums, int startIndex, int endIndex) {
        int pivot = nums[startIndex];
        int mark = startIndex;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (nums[i] < pivot) {
                // 小于则意味着比基准元素小的区域多了1个范围
                mark++;
                int swapEle = nums[mark];
                nums[mark] = nums[i];
                nums[i] = swapEle;
            }
        }
        // 交换最后mark位置与基准位置
        nums[startIndex] = nums[mark];
        nums[mark] = pivot;
        return mark;
    }

    /**
     * 非递归方法
     *
     * @param nums       待交换的数组 {4, 7, 3, 5, 6, 2, 8, 1}
     * @param startIndex 开始索引
     * @param endIndex   结束索引
     */
    public static void quickSort3(int[] nums, int startIndex, int endIndex) {
        // 用一个集合栈来代替递归的函数栈
        Stack<Map<String, Integer>> quickSortStack = new Stack<Map<String, Integer>>();
        // 整个数列的起止下标，以哈希的形式入栈
        Map rootParam = new HashMap();
        rootParam.put("startIndex", startIndex);
        rootParam.put("endIndex", endIndex);
        quickSortStack.push(rootParam);
        // 循环结束条件：栈为空时
        while (!quickSortStack.isEmpty()) {
            // 栈顶元素出栈，得到起止下标
            Map<String, Integer> param = quickSortStack.pop();
            // 得到基准元素位置
            int pivotIndex = partition2(nums, param.get("startIndex"), param.get("endIndex"));
            // 根据基准元素分成两部分, 把每一部分的起止下标入栈
            if (param.get("startIndex") < pivotIndex - 1) {
                Map<String, Integer> leftParam = new HashMap<String, Integer>();
                leftParam.put("startIndex", param.get("startIndex"));
                leftParam.put("endIndex", pivotIndex - 1);
                quickSortStack.push(leftParam);
            }
            if (pivotIndex + 1 < param.get("endIndex")) {
                Map<String, Integer> rightParam = new HashMap<String, Integer>();
                rightParam.put("startIndex", pivotIndex + 1);
                rightParam.put("endIndex", param.get("endIndex"));
                quickSortStack.push(rightParam);
            }
        }
    }
}
