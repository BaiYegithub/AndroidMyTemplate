package app.vp.cn.java.arithmetic;

import java.util.Arrays;

/**
 * author : by
 * date: 2019/2/11 0011  下午 5:18.
 * describe
 * 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
 * <p>
 * 如果不能形成任何面积不为零的三角形，返回 0。
 */

public class Solution4 {
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);//排序
        for (int i = A.length - 3; i >= 0; --i) {
            if (A[i] + A[i + 1] > A[i + 2]) {
                return A[i] + A[i + 1] + A[i + 2];
            }
        }
        return 0;
    }
}
