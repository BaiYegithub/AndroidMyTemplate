package app.vp.cn.ui;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.Date;

import app.vp.cn.common.util.DateUtil;

public class EventDispatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch);
        Calendar instance = Calendar.getInstance();
        long yyyyMMdd = DateUtil.getStringToDate("20200131", "yyyyMMdd");
        instance.setTimeInMillis(yyyyMMdd);
        instance.add(Calendar.MONTH,1);
        long timeInMillis = instance.getTimeInMillis();
        String yyyyMMdd1 = DateUtil.getDateToString(timeInMillis, "yyyyMMdd");
        Log.i("bai", "onCreate: "+yyyyMMdd1);

//        TransitionDrawable drawable = (TransitionDrawable) ContextCompat.getDrawable(this, R.drawable.transition_drawable);
//
//        drawable.startTransition(4000);
        ImageView ivAcEvent = findViewById(R.id.iv_acEventDispatch);
//        ivAcEvent.setBackground(drawable);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("bai", "activity 的 dispatchTouchEvent:事件分发器 ");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("bai", "activity 的 onTouchEvent: 触摸事件");
        return super.onTouchEvent(event);
    }
}
