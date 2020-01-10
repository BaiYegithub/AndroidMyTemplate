package app.vp.cn.java.arithmetic.digui;

import android.content.SyncStatusObserver;

/**
 * Created by baiye
 * Date: 2019/9/27
 * Time: 11:32
 * Description:
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2:
 * <p>
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Rob {

    public int rob(int[] num) {
        int prevMax = 0;
        int currMax = 0;

        for (int i = 0; i < num.length; i++) {
            int temp = currMax;
            currMax = Math.max(prevMax + num[i], currMax);
            prevMax = temp;

            System.out.println("第" + i + "轮结束后，prevMax是" + prevMax + "currMax 是" + currMax);
        }
        return currMax;
    }

    public int rob2(int[] num){
        if(num.length==0){
            return 0;
        }else if(num.length ==1){
            return num[0];
        }else if(num.length==2){
            return Math.max(num[0],num[1]);
        }else {
            int[] dp = new int[num.length];
            dp[0] = num[0];
            dp[1] = Math.max(num[0],num[1]);
            for (int i = 2; i <num.length ; i++) {
                dp[i] = Math.max(dp[i-1],dp[i-2]+num[i]);
            }
            return dp[num.length-1];
        }
    }
}
