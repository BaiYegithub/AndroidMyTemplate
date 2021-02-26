package app.vp.cn.java.arithmetic.sort;

import java.util.Arrays;

/**
 * Created by baiye
 * Date: 2021/2/19
 * Time: 17:23
 * Description: 归并排序
 */
public class MergeSort {
    //归并排序算法，a是数组，n表示数组大小
    public static void merge_sort(int[] a, int n) {
        merge_sort_c(a, 0, n - 1);
    }

    private static void merge_sort_c(int[] a, int p, int r) {
        //递归终止条件
        if (p >= r) {
            return;
        }
        //取p到r之间的中间位置q
        int q = (p + r) / 2;
        //分治递归
        merge_sort_c(a, p, q);
        merge_sort_c(a, q + 1, r);

        merge(a, p, q, r);
    }

    private static void merge(int[] a, int p, int q, int r) {
        //初始化变量 i,j,k
        int i = p, j = q + 1, k = 0;
        int[] tmp = new int[a.length];
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }

        //判断哪个子数组中有剩余的数据
        int start = i, end = q;
        if (j <= r) {
            start = j;
            end = r;
        }
        //将剩余的数据拷贝到临时数组tmp
        while (start <= end) {
            tmp[k++] = a[start++];
        }
        //将tmp中的数组拷贝回a
        for (i = 0;i<=r-p;i++){
            a[p+i] = tmp[i];
        }
    }

    public static void main(String[] args) {
        int array[] = {12, 1, 3, 46, 5, 0, -3, 12};
        merge_sort(array, array.length);
        System.out.println(Arrays.toString(array));
    }





}
