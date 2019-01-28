package app.vp.cn.common.util;

import android.content.Context;
import android.widget.Toast;

import app.vp.cn.common.app.BaseApp;

/**
 * author : by
 * date: 2019/1/28 0028  下午 4:12.
 * describe
 */

public class UIUtils {

    private static Toast toast;

    public static void showToast(String content) {
        if (toast == null) {
            toast = Toast.makeText(BaseApp.getmContext(), content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public static Context getContext() {
        return BaseApp.getmContext();
    }
}
