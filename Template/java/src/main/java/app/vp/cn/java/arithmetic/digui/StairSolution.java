package app.vp.cn.java.arithmetic.digui;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by baiye
 * Date: 2021/2/8
 * Time: 15:34
 * Description:
 */
class StairSolution {
    /**
     * 楼梯问题，一个人，一步走1或者2个楼梯阶，一共有n个楼梯阶，一共几种走法  非递归解法
     *
     * @param n
     * @return
     */
    public int stair(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int ret = 0;
        int pre = 2;
        int prepre = 1;
        for (int i = 3; i <= n; ++i) {
            ret = pre + prepre;
            prepre = pre;
            pre = ret;
        }
        return ret;
    }

    /**
     * 楼梯问题，一个人，一步走1或者2个楼梯阶，一共有n个楼梯阶，一共几种走法  递归解法
     *
     * @param n
     * @return
     */
    private Map<Integer, Integer> map = new HashMap<>();

    public int stair2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int step = stair2(n - 1) + stair2(n - 2);
        map.put(n, step);
        return step;
    }

}
