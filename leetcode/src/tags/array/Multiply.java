package tags.array;

/**
 * @author Lan
 * @createTime 2024-01-24  09:47
 * JZ66 构建乘积数组
 * 给定一个数组 A[0,1,...,n-1] ,请构建一个数组 B[0,1,...,n-1] ,其中 B 的元素 B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]（除 A[i] 以外的全部元素的的乘积）。程序中不能使用除法。（注意：规定 B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2]）
 * 对于 A 长度为 1 的情况，B 无意义，故而无法构建，用例中不包括这种情况。
 **/
public class Multiply {

    // 我的解法：暴力
    public int[] multiply (int[] A) {
        // write code here
        int[] b = new int[A.length];

        for (int i = 0; i <= A.length - 1; i++) {
            int res = 1;
            if (i == 0) {
                res = getMultiply(A, 1, A.length - 1, i);
            } else if (i == A.length - 1) {
                res = getMultiply(A, 0, A.length - 2, i);
            } else {
                res = getMultiply(A, 0, A.length - 1, i);
            }
            b[i] = res;
        }
        return b;
    }

    public int getMultiply(int[] A, int start, int end, int index) {
        int res = 1;
        for (; start <= end; start++) {
            if (start == index) {
                continue;
            }
            res *= A[start];
        }
        return res;
    }

    // 官方解法

    /**
     * https://www.nowcoder.com/share/jump/5391693681706061171368
     * multiply.jpg 参考图示
     * 矩阵中由对角线1将其分成了上三角和下三角。我们先看下三角，如果我们累乘的时候，B[1]是在B[0]的基础上乘了新增的一个A[0]，
     * B[2]是在B[1]的基础上乘了新增的一个A[1]，那我们可以遍历数组的过程中不断将数组B的前一个数与数组A的前一个数相乘就得到了下三角中数组B的当前数。
     * 同理啊，我们在上三角中，用一个变量存储从右到左的累乘，每次只会多乘上一个数字。这样，两次遍历就可以解决。
     *
     *
     * 具体做法：
     * step 1：初始化数组B，第一个元素为1.
     * step 2：从左到右遍历数组A，将数组B的前一个数与数组A的前一个数相乘就得到了下三角中数组B的当前数。
     * step 3：再从右到左遍历数组A，用一个数字记录从右到左上三角中的累乘，每次只会乘上一个数，同时给数组B对应部分也乘上该累乘。
     * @param A
     * @return
     */
    public int[] multiply2(int[] A) {
        //初始化数组B
        int[] B = new int[A.length];
        B[0] = 1;
        //先乘左边，从左到右
        for(int i = 1; i < A.length; i++)
            //每多一位由数组B左边的元素多乘一个前面A的元素
            B[i] = B[i - 1] * A[i - 1];
        int temp = 1;
        //再乘右边，从右到左
        for(int i = A.length - 1; i >= 0; i--){
            //temp为右边的累乘
            B[i] *= temp;
            temp *= A[i];
        }
        return B;
    }

}
