package app.vp.cn.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import app.vp.cn.common.util.LogUtil;
import app.vp.cn.ui.R;

/**
 * author : by
 * date: 2019/4/17 0017  上午 9:54.
 * describe
 */

public class ViewLinearlayout extends LinearLayout {


    public ViewLinearlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.my_layout, this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.i("xxx", "viewGroup 的 dispatchTouchEvent返回" + super.dispatchTouchEvent(ev));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.i("xxx", "viewGroup 的 onInterceptTouchEvent返回" + super.onInterceptTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.i("xxx", "viewGroup 的 onTouchEvent返回" + super.onTouchEvent(event));
        return super.onTouchEvent(event);
    }
}
