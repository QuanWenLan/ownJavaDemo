package tags.array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @program: javaDemo->NextGraterElement
 * @description: 496 下一个更大元素1
 * @author: lanwenquan
 * @date: 2022-07-30 17:27
 */
public class NextGraterElement {
    public static void main(String[] args) {
        NextGraterElement obj = new NextGraterElement();
        int[] nums1 = new int[]{4, 1, 2};
        int[] nums2 = new int[]{1, 3, 4, 2};
        System.out.println(Arrays.toString(obj.nextGreaterElement(nums1, nums2)));
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        // 可以先将 nums2 遍历放到 map 中，数组是没有重复元素的
        HashMap<Integer, Integer> num2Map = new HashMap<>(nums2.length);
        for (int i = 0; i < nums2.length; i++) {
            num2Map.put(nums2[i], i);
        }
        // 需要先遍历nums1
        for (int i = 0; i < nums1.length; i++) {
            int number1 = nums1[i];
            // 找到 i 在 nums2 中的值
            int number2Index = num2Map.get(number1);
            number2Index++;

            ans[i] = -1;
            for (; number2Index < nums2.length; number2Index++) {
                if (nums2[number2Index] > number1) {
                    ans[i] = nums2[number2Index];
                    break;
                }
            }
        }
        return ans;
    }
}
