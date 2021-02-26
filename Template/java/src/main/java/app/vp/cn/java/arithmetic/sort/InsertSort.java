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

    /**
     * 插入排序算法 ，a 表示要排序的数组 ，n表示数组的长度
     * @param a
     * @param n
     */
    public static void insertionSort(int[] a, int n) {
        if (n < 1) {
            return;
        }
        for (int i = 1; i < n; i++) {
            int value = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (a[j] > value) {
                    a[j+1] = a[j];
                }else {
                    break;
                }
            }
            a[j+1] = value;
        }

    }


    public static void main(String[] args) {
        int array[] = {12, 1, 3, 46, 5, 0, -3, 12};
        insertionSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }

}
