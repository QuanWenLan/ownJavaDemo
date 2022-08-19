package tags.tanxin;

/**
 * @author Vin lan
 * @className Jump
 * @description 45. 跳跃游戏 II
 * https://leetcode.cn/problems/jump-game-ii/
 * @createTime 2022-08-17  17:31
 **/
public class Jump {
    public static void main(String[] args) {
        Jump obj = new Jump();
//        int[] nums = new int[]{2, 3, 1, 1, 4};
        int[] nums = new int[]{2, 3, 0, 1, 4, 3};
        System.out.println(obj.jump(nums));
    }

    public int jump(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        // 一步可以覆盖的范围，在这个范围内如果可以到的话必然可以到
        // 要从覆盖范围出发，不管怎么跳，覆盖范围内一定是可以跳到的，以最小的步数增加覆盖范围，覆盖范围一旦覆盖了终点，得到的就是最小步数,这里需要统计两个覆盖范围，当前这一步的最大覆盖和下一步最大覆盖。
        int ans = 0;
        // 当前覆盖最远距离下标，下一步覆盖最远距离下标
        int curDistance = 0, nextDistance = 0;
        for (int i = 0; i < nums.length; i++) {
            nextDistance = Math.max(nums[i] + i, nextDistance);  // 更新下一步覆盖最远距离下标
            if (i == curDistance) {                         // 遇到当前覆盖最远距离下标
                if (curDistance != nums.length - 1) {       // 如果当前覆盖最远距离下标不是终点
                    ans++;                                  // 需要走下一步
                    curDistance = nextDistance;             // 更新当前覆盖最远距离下标（相当于加油了）
                    if (nextDistance >= nums.length - 1) {
                        break; // 下一步的覆盖范围已经可以达到终点，结束循环
                    }
                } else {
                    break;                               // 当前覆盖最远距离下标是集合终点，不用做ans++操作了，直接结束
                }
            }
        }
        return ans;
    }
}
