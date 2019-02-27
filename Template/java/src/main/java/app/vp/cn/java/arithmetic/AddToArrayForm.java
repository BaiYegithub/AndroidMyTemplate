package app.vp.cn.java.arithmetic;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * author : by
 * date: 2019/2/26 0026  下午 2:16.
 * describe
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 * <p>
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 */

public class AddToArrayForm {
    public static List<Integer> addToArrayForm(int[] A, int K) {

       /* Long beforeNum = 0L;
        int x = 1;
        for (int i = A.length - 1; i >= 0; i--) {
            beforeNum += A[i] * x;
            x = 10 * x;
        }
        beforeNum += K;

        List<Integer> list = new ArrayList<>();

        String strBeforeNum = String.valueOf(beforeNum);

        for (int i = 0; i < strBeforeNum.length(); i++) {
            list.add(Integer.parseInt(strBeforeNum.substring(i, i + 1)));
        }

        return list;*/

        int N = A.length;
        int cur = K;
        List<Integer> ans = new ArrayList<>();

        int i = N;
        while (--i >= 0 || cur > 0) {
            if (i >= 0) {
                cur += A[i];
            }
            ans.add(cur % 10);
            cur /= 10;
        }
        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        int[] ll = new int[]{3, 9, 7, 6, 9, 5, 3, 4, 4, 9};
        List<Integer> integers = addToArrayForm(ll, 982);
        Log.i("xxx", integers.toString());
    }
}
