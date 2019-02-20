package app.vp.cn.java.arithmetic;

import java.util.Arrays;

/**
 * author : by
 * date: 2019/2/11 0011  下午 3:54.
 * describe  我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * <p>
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * <p>
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 */

public class Solution3 {
    public int[][] kClosest(int[][] points, int K) {
        int N = points.length;
        int[] dists = new int[N];
        for (int i = 0; i < N; ++i) {
            dists[i] = dist(points[i]);
        }
        Arrays.sort(dists);
        int distK = dists[K - 1];

        int[][] ans = new int[K][2];
        int t = 0;
        for (int i = 0; i < N; ++i) {
            if (dist(points[i]) <= distK) {
                ans[t++] = points[i];
            }
        }
        return ans;
    }

    private int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
