package app.vp.cn.java.arithmetic.search;

/**
 * Created by baiye
 * Date: 2021/4/30
 * Time: 16:52
 * Description:
 */
public class SearchMain {
    public static void main(String[] args) {
        int index = new Search().bSearch3(new int[]{1, 2, 6, 7, 9, 10, 16}, 7, 5);
        System.out.print("得到的索引是" + index);
    }


}
