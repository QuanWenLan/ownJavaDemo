package tags.hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Vin lan
 * @className Intersection
 * @description 349.两个数组的交集
 * @createTime 2023-03-20  14:36
 **/
public class Intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        // 暴力枚举,用两个 set，过滤掉数组中各自重复的数据，然后再遍历一个 set1，判断set2中是否有set1中的元素，有的话加入结果中。
        int len1 = nums1.length;
        int len2 = nums2.length;

        List<Integer> resultList = new ArrayList();
        HashSet<Integer> set1 = new HashSet();
        for (int i = 0; i < len1; i++) {
            set1.add(nums1[i]);
        }

        HashSet<Integer> set2 = new HashSet();
        for (int i = 0; i < len2; i++) {
            set2.add(nums2[i]);
        }

        for (int l : set1) {
            if (set2.contains(l)) {
                resultList.add(l);
            }
        }

        int[] arrR = new int[resultList.size()];
        int start = 0;
        for (int item : resultList) {
            arrR[start++] = item;
        }
        return arrR;
    }
}
