package app.vp.cn.java.javabasics;

import android.util.Log;

/**
 * author : by
 * date: 2019/4/16 0016  下午 4:24.
 * describe
 */

public class Test {
    public static void methodBasic() {
        int lNum = 3;
        methodRunInner(lNum);
        Log.d("CopySample", "After methodRunInner, lNum=" + lNum);
    }

    private static void methodRunInner(int lNum) {
        lNum++;
        Log.d("CopySample", "methodRunInner, lNum=" + lNum);
    }

    public static void main(String[] args){
        methodBasic();

    }
}
