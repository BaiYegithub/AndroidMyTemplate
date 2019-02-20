package app.vp.cn.java.arithmetic;

/**
 * author : by
 * date: 2019/2/11 0011  下午 2:59.
 * describe
 */

public class MyMain {
    public static void main(String[] args){
        int[] a = new int[]{6,5,4,4,8};
        Solution solution = new Solution();
      //  solution.isMonotonic(a);

        Solution4 solution4 = new Solution4();
        int i = solution4.largestPerimeter(a);
        System.out.println(i);
    }
}
