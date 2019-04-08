package app.vp.cn.ui.view;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * author : by
 * date: 2019/3/30 0030  下午 2:16.
 * describe
 */

public class ILoveYouCharFilter implements InputFilter {
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (end > 0){
            return "I LOVE YOU";
        }else {
            return null;
        }
    }
}
