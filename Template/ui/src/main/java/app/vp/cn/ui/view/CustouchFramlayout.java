package app.vp.cn.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by baiye
 * Date: 2020/9/30
 * Time: 14:48
 * Description:
 */
public class CustouchFramlayout extends FrameLayout {
    public CustouchFramlayout(@NonNull Context context) {
        super(context);
    }

    public CustouchFramlayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustouchFramlayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            return false;//不消费掉
        }
        return super.onTouchEvent(event);
    }
}
