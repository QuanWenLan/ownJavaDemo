package tags.array;

import java.util.Arrays;

/**
 * @program: javaDemo->RemoveDuplicate
 * @description: 26 删除有序数组中的重复项
 * @author: lanwenquan
 * @date: 2021-07-12 22:44
 */
public class RemoveDuplicate {
    public static void main(String[] args) {
        RemoveDuplicate removeDuplicate = new RemoveDuplicate();
        removeDuplicate.removeDuplicates(new int[] {0,0,1,1,1,2,2,3,3,4});
    }
    public int removeDuplicates(int[] nums) {
        // 使用双指针，没有想出来
        if(nums == null || nums.length == 1) {
            return nums.length;
        }
        int i = 0, j = 1;
        while (j < nums.length) {
            if(nums[i] == nums[j]) {
                j++;
            } else {
                i++;
                nums[i] = nums[j];
                j++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return i + 1;
    }
}
