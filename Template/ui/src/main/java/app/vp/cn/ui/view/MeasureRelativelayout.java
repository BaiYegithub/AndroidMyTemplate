package app.vp.cn.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * Created by baiye
 * Date: 2020/12/29
 * Time: 10:57
 * Description:
 */
public class MeasureRelativelayout extends RelativeLayout {
    public MeasureRelativelayout(Context context) {
        super(context);
    }

    public MeasureRelativelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureRelativelayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("bai", "onMeasure: measure前"+System.currentTimeMillis());
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("bai", "onMeasure: measure后"+System.currentTimeMillis());

    }
}
