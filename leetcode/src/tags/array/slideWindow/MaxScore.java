package tags.array.slideWindow;

/**
 * @author Lan
 * @createTime 2024-02-07  16:23
 * 1423. 可获得的最大点数
 * https://leetcode.cn/problems/maximum-points-you-can-obtain-from-cards/
 **/
public class MaxScore {
    public static void main(String[] args) {
        MaxScore obj = new MaxScore();
//        int[] cardPoints1 = new int[]{1, 2, 3, 4, 5, 6, 1}; // 12
//        System.out.println("obj.maxScore(cardPoints1, 3) = " + obj.maxScore(cardPoints1, 3));
//        int[] cardPoints2 = new int[]{2, 2, 2}; // 4
//        System.out.println("obj.maxScore(cardPoints2, 2) = " + obj.maxScore(cardPoints2, 2));
//        int[] cardPoints3 = new int[]{9, 7, 7, 9, 7, 7, 9}; // 55
//        System.out.println("obj.maxScore(cardPoints3, 7) = " + obj.maxScore(cardPoints3, 7));
//        int[] cardPoints4 = new int[]{1, 1000, 1}; // 1
//        System.out.println("obj.maxScore(cardPoints4, 1) = " + obj.maxScore(cardPoints4, 1));
//        int[] cardPoints5 = new int[]{1, 79, 80, 1, 1, 1, 200, 1}; // 202
//        System.out.println("obj.maxScore(cardPoints5, 3) = " + obj.maxScore(cardPoints5, 3));
        int[] cardPoints6 = new int[]{11, 49, 100, 20, 86, 29, 72}; //
        System.out.println("obj.maxScore(cardPoints6, 3) = " + obj.maxScore(cardPoints6, 4));
    }

    /**
     * 记数组 cardPoints 的长度为 nnn，由于只能从开头和末尾拿 k 张卡牌，所以最后剩下的必然是连续的 n−k 张卡牌。
     * 我们可以通过求出剩余卡牌点数之和的最小值，来求出拿走卡牌点数之和的最大值。
     */
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int total = 0;

        for (int cardPoint : cardPoints) {
            total += cardPoint;
        }
        // 选前 n-k 个作为初始值
        int windowSize = n - k;
        int sum = 0;
        for (int i = 0; i < windowSize; ++i) {
            sum += cardPoints[i];
        }
        int minSum = sum;
        for (int i = windowSize; i < n; ++i) {
            // 滑动窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            sum = sum + cardPoints[i] - cardPoints[i - windowSize];
            minSum = Math.min(minSum, sum);
        }
        // 最大分数 = 总分数 - 滑动窗口的最小值
        return total - minSum;
    }

    /**
     * 双指针有问题，写不了，留着当个纪念吧，以为能解出来的，后面看什么时候再删了
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore2(int[] cardPoints, int k) {
        int len = cardPoints.length;
        int result = 0;
        if (len == k) {
            return getAll(cardPoints, 0, len);
        }

        int leftIndex = 0, rightIndex = len - 1;
        while (leftIndex <= rightIndex && k > 0) {
            // 获取左边和右边的值
            int leftVal = cardPoints[leftIndex];
            int rightVal = cardPoints[rightIndex];
            if (leftVal < rightVal) { // 取右边的值
                rightIndex--;
                result += rightVal;
            } else if (leftVal > rightVal) { // 取左边的值
                leftIndex++;
                result += leftVal;
            } else { // 两个值相同，比较他们前面一个和后面一个数，找到大的那个值，如果还有相同的继续找到大的值
                int l = leftIndex, r = rightIndex;
                boolean moveLeft = false, moveRight = false;
                while (l <= r) {
                    if (cardPoints[l] == cardPoints[r]) {
                        l++;
                        r--;
                    } else if (cardPoints[l] > cardPoints[r]) { // 左边的大，这里的意思是如果比较到不相同的时候，
                        // 代表着需要leftIndex往右走会有更大的值。
                        l++;
                        moveLeft = true;
                        break;
                    } else { // 这里也是同理
                        r--;
                        moveRight = true;
                        break;
                    }
                }
                if (moveLeft) {
                    leftIndex++;
                    result += leftVal;
                }
                if (moveRight) {
                    rightIndex--;
                    result += rightVal;
                }
                if (!moveLeft && !moveRight) { // 如果都相同
                    leftIndex++;
                    result += leftVal;
                }
            }
            k--;
        }
        return result;
    }

    public int getAll(int[] cardPoints, int start, int end) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            sum = sum + cardPoints[i];
        }
        return sum;
    }
}
