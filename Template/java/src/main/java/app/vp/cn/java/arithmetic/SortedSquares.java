package app.vp.cn.java.arithmetic;

import java.util.Arrays;

/**
 * author : by
 * date: 2019/2/22 0022  下午 5:39.
 * describe   给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 * <p>
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 */

public class SortedSquares {
    public static int[] sortedSquares(int[] A) {

        int[] newInts = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            newInts[i] = A[i] * A[i];
        }
        Arrays.sort(newInts);
        return newInts;
    }

    public static void main(String[] args){

        int[] ints = sortedSquares(new int[]{1, 2, 5, -3, 0});
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]+",");
        }
    }
}
