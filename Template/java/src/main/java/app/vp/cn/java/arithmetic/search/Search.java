package app.vp.cn.java.arithmetic.search;

/**
 * Created by baiye
 * Date: 2021/4/30
 * Time: 16:40
 * Description:
 */
public class Search {
    //查找第一个等于value 的值
    public int bSearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int middle = low + ((high - low) >> 1);
            if (value > a[middle]) {
                low = middle + 1;
            } else if (value < a[middle]) {
                high = middle - 1;
            } else {
                if (middle == 0 || a[middle - 1] != value) {
                    return middle;
                } else {
                    high = middle - 1;
                }
            }
        }
        return -1;
    }

    //查找最后一个等于value的值
    public int bSearch2(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (value < a[mid]) {
                high = mid - 1;
            } else if (value > a[mid]) {
                low = mid + 1;
            } else {
                if (mid == (n - 1) || a[mid + 1] != value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    //查找第一个大于等于给定值的元素
    public int bSearch3(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (high >= low) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if (mid == 0 || a[mid - 1] < value) {
                    return mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    //查找最后一个小于等于给定值的元素
    public int bSearch4(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (high >= low) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] <= value) {
                if (mid == n - 1 || a[mid + 1] > value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
