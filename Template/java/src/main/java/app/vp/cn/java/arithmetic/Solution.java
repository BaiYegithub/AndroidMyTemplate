package app.vp.cn.java.arithmetic;

/**
 * author : by
 * date: 2019/2/11 0011  下午 2:22.
 * describe
 */

public class Solution {
    /*如果数组是单调递增或单调递减的，那么它是单调的。

如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。

当给定的数组 A 是单调数组时返回 true，否则返回 false。*/
    public boolean isMonotonic(int[] A) {

        int right = 0;
        int left = 0;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i + 1] >= A[i]) {
                right++;
            }  if(A[i+1]<=A[i]){
                left++;
            }
        }

        if (right == A.length-1 || left == A.length-1) {
            return true;
        }
        return false;
    }
}
