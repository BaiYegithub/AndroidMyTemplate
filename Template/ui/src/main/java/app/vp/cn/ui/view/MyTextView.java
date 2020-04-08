package app.vp.cn.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by baiye
 * Date: 2020/3/25
 * Time: 17:26
 * Description:
 */
public class MyTextView extends android.support.v7.widget.AppCompatTextView {
    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("bai", "MyTextView 的 dispatchTouchEvent:事件分发器 " );
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("bai", "MyTextView 的 onTouchEvent: 触摸事件" );
        return super.onTouchEvent(event);
    }
}
