package app.vp.cn.java.arithmetic.sort;

import java.util.Arrays;

/**
 * Created by baiye
 * Date: 2021/2/26
 * Time: 10:28
 * Description: 快速排序
 */
class QuickSort {
    private static void quick_sort(int[] a, int n) {
        quick_sort_c(a, 0, n - 1);
    }

    private static void quick_sort_c(int[] a, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = partition(a, p, r);
        quick_sort_c(a, p, q - 1);
        quick_sort_c(a, q + 1, r);
    }

    private static int partition(int[] a, int p, int r) {
        //每次都以最后一个元素为参照对象
        int pivot = a[r];
        int i = p;
        for (int j = p; j <= r - 1; j++) {
            if (a[j] < pivot) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
                i = i + 1;
            }
        }
        int t = a[i];
        a[i] = a[r];
        a[r] = t;
        return i;
    }

    public static void main(String[] args) {
        int array[] = {12, 1, 3, 46, 5, 0, -3, 12};
        quick_sort(array, array.length);
        System.out.println(Arrays.toString(array));
    }

}
