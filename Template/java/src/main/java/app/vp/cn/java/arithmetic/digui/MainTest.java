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

        int[] num = new int[]{2,3,2};
        Rob rob = new Rob();
        int rob1 = rob.rob2(num);
        System.out.println("小偷可以偷到的最多的钱是" + rob1);

        Rob2 rob2 = new Rob2();
        int rob3 = rob2.rob(num);
        System.out.println("在收尾连接的房屋中小偷可以偷到的最多的钱是" + rob3);
    }
}
