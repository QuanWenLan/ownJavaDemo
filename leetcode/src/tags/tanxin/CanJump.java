package tags.tanxin;

/**
 * @author Vin lan
 * @className CanJump
 * @description 55. 跳跃游戏
 * https://leetcode.cn/problems/jump-game/
 * @createTime 2022-08-17  12:10
 **/
public class CanJump {
    public static void main(String[] args) {
        CanJump obj = new CanJump();
//        int[] nums = new int[]{2, 1, 5, 3, 6, 4};
        int[] nums = new int[]{3, 2, 1, 0, 4};
        System.out.println(obj.canJump(nums));
    }

    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        // 一步一步跳必然可以跳到最后一步，有0的话，如果前面的步数可以跳过0也是可以到最后一步的
        // 一步可以覆盖的范围，在这个范围内如果可以到的话必然可以到
        int cover = 0;
        for (int i = 0; i <= cover; i++) {
            int step = nums[i];
            cover = Math.max(cover, i + step);
            // 假设可以一次跳到最后的下标
            if (i + step >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}
