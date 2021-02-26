package app.vp.cn.ui.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by baiye
 * Date: 2020/12/29
 * Time: 14:28
 * Description:
 */
public class SystemCustomScrollView extends NestedScrollView {
    public SystemCustomScrollView(Context context) {
        super(context);
    }

    public SystemCustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SystemCustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        long l = System.currentTimeMillis();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("bai123", "onMeasure: 系统scrollView用时"+(System.currentTimeMillis()-l));
    }
}
