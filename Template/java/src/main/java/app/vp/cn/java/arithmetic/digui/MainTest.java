package app.vp.cn.java.arithmetic.digui;

/**
 * Created by baiye
 * Date: 2019/9/25
 * Time: 18:16
 * Description:
 */
public class MainTest {
    public static void main(String args[]) {
        Solution solution = new Solution();
        int i2 = 0;
        int i3 = 0;

        //int i1 = solution.climbStairs(6000);
        i2 = solution.climbStairs2(6000);
        i3 = solution.climbStairs3(6000);

        System.out.print("  " + i2 + "   " + i3);
    }
}
