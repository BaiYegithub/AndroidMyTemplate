package app.vp.cn.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * Created by baiye
 * Date: 2020/9/18
 * Time: 18:13
 * Description:
 */
public class CustomFrameLayout extends FrameLayout {
    public CustomFrameLayout(@NonNull Context context) {
        super(context);
    }

    public CustomFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.i("bai", "CustomFrameLayout  dispatchDraw: >>>>>>>>>>");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("bai", "CustomFrameLayout  onDraw: >>>>>>>>>");
    }
}
