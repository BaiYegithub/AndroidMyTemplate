package app.vp.cn.java.arithmetic.sort;

import java.util.Arrays;

/**
 * Created by baiye
 * Date: 2019/8/12
 * Time: 16:32
 * Description:  插入排序算法
 */
public class InsertSort {
    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insertValue = array[i];
            int j = i - 1;
            for (; j >= 0 && insertValue < array[j]; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = insertValue;
        }
    }

    public static void main(String[] args) {
        int array[] = {12, 1, 3, 46, 5, 0, -3, 12};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

}
